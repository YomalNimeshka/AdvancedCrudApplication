package com.controller;

import com.dao.DAO;
import com.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model =new Model();
        DAO dao = new DAO();

        //String username = request.getParameter("userName");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

       // model.setUserName(username);
        model.setId(id);

        Model account = dao.editAccountDetails(model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Delete.jsp");
        request.setAttribute("account", account);
        request.setAttribute("id", id);
        requestDispatcher.forward(request,response);

    }
}
