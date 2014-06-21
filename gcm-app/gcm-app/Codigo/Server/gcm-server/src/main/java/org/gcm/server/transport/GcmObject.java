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

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Classe utilizada para centralizar as informações recebidas pelo servidor
 * enviadas do smartphone, o JSON enviado pelo smartphone, será desserializado
 * nesta classe forma.
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class GcmObject {

    // <editor-fold desc=" VARIÁVEIS " defaultstate="collapsed">
    
    /**
     * Id do usuário.
     */
    private int id;

    /**
     * Lista de ids de usuários.
     */
    @SerializedName("list")
    private ArrayList<String> lista;
    
    /**
     * Mensagem para envio.
     */
    private String message;

    /**
     * Nome do grupo para envio da mensagem.
     */
    @SerializedName("group_name")
    private String groupName;

    //</editor-fold>
    
    // <editor-fold desc=" CONSTRUTOR " defaultstate="collapsed">
    
    /**
     * Construtor da classe.
     */
    public GcmObject() {
        this.lista = new ArrayList<>();
    }

    //</editor-fold>
    
    // <editor-fold desc=" GETTERS/SETTERS " defaultstate="collapsed">
    
    /**
     * @return O valor da propriedade id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id O valor para alterar a propriedade {@code id}.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return A propriedade lista.
     */
    public ArrayList<String> getLista() {
        return lista;
    }

    /**
     * @param lista O valor para alterar a propriedade lista.
     */
    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    /**
     * @return O valor da propriedade message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message O valor para alterar a propriedade message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return O valor da propriedade groupName.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName O valor para alterar a propriedade groupName.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    //</editor-fold>
}
