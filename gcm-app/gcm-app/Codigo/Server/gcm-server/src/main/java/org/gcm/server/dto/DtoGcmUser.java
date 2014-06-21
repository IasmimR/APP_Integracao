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
package org.gcm.server.dto;

/**
 * Classe para transporte de dados (serialização), para envio e recebimento de
 * informações, entre o smartphone e o servidor.
 *
 * @author Iasmim Ribeiro
 * @version 1.0
 * @serial 1.0
 */
public class DtoGcmUser {

    // <editor-fold desc=" VARIÁVEIS " defaultstate="collapsed">
    /**
     * Id do usuário.
     */
    private int id;

    /**
     * Nome do usuário.
     */
    private String name;

    // </editor-fold>
    
    // <editor-fold desc=" GETTER/SETTERS " defaultstate="collapsed">
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
     * @return O valor da propriedade name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name O valor para alterar a propriedade {@code name}.
     */
    public void setName(String name) {
        this.name = name;
    }

    // </editor-fold>
}
