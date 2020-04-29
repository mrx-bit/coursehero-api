package kz.iitu.hackday.coursehero.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants.*;
import kz.iitu.hackday.coursehero.utils.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        System.out.println("CustomAuthenticationEntryPoint.commence");
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");

//        String json = String.format("{\"message\": \"%s\"}", e.getMessage());

        System.out.println(e.getMessage());
        System.out.println("MESSAGE END");

        ErrorResponse errorResponse = new ErrorResponse(UnauthorizedError.MESSAGE, UnauthorizedError.ERROR_CODE,
                HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());

        ObjectMapper mapper = new ObjectMapper();

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}
