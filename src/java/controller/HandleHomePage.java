/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Product;
import dao.ProductDAO;
import models.User;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


/**
 *
 * @author ASUS
 */

// /home
public class HandleHomePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("./login");
            return;
        }
        
        // Lấy từ khóa tìm kiếm (nếu có)
        String keyword = request.getParameter("keyword");
        if (keyword != null) {
            keyword = keyword.toLowerCase().trim();
        }
        String sort = request.getParameter("sort");
        
        int page = 1;
        int pageSize = 8;
        
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (Exception e) {
                page = 1;
            }
        }
        
        // goi dao moi 
        ArrayList<Product> products = ProductDAO.getProducts(keyword, sort, page, pageSize);
        
        int totalProducts = ProductDAO.countProducts(keyword);
        System.out.println("tong san pham: " + totalProducts);
        int totalPages = (int)Math.ceil((double) totalProducts/pageSize);
        
        request.setAttribute("products", products);
        request.setAttribute("currentPage", page); 
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("keyword", keyword);
        request.setAttribute("sort", sort);
        
        request.getRequestDispatcher("./product_list.jsp").forward(request, response);
    } 

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        processRequest(request, response);
//    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
