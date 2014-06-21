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
package org.gcm.android.util;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
  
/***
 * Classe de detecção, indica se o aparelho possui conexão ativa com a internet.
 * @author  Iasmim Ribeiro
 * @sice 1.0
 * @version 1.0
 */
public class ConnectionDetector {
  
	/**
	 * Referência ao contexto de execução.
	 */
    private Context _context;
  
    /**
     * Construtor da classe.
     * @param context Obtém o contexto de execução assim que a classe e criada.
     */
    public ConnectionDetector(Context context){
        this._context = context;
    }
  
    /**
     * Verifica se o aparelho possui qualquer tipo de conexão ativa. seja ela mobile ou fixa (wifi).
     * @return true Caso haja conexões / false Caso não haja conexões.
     */
    public boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
                  for (int i = 0; i < info.length; i++)
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
  
          }
          return false;
    }
}