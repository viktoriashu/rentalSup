package com.viktoria.rentalSup.servlet;


import com.viktoria.rentalSup.dataSource.JspHelper;
import com.viktoria.rentalSup.dto.UserTypeDto.UserTypeDto;
import com.viktoria.rentalSup.enums.PageEnum;
import com.viktoria.rentalSup.service.UserTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserTypeService userTypeService = UserTypeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(PageEnum.LOGIN_PAGE.getValue()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userTypeService.login(req.getParameter("login"), req.getParameter("password"))
                .ifPresentOrElse(
                        userTypeDto -> onLoginSuccess(userTypeDto, req, resp),
                        () -> onLoginFail(req, resp)

                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&login=" + req.getParameter("login"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserTypeDto userTypeDto, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user_type", userTypeDto);
        resp.sendRedirect("/" + PageEnum.SUP_PAGE.getValue());
    }


}
