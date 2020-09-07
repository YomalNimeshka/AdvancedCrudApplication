package com.controller;

import com.dao.DAO;
import com.database.DbConnection;
import com.model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;

//@WebServlet(name = "UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String filepath = request.getServletContext().getRealPath("WEB-INF/csvFile") + "/upload.csv";
        int batchSize = 20;
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText = null;

            int count =0;
            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null){
                String[] data = lineText.split(",");
                String userName = data[0];
                String nic = data[1];
                String mobileNumber = data[2];
                String gender = data[3];
                String password = data[4];
                Model model = new Model(userName, nic, mobileNumber, gender, password);
                dao.registerUser(model);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("csv uploaded");
        response.sendRedirect(request.getContextPath()+ "/Dashboard?page=1");
    }
}
