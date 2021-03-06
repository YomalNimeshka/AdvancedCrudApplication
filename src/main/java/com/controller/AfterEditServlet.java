package com.controller;

import com.dao.DaoModel;
import com.model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "AfterEditServlet")
public class AfterEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = new Model();

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("userName");
        String nic = request.getParameter("nic");
        String mobileNumber = request.getParameter("mobileNumber");
        String gender = request.getParameter("gender");

        model.setId(id);
        model.setUserName(username);
        model.setNic(nic);
        model.setMobileNumber(mobileNumber);
        model.setGender(gender);

        DaoModel daoModel = new DaoModel();
        daoModel.updateAccount(model);


        response.sendRedirect(request.getContextPath() + "/Dashboard?pageId=1&sort=id&order=1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
