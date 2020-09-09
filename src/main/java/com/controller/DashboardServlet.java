package com.controller;

import com.dao.DAO;
import com.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession session = request.getSession(false);
        //String accountName = (String) session.getAttribute("accountName");
        int id = (int) session.getAttribute("id");
        if (session != null && id != 0) {

            String pageid = request.getParameter("page");
            int page = Integer.parseInt(pageid);
            int total = 5;
            String sortId = "user_id";
            if (page == 1) {
            } else {
                page = page - 1;
                page = page * total + 1;
            }
            int noOfRecords = dao.NoOfRecords();
            //this is requrired for pagination
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / total);

            List<Model> listOfAcc = dao.pagination(page, total, sortId, id);
            request.setAttribute("listOfAcc", listOfAcc);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("sortType", sortId);


           /* List<Model> listOfAcc = null;
            try {
                listOfAcc = dao.displayTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("listOfAcc", listOfAcc);*/

            RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("login has to be done first");
            request.getRequestDispatcher("Login.jsp").include(request, response);
        }


    }
}
