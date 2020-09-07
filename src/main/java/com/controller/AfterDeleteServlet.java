package com.controller;

import com.dao.DAO;
import com.model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "AfterDeleteServlet")
public class AfterDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model =new Model();
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("id"));
        model.setId(id);

        dao.deleteAccount(model);
        response.sendRedirect(request.getContextPath()+ "/Dashboard?page=1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
