/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UserDAO;
/**
 *
 * @author ASUS
 */

// /forgot-password
public class HandleForgotPassword extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dis = request.getRequestDispatcher("forgot-password.jsp");
        dis.forward(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String identifier = request.getParameter("identifier");

        // tìm user theo username hoặc email
        boolean exists = UserDAO.checkUserExists(identifier);

        if (!exists) {
            request.setAttribute("message", "User not found!");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
        } else {
            // đơn giản: cho reset luôn (basic)
            request.setAttribute("identifier", identifier);
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
