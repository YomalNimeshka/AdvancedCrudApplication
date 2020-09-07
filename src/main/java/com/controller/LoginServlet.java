package com.controller;

import com.dao.DAO;
import com.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        model.setUserName(userName);
        model.setPassword(password);

        DAO dao = new DAO();
        try {
            Model isConnected = dao.loginUser(model);

            int id = isConnected.getId();
            System.out.println(id);

            if (id==0){
                //user cannot login
                RequestDispatcher rd = request.getRequestDispatcher("LoginWrong.jsp");
                rd.include(request,response);
            }else {
                //user is been logged in and creating a session
                HttpSession session = request.getSession(true);
                session.setAttribute("accountName", userName);
                session.setAttribute("id", model.getId());
                response.sendRedirect(request.getContextPath()+ "/Dashboard?page=1");
                /*RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
                rd.forward(request,response);*/
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
