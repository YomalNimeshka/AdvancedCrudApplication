package com.controller;

import com.dao.DaoModel;
import com.model.Model;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet(name = "DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoModel dao = new DaoModel();
        List<Model> listUser;
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("id");
        String sort = request.getParameter("sort");
        int order= Integer.parseInt(request.getParameter("order"));
        //System.out.println("order by"+ order);
        int pageid = Integer.parseInt(request.getParameter("pageId"));
        String columnName;
        String SortType;
        int total = 5;

        if ((order%2)==1){
            SortType="DESC";
        }else{
            SortType="ASC";
        }
        if (pageid == 1) {
        } else {
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }
        if (userId != 0) {
            if (sort.equals("userName")) {
                columnName = "userName";
            }else if (sort.equals("gender")) {
                columnName = "gender";
            }else if (sort.equals("nic")) {
                columnName = "nic";
            }else if (sort.equals("mobileNumber")) {
                columnName = "mobileNumber";
            } else {
               columnName = "id";
            }

            int noOfRecords = dao.NoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);
            listUser = dao. pagination( pageid, total, columnName, userId,SortType);
            request.setAttribute("listOfAcc", listUser);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", pageid);
            request.setAttribute("columnName", columnName);
            request.setAttribute("order", order);


            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("login has to be done first");
            request.getRequestDispatcher("Login.jsp").include(request, response);
        }


    }
}
