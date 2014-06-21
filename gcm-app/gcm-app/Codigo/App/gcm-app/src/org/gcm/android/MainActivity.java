/*
 * (C) Copyright 2014.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Iasmim Ribeiro
 */

package org.gcm.android;

import org.gcm.android.config.IConfig;
import org.gcm.android.enumeradores.EnumPreferences;
import org.gcm.android.http.ShareWithJAVAServer;
import org.gcm.android.transport.GcmDesserializer;
import org.gcm.android.transport.GcmObject;
import org.gcm.android.util.Util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

/**
 * Classe que irá exibir as mensagens recebidas.
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 *
 */
public class MainActivity extends ActionBarActivity {
	
	/**
	 * Referência a classe {@code ShareWithJAVAServer}.
	 */
	ShareWithJAVAServer appUtil;
	
	/**
	 * Variável para armazenar o regId.
	 */
	String regId;
	
	/**
	 * Variável para armzenar o nome.
	 */
	String mNome;
	
	/**
	 * Variável para armzenar o email.
	 */
	String mEmail;
	
	/**
	 * Referência a classe assíncrona de requisições.
	 */
	AsyncTask<Void, Void, String> shareRegidTask;
	
	/**
	 * Referência ao objeto forma de desserialização de JSON.
	 */
	private GcmDesserializer mListaDeMensagens;
	
	/**
	 * Referências a UI linear Messages.
	 */
	public LinearLayout mLinearMessages;
	
	/**
	 * Referência a propria classe.
	 */
	public static MainActivity mMain;
	
	/**
	 * Construtor da classe.
	 */
	public MainActivity(){
		mListaDeMensagens = new GcmDesserializer();
		mMain = this;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appUtil = new ShareWithJAVAServer();
		
		mLinearMessages = (LinearLayout) findViewById(R.id.gcm_received_messages);
		
		registerReceiver(mHandleMessageReceiver, new IntentFilter("com.google.android.c2dm.intent.RECEIVE"));
		
		regId = getIntent().getStringExtra("regId");
		mNome = getIntent().getStringExtra(EnumPreferences.NAME.toString());
		mEmail = getIntent().getStringExtra(EnumPreferences.EMAIL.toString());
		Boolean usuarioRegistrado = getIntent().getBooleanExtra(EnumPreferences.USUARIO_REGISTRADO.toString(), false);

		Log.d("MainActivity", "regId: " + regId);

		final Context context = this;
		
		if(!usuarioRegistrado){
			sendIdToServer(context);
			shareRegidTask.execute(null, null, null);
		}
	
	}
	
	/**
     * onResume da classe.
     */
	@Override
    public void onResume() {
        super.onResume();
    }
	
	/**
     * onPause da classe.
     */
    @Override
    public void onPause() {
        super.onPause();
    }
    
    /**
     * OnDestroy da classe.
     */
    @Override
    public void onDestroy(){
    	unregisterReceiver(mHandleMessageReceiver);
    	super.onDestroy();
    }
	    
	/**
	 * Adiciona as mensagens recebidas ao linear layout na UI.
	 * @param context Contexto de execução.
	 * @param element Elemento que contém o tipo da mensagem e a mensagem em si.
	 */
	public final void addMsgToLinearMessages(Context context, GcmObject element) {		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		
		final RelativeLayout rl = (RelativeLayout) inflater.inflate(org.gcm.android.R.layout.custom_layout, null);
		
		TextView tx1 = (TextView) rl.findViewById(R.id.type);
		if(element.type.equals("1")){
			tx1.setTypeface(null, Typeface.BOLD);
			tx1.setText("group:");
		}
		else{
			tx1.setTypeface(null, Typeface.NORMAL);
			tx1.setText("server:");
		}
		
		TextView tx2 = (TextView) rl.findViewById(R.id.text);
		tx2.setText(element.text);
				
		mLinearMessages.addView(rl);
	}

	/**
	 * Envia o id + as informações inseridas pelo usuário para o servidor PHP.
	 * @param context Contexto de execução.
	 */
	private void sendIdToServer(final Context context) {
		shareRegidTask = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String result = appUtil.registreUsuairioPHPServidor(context, regId, mNome, mEmail);
				return result;
			}

			@Override
			protected void onPostExecute(String result) {
				shareRegidTask = null;
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			}

		};
	}
		
	 /**
     * Broadcast para receber as mensagens.
     * */
    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
    	
    	/**
    	 * Tag de identificação no log.
    	 */
    	public static final String TAG = "GCMNotificationIntentService";
    	/**
    	 * Referência a notificação.
    	 */
    	public static final int NOTIFICATION_ID = 1;
    	/**
    	 * Referência ao notification manager.
    	 */
    	private NotificationManager mNotificationManager;
    	
        @Override
        public void onReceive(Context context, Intent intent) {
        	Bundle extras = intent.getExtras();

    		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(MainActivity.this);

    		String messageType = gcm.getMessageType(intent);

    		if (!extras.isEmpty()) {
    			if(messageType != null){
    				if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
    						.equals(messageType)) {
    					shotNotification("Send error: " + extras.toString());
    				} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
    						.equals(messageType)) {
    					shotNotification("Deleted messages on server: "
    							+ extras.toString());
    				} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
    						.equals(messageType)) {
    	
    					Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
    	
    					shotNotification("Message Received from Google GCM Server: "
    							+ extras.get(IConfig.MESSAGE_KEY));
    					Toast.makeText(getApplicationContext(),"Mensagem recebida: " +
    							extras.get(IConfig.MESSAGE_KEY).toString(),
    							Toast.LENGTH_SHORT).show();
    					
    					GcmObject gcmOb = new GcmObject();
    					gcmOb.text = extras.getString(IConfig.MESSAGE_KEY);
    					gcmOb.type = extras.getString(IConfig.MESSAGE_TYPE);
    					
    					addMessageInSharedPrefs(gcmOb);
    					
    					Log.i(TAG, "Received: " + extras.toString());
    				}
    			}
    		}
        }
        
        /**
    	 * Método para criar ou atualizar a notificação na barra de notificações do
    	 * android.
    	 * 
    	 * @param msg
    	 *            Mensagem para ser inserida na notificação.
    	 */
    	private final void shotNotification(String msg) {
    		Log.d(TAG, "Preparing to send notification...: " + msg);
    		mNotificationManager = (NotificationManager) MainActivity.this
    				.getSystemService(Context.NOTIFICATION_SERVICE);

    		PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0,
    				new Intent(MainActivity.this, MainActivity.class), 0);

    		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
    				.setSmallIcon(org.gcm.android.R.drawable.gcm_logo)
    				.setContentTitle("GCM Notification")
    				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
    				.setContentText(msg);

    		mBuilder.setContentIntent(contentIntent);
    		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    		Log.d(TAG, "Notification sent successfully.");
    	}
    	
    	private final void addMessageInSharedPrefs(final GcmObject msg){
    		Gson gson = new Gson();
    		
    		addMsgToLinearMessages(MainActivity.this, msg);
    		
    		Util.setPreference(getApplicationContext(), EnumPreferences.LISTA_DE_MENSAGENS.toString(), gson.toJson(MainActivity.mMain.getmListaDeMensagens()));
    		
    	}
    };
	
    /**
     * @return O valor da propriedade mListaDeMensagens.
     */
	public final GcmDesserializer getmListaDeMensagens() {
		return mListaDeMensagens;
	}

	/**
	 * @param mListaDeMensagens Valor para atualizar a propriedade mListaDeMensagens.
	 */
	public final void setmListaDeMensagens(GcmDesserializer mListaDeMensagens) {
		this.mListaDeMensagens = mListaDeMensagens;
	}
	
}
