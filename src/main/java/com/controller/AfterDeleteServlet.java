package com.controller;

import com.dao.DaoModel;
import com.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "AfterDeleteServlet")
public class AfterDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();
        DaoModel daoModel = new DaoModel();
        int id = Integer.parseInt(request.getParameter("id"));
        model.setId(id);

        daoModel.deleteAccount(model);
        response.sendRedirect(request.getContextPath() + "/Dashboard?pageId=1&sort=id&order=1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
