package src.servlet;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/myServlet")
//@WebFilter(servletNames = "myServlet") //可指定过滤某个Servlet
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        servletContext.log("myFilter doFilterInternal");
        System.out.println("contextPath:" + servletContext.getContextPath());
        filterChain.doFilter(request, response);
    }
}
