package com.controller;

import com.dao.DaoModel;
import com.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoModel daoModel = new DaoModel();
        String username = request.getParameter("search-bar");
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        //checking if the user login and the searched name is the same
        if (username == "") {
            response.sendRedirect(request.getContextPath() + "/Dashboard?page=1");
        } else {

            //if the logged in user name is equal to the searched name then dont display that name


            List<Model> listOfAcc = daoModel.searchOption(username, id);
            request.setAttribute("listOfAcc", listOfAcc);
            request.setAttribute("searchValue", username);
            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
            rd.forward(request, response);

        }
    }
}
