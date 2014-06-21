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
package org.gcm.android.config;

/***
 * Interface de configurações para envio e recebimento de informações pela aplicação.
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public interface IConfig {

	/**
	 * Utilizado para enviar o GCM regId para o servidor PHP.
	 */
	static final String APP_SERVER_URL = "http://192.168.25.43:8080/gcm-server/Register";

	/**
	 * Número do projeto.
	 */
	static final String GOOGLE_PROJECT_ID = "52482822914";
	
	/**
	 * Tag de identificação da mensagem no JSON recebido pelo servidor JAVA. 
	 */
	static final String MESSAGE_KEY = "message";
	
	/**
	 * Tipo da requisição ou tipo da mensagem recebida.
	 * Valores: Servidor e Grupo.
	 */
	static final String MESSAGE_TYPE = "type";

}
