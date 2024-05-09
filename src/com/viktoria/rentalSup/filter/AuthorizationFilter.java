package com.viktoria.rentalSup.filter;

import com.viktoria.rentalSup.dto.UserTypeDto.UserTypeDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.viktoria.rentalSup.enums.PageEnum.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(SLASH.getValue() + LOGIN_PAGE.getValue(),
            SLASH.getValue() + REGISTRATION_PAGE.getValue(), SLASH.getValue() + LOCALE.getValue());
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri) || isUserLoggedIn(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse)servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN_PAGE.getValue());
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var userType = (UserTypeDto)((HttpServletRequest) servletRequest).getSession().getAttribute("user_type");
        return userType != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
