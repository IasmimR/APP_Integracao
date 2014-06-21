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
package org.gcm.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gcm.server.db.DbFunctions;
import org.gcm.server.transport.Content;
import org.gcm.server.transport.GcmUser;

/**
 * Servlet para receber as solicitações de registro de usuários.
 *
 * @author Iasmim Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class Register extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet.">
    /**
     * Processa as requisições HTTP seja ela <code>GET</code> ou
     * <code>POST</code>.
     *
     * @param request servlet Requisição ao servlet.
     * @param response servlet Resposta do servlet a requisição.
     * @throws ServletException Exceção específica do servlet, se ocorrer
     * alguma.
     * @throws IOException Se ocorrer alguma exceção de I/O.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Contempla o método HTTP <code>GET</code>.
     *
     * @param request servlet Requisição ao servlet.
     * @param response servlet Resposta do servlet a requisição.
     * @throws ServletException Exceção específica do servlet, se ocorrer
     * alguma.
     * @throws IOException Se ocorrer alguma exceção de I/O.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Contempla o método HTTP <code>POST</code>.
     *
     * @param request servlet Requisição ao servlet.
     * @param response servlet Resposta do servlet a requisição.
     * @throws ServletException Exceção específica do servlet, se ocorrer
     * alguma.
     * @throws IOException Se ocorrer alguma exceção de I/O.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> list = new ArrayList<>();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        GcmUser gcmUSer = new GcmUser();
        gcmUSer.setName(request.getParameter("name"));
        gcmUSer.setEmail(request.getParameter("email"));
        gcmUSer.setGcm_regid(request.getParameter("regId"));

        list.add(request.getParameter("regId"));

        Content content = new Content();
        DbFunctions dbFunctions = new DbFunctions();

        dbFunctions.storeUser(gcmUSer);

        content.setRegistrationIds(list);
        content.createData("1", "registrado");

        try {
            String resp = GcmNotification.sendNotification(content);
            out.write(resp);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retorna uma pequena descrição do servlet.
     *
     * @return uma String contendo a descrição do servlet.
     */
    @Override
    public String getServletInfo() {
        return "Pequena descrição.";
    }

    // </editor-fold>
}
