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
package org.gcm.android.enumeradores;

/***
 * Enumerador que contém os nomes das preferences, armzenadas em arquivo XML.
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public enum EnumPreferences {
	
	/**
	 * Constante email.
	 */
	EMAIL("email"), 
	
	/**
	 * Constante nome.
	 */
	NAME("nome"), 
	
	/**
	 * Constante lista de mensagens.
	 */
	LISTA_DE_MENSAGENS("listaDeMensagens"), 
	
	/**
	 * Constante usuario registrado.
	 */
	USUARIO_REGISTRADO("usuarioRegistrado");
	
	/**
	 * Variável para referência aos valores do enum.
	 */
	public String valor;
	
	/**
	 * Construtor do enumerador.
	 * @param valor Para obter referência do enum.
	 */
	EnumPreferences(String valor){
		this.valor = valor;
	}
	
	/**
	 * Retorna o valor do enumerador em string.
	 */
	@Override
	public String toString(){
		return valor;
		
	}
}
