/*
 * (C) Copyright ${year} Nuxeo SA (http://nuxeo.com/) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Iasmim Ribeiro
 */
package org.gcm.server.transport;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe utilizada para armzenar os conteúdos de envio para o servidor GCM, ela
 * irá aceitar uma lista de id, e um {@code Map} que contém o tipo do envio e a
 * mensagem a ser enviada.
 *
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class Content {

    // <editor-fold desc=" VARIÁVEIS " defaultstate="collapsed">
    
    /**
     * Lista de ids de registros.
     */
    public List<String> registration_ids;
    
    /**
     * {@code Map} que cotém na sua chave o tipo de envio e em seu valor a 
     * mensagem a ser enviada.
     */
    public Map<String, String> data;

    // </editor-fold>
    
    // <editor-fold desc=" GETTER/SETTERS " defaultstate="collapsed">
    
    /**
     * Adiciona um id na lista de registros.
     * @param regId RegId para inserção na lista.
     */
    public void addRegId(String regId) {
        if (registration_ids == null) {
            registration_ids = new LinkedList<>();
        }
        registration_ids.add(regId);
    }

    /**
     * Adiciona o tipo e a mensagem passados no {@code Map} data.
     * @param type Tipo de envio
     *      1- Servidor (1:1)
     *      2- Grupo (1:N)
     * @param message Mensagem a ser enviada.
     */
    public void createData(String type, String message) {
        if (data == null) {
            data = new HashMap<>();
        }

        data.put("type", type);
        data.put("message", message);
    }
    
    /**
     * @param list O valor para alterar a propriedade list.
     */
    public void setRegistrationIds(List<String> list) {
        this.registration_ids = list;
    }

    /**
     * @return A propriedade registration_ids.
     */
    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    /**
     * @param registration_ids O valor para alterar a propriedade registration_ids.
     */
    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    /**
     * @return A propriedade data.
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * @param data O valor para alterar a propriedade data.
     */
    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    //</editor-fold>
}
