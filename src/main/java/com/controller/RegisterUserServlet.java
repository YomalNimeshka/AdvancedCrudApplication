package com.controller;

import com.dao.DaoModel;
import com.model.Model;
import com.util.EmailSend;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(name = "RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String nic = (request.getParameter("nic"));
        String mobileNumber = request.getParameter("mobileNumber");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String mail = request.getParameter("mail");


        // matching nic
        Pattern patt = Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher nicM = patt.matcher(nic);
        boolean nicMatch = nicM.matches();

        // matching nic
        Pattern pattMail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher pMail = pattMail.matcher(mail);
        boolean mailMatch = pMail.matches();


        //validation for mobile number
        Pattern numberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\\d)\\d{6}$");
        Matcher mobilePatternMatcher = numberPattern.matcher(mobileNumber);
        boolean mobileMatch = mobilePatternMatcher.matches();


        //validation for all the user inputs for the registration.
        if (userName == null || mobileNumber == null || gender == null || nic == null) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        } else if (!nicMatch == true) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        }
        else if (!mailMatch == true) {
            System.out.println("error");
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        } else if (!mobileMatch == true) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        } else if (!confirmPassword.equals(password)) {
            RequestDispatcher rd = request.getRequestDispatcher("RegisterError.jsp");
            rd.forward(request, response);
        } else {
            try{
                Model model = new Model(userName, nic, mobileNumber, gender, password,1);
                DaoModel dao = new DaoModel();
                EmailSend E = new EmailSend();
                dao.registerUser(model);
                E.send(mail,"Change password by login","http://localhost:8080/CRUD/");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
