package com.controller;

import com.dao.DAO;
import com.model.Model;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(name = "DownloadPDFServlet")
public class DownloadPDFServlet extends HttpServlet {
    private final String DOWNLOAD_FILE_NAME = "Report.pdf";

    private final String FILE_TYPE = "application/pdf";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        generateReport(request,response);
        response.sendRedirect(request.getContextPath() + "/Dashboard?page=1");
    }

    private void generateReport(HttpServletRequest request, HttpServletResponse response) {
        List<Model> dataList;
        String reportPath;
        OutputStream outputStream;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        DAO jasperData;

        Map reportParameters;

        try {
            reportPath = request.getServletContext().getRealPath("WEB-INF/reports") + "/ireport.jrxml";

            reportParameters = new HashMap();
            reportParameters.put("userName", "User Name");

            jasperDesign = JRXmlLoader.load(reportPath);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            jasperData = new DAO();
            dataList = jasperData.downloadPDF();
            reportSource = new JRBeanCollectionDataSource(dataList, false);

            byte[] byteStream;
            byteStream = JasperRunManager.runReportToPdf(jasperReport, reportParameters, reportSource);

            outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=" + DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(byteStream.length);
            outputStream.write(byteStream, 0, byteStream.length);
            System.out.println("PDF Created");
            outputStream.close();


        } catch (JRException e) {
            Logger.getLogger(DownloadPDFServlet.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
