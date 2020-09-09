package com.controller;

import com.dao.DAO;
import com.model.Model;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "C:/temp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            String name = null;
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                File fileDir = new File(UPLOAD_DIRECTORY);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
                //send this selected file to the uploading to db method
                uploadFileToDb(request, response, name);

                //File uploaded successfully
                System.out.println("File uploaded");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {

        }
    }

    public void uploadFileToDb(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
        DAO dao = new DAO();
        //getting the file saved file path to upload to database
        File fileDir = new File(UPLOAD_DIRECTORY);
        String filepath = fileDir.getAbsolutePath() + "/" + name;
        System.out.println(filepath);

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText ;


            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String userName = data[0];
                String nic = data[1];
                String mobileNumber = data[2];
                String gender = data[3];
                String password = data[4];
                Model model = new Model(userName, nic, mobileNumber, gender, password);
                dao.registerUser(model);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("csv uploaded");
        response.sendRedirect(request.getContextPath() + "/Dashboard?page=1");
    }
}
