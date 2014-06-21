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

import org.gcm.android.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe de utilit�rios do sistema.
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class Util {
	
	/**
	 * Obt�m uma preference do arquivo de prefer�ncias da aplica��o.
	 * @param context Contexto de execu��o.
	 * @param prefName Nome da prefer�ncia.
	 * @param type Tipo primitivo da prefer�ncia.
	 * @return Valor da prefer�ncia.
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T getPreference(Context context, String prefName, T type){
		final SharedPreferences prefs = context.getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		Object resultado = null;
		
		 if (type instanceof String) {
			 resultado = prefs.getString(prefName, "");
         }
         if (type instanceof Integer) {
        	 resultado = prefs.getInt(prefName, 0);
         }
         if (type instanceof Float) {
        	 resultado = prefs.getFloat(prefName, 0l);
         }
         if (type instanceof Boolean) {
        	 resultado = prefs.getBoolean(prefName, false);
         }
         
         return (T) resultado;
	}
	
	/**
	 * Insere/Atualiza o valor de uma determinada prefer�ncia.
	 * @param context Contexto de execu��o.
	 * @param prefName Nome da prefer�ncia.
	 * @param value Valor da prefer�ncia a ser atualizada.
	 */
	public final static <T> void setPreference(Context context, String prefName, T value) {
		final SharedPreferences prefs = context.getSharedPreferences(
				MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = prefs.edit();
		
		 if (value instanceof String) {
             mEditor.putString(prefName, (String) value);
         }
         if (value instanceof Integer) {
             mEditor.putInt(prefName, (Integer) value);
         }
         if (value instanceof Float) {
             mEditor.putFloat(prefName, (Float) value);
         }
         if (value instanceof Boolean) {
             mEditor.putBoolean(prefName, (Boolean) value);
         }
		
		mEditor.commit();
	}
	
}
