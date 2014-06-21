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
import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 *
 */
public class GcmDesserializer implements Serializable {
	
	/**
	 * Serial version gerada pelo Eclipse.
	 */
	private static final long serialVersionUID = 9191204116709505600L;
	
	/**
	 * Lista de mensagens.
	 */
	public List<GcmObject> list;

	/**
	 * Construtor da classe, inicializa a coleção para prevenir exceção.
	 */
	public GcmDesserializer(){
		list = new ArrayList<GcmObject>();
	}
	
	/**
	 * Obtém a lista de mensagens.
	 * @return lista de mensagens.
	 */
	public List<GcmObject> getList() {
		return list;
	}

	/**
	 * Atualiza a lista de mensagens.
	 * @param list para atualização da lista de mensagens.
	 */
	public void setList(List<GcmObject> list) {
		this.list = list;
	}
	
	/**
	 * Adiciona um determinado item na lista.
	 * @param object Item a ser adicionado.
	 */
	public void addItem(GcmObject object){
		list.add(object);
	}
	
	/**
	 * Retorna o tamanho da lista.
	 * @return Tamanho da lista.
	 */
	public int sizeOfList(){
		return list.size() - 1;
	}
	
}
