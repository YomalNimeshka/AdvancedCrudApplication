package com.controller;

import com.dao.DaoAuditTrace;
import com.dao.DaoModel;
import com.model.AuditModel;
import com.model.Model;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;

//@WebFilter(filterName = "JspFilter")
public class JspFilter implements Filter {
    private ArrayList<String> urlList;
    private String initParam;
    private ServletContext context;



    public void init(FilterConfig config) throws ServletException {
        //Get initialization parameters
        initParam = config.getInitParameter("init-param");
        //Check initialization parameter values
        //System.out.println("Initialization Parameters: " + initParam);

    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        AuditModel auditModel = new AuditModel();
        DaoAuditTrace daoAuditTrace = new DaoAuditTrace();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Integer userId = null;
        if(!request.getRequestURL().toString().equalsIgnoreCase("http://localhost:8080/CRUD/") &&
                !request.getRequestURL().toString().equalsIgnoreCase("http://localhost:8080/CRUD/Login")
        ){
            userId = (Integer) ((HttpServletRequest) req).getSession().getAttribute("id");
            System.out.println("ID : "+ userId);
        }

        if (userId!=null){
            //audit trace part
            String clientURI = request.getRequestURI();

            //sending the trace data to the model
            auditModel.setId(userId);
            auditModel.setUri(clientURI);
            String time = new Date().toString();
            auditModel.setTimeStamp(time);

            System.out.println(auditModel.getId());
            System.out.println(auditModel.getTimeStamp());
            System.out.println(auditModel.getUri());

            daoAuditTrace.sendAudoitTraceToDb(auditModel);
            chain.doFilter(req, resp);
        }else {
            //audit trace part
            String clientURI = request.getRequestURI();

            //sending the trace data to the model
            auditModel.setId(userId);
            auditModel.setUri(clientURI);
            String time = new Date().toString();
            auditModel.setTimeStamp(time);

            System.out.println(auditModel.getId());
            System.out.println(auditModel.getTimeStamp());
            System.out.println(auditModel.getUri());
            //daoAuditTrace.sendAudoitTraceToDb(auditModel);
            chain.doFilter(req, resp);
        }



    }
    public void destroy() {
    }



}
