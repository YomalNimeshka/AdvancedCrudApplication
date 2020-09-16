package com.controller;

import com.dao.DaoAuditTrace;
import com.dao.DaoModel;
import com.model.AuditModel;
import com.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        model.setUserName(userName);
        model.setPassword(password);

        DaoModel dao = new DaoModel();
        AuditModel auditModel = new AuditModel();
        DaoAuditTrace daoAuditTrace = new DaoAuditTrace();
        try {
            Model isConnected = dao.loginUser(model);
            int id = isConnected.getId();
            int tempId=isConnected.getTempPass();
            //System.out.println(id);
            if (id == 0) {
                //user cannot login
                RequestDispatcher rd = request.getRequestDispatcher("LoginWrong.jsp");
                rd.include(request, response);
            } else if(tempId==1){
                HttpSession session = request.getSession(true);
                session.setAttribute("id", id);
                RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
                rd.include(request, response);

            }else {
                //user is been logged in and creating a session
                HttpSession session = request.getSession(true);
                session.setAttribute("accountName", userName);
                session.setAttribute("id", id);

                //audit trace part
                String clientURI = request.getRequestURI();
                //System.out.println(id);
                auditModel.setId(id);
                auditModel.setUri(clientURI);
                String time = new Date().toString();
                auditModel.setTimeStamp(time);

                System.out.println(auditModel.getId());
                System.out.println(auditModel.getTimeStamp());
                System.out.println(auditModel.getUri());
                // Print client URI and time stamp
                //System.out.println("Client URI: "+ clientURI + ", Time Stamp: "
                //        + new Date().toString());
                // Pass data to the filter chain
                daoAuditTrace.sendAudoitTraceToDb(auditModel);


                response.sendRedirect(request.getContextPath() + "/Dashboard?pageId=1&sort=id&order=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
