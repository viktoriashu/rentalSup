package com.viktoria.rentalSup.servlet;

import com.viktoria.rentalSup.dataSource.JspHelper;
import com.viktoria.rentalSup.enums.PageEnum;
import com.viktoria.rentalSup.service.SupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sup")
public class SupServlet extends HttpServlet {
    private final SupService supService = SupService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageEnum.SUP_PAGE.getValue(), supService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(PageEnum.SUP_PAGE.getValue()))
                .forward(req, resp);
    }
}
