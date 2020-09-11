package com.controller;

import com.dao.DaoAuditTrace;
import com.model.AuditModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "TrackRecordServlet")
public class TrackRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer userId = null;
        userId = (Integer) session.getAttribute("id");
        AuditModel model = new AuditModel();
        model.setId(userId);
        List<AuditModel> trackList;

        if (userId== null){

        }else{
            DaoAuditTrace daoAuditTrace = new DaoAuditTrace();
            try {
                trackList =  daoAuditTrace.getTrace(model);
                request.setAttribute("listOfAcc", trackList);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("TrackRecord.jsp");
            dispatcher.forward(request, response);
        }

    }
}
