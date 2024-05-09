package com.viktoria.rentalSup.servlet;

import com.viktoria.rentalSup.dataSource.JspHelper;
import com.viktoria.rentalSup.dto.UserTypeDto.CreateUserTypeDto;
import com.viktoria.rentalSup.enums.PageEnum;
import com.viktoria.rentalSup.exception.ValidationException;
import com.viktoria.rentalSup.service.RoleService;
import com.viktoria.rentalSup.service.UserTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NUMBER = "number";

//    оставить для админки
//    private static final String ROLE = "role";
    private static final String ERRORS = "errors";
    private static final String ROLES = "roles";
    private static final int CLIENT_ROLE = 2;
    private final UserTypeService userTypeService = UserTypeService.getInstance();
    private final RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ROLES, roleService.findAll());

        req.getRequestDispatcher(JspHelper.getPath(PageEnum.REGISTRATION_PAGE.getValue()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userTypeDto = CreateUserTypeDto.builder()
                .firstName(req.getParameter(FIRST_NAME))
                .lastName(req.getParameter(LAST_NAME))
                .login(req.getParameter(LOGIN))
                .password(req.getParameter(PASSWORD))
                .number(req.getParameter(NUMBER))
                .roleId(String.valueOf(CLIENT_ROLE))
                //оставить для админки
//                .roleId(req.getParameter(ROLE))
                .build();

        try {
            userTypeService.create(userTypeDto);
            resp.sendRedirect("/" + PageEnum.LOGIN_PAGE.getValue());
        } catch (ValidationException exception) {
            req.setAttribute(ERRORS, exception.getErrors());
            doGet(req, resp);
        }
    }
}



//@WebServlet("/registration")
//public class RegistrationServlet extends HttpServlet {
//
//    private static final String FIRST_NAME = "firstName";
//    private static final String LAST_NAME = "lastName";
//    private static final String LOGIN = "login";
//    private static final String PASSWORD = "password";
//    private static final String NUMBER = "number";
//    private static final String ROLE = "role";
//    private static final String ERRORS = "errors";
//    private static final String ROLES = "roles";
//    private final UserTypeService userTypeService = UserTypeService.getInstance();
//    private final RoleService roleService = RoleService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute(ROLES, roleService.findAll());
//
//        req.getRequestDispatcher(JspHelper.getPath(PageEnum.REGISTRATION_PAGE.getValue()))
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var userTypeDto = CreateUserTypeDto.builder()
//                .firstName(req.getParameter(FIRST_NAME))
//                .lastName(req.getParameter(LAST_NAME))
//                .login(req.getParameter(LOGIN))
//                .password(req.getParameter(PASSWORD))
//                .number(req.getParameter(NUMBER))
//                .roleId(req.getParameter(ROLE))
//                .build();
//
//        try {
//            userTypeService.create(userTypeDto);
//            resp.sendRedirect("/" + PageEnum.LOGIN_PAGE.getValue());
//        } catch (ValidationException exception) {
//            req.setAttribute(ERRORS, exception.getErrors());
//            doGet(req, resp);
//        }
//    }
//}