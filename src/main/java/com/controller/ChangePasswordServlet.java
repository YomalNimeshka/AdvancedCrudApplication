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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(name = "ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("id");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        //backend validation for password
        Pattern passPattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}");
        Matcher patternMatcher = passPattern.matcher(password);
        boolean match = patternMatcher.matches();

        if (password == null || confirmPassword == null) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        }else if (!confirmPassword.equals(password)) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        } else {
            Model model = new Model(userId,password,0);
            DAO dao = new DAO();
            dao.updatePassword(model);
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
