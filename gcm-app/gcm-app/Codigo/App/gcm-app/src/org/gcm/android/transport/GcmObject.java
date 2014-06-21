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
package org.gcm.android.transport;
import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Classe para recebimento do JSON enviado pelo servidor ( ela servirá de molde para a desserialização )
 * do JSON recebido.
 * @author  Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 *
 */
public class GcmObject implements Serializable {
	

	/**
	 * Serial version gerada pelo Eclipse.
	 */
	private static final long serialVersionUID = -5619344545517862582L;
	
	/**
	 * Tipo da resposta.
	 * 1- Servidor (1:1).
	 * 2- Grupo (1:N).
	 */
	@SerializedName("type")
	public String type;
	
	/**
	 * Conteúdo da mensagem.
	 */
	@SerializedName("text")
	public String text;
	
	/**
	 * @return Obtém o valor da propriedade type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type Atualiza o valor da propriedade type.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return Obtém o valor da propridade text.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text Atualiza o valor da propridade text.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
