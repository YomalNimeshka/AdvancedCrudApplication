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

//@WebServlet(name = "EditServlet")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();

        //String username = request.getParameter("userName");
        int id = Integer.parseInt(request.getParameter("id"));
        //model.setUserName(username);
        //System.out.println(username);
        model.setId(id);

        DAO dao = new DAO();
        Model accountDetails = dao.editAccountDetails(model);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Edit.jsp");
        request.setAttribute("account", accountDetails);
        request.setAttribute("id", id);
        requestDispatcher.forward(request,response);
    }
}
