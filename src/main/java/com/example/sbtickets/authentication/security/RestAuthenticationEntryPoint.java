package com.example.sbtickets.authentication.security;

import com.example.sbtickets.bean.AuthenticationBean;
import com.example.sbtickets.bean.WrapperResponse;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.BAD_REQUEST.value());
        json.put("body", null);
        json.put("msg", "Unauthorized");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(json.toString());
        httpServletResponse.getWriter().flush();
    }
}
