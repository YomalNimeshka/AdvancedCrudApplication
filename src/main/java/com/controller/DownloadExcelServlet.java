package com.controller;

import com.dao.DaoModel;
import com.model.Model;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(name = "DownloadExcelServlet")
public class DownloadExcelServlet extends HttpServlet {
    private final String DOWNLOAD_FILE_NAME = "ReportExcel.xlsx";
    private final String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("search-bar");
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("id");
        generateExcel(request, response,username,id);
        response.sendRedirect(request.getContextPath() + "/Dashboard?page=1");
    }

    public void generateExcel(HttpServletRequest request, HttpServletResponse response,String username, int id) {
        List<Model> dataList;
        String reportPath;
        OutputStream outputStream;
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JRDataSource reportSource;
        DaoModel jasperData;
        Map reportParameters;

        try {
            reportPath = request.getServletContext().getRealPath("WEB-INF/reports") + "/ireport.jrxml";

            reportParameters = new HashMap();
            reportParameters.put("userName", "User Name");

            jasperDesign = JRXmlLoader.load(reportPath);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

            jasperData = new DaoModel();
            if (username == "" || username == null) {
                dataList = jasperData.downloadPDF();
            }else{
                dataList = jasperData.searchOption(username, id);
            }
            reportSource = new JRBeanCollectionDataSource(dataList, false);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, reportSource);


            outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=" + DOWNLOAD_FILE_NAME);
            response.setContentType(FILE_TYPE);
            response.setContentLength(4096);

            JRXlsxExporter exporterXLS = new JRXlsxExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, DOWNLOAD_FILE_NAME);
            exporterXLS.exportReport();
            outputStream.close();


        } catch (JRException e) {
            Logger.getLogger(DownloadExcelServlet.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
