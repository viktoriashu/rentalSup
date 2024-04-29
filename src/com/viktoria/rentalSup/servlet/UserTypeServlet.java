package com.viktoria.rentalSup.servlet;

import com.viktoria.rentalSup.service.UserTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

//@WebServlet("/userType")
//возможно будет переделан и будет использоваться
//public class UserTypeServlet extends HttpServlet {
//
//    private final UserTypeService userTypeService = UserTypeService.getInstance();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        try (var printWriter = resp.getWriter()) {
//            printWriter.write("<h1>Список пользователей:</h1>");
//            printWriter.write("<ul>");
//            userTypeService.findAll().forEach(userTypeDto -> {
//                printWriter.write("""
//                        <li>
//                        <a href="roles?userTypeId=%d">%s</a>
//                        </li>
//                        """.formatted(userTypeDto.getId(), userTypeDto.getDescription()));
//            });
//
//            printWriter.write("</ul>");
//        }
//    }
//}
