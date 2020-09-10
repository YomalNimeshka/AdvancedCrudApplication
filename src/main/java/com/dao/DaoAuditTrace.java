package com.dao;

import com.controller.LoginServlet;
import com.database.DbConnection;
import com.model.AuditModel;
import com.model.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DaoAuditTrace {
    private static Connection connection;
    public void sendAudoitTraceToDb(AuditModel model){
        String sql = "insert into audittrace (id,uri, timeStampUri) values (?, ?, ?)";
        LoginServlet id = new LoginServlet();

        try {
            connection= DbConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, model.getId());
            preparedStatement.setString(2, model.getUri());
            preparedStatement.setString(3, model.getTimeStamp());
            preparedStatement.execute();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
