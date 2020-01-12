package src.spring;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebFilter(urlPatterns = "/myServlet")
//@WebFilter(servletNames = "myServlet") //可指定过滤某个Servlet
public class MyFilterForSpring extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        servletContext.log("myFilter doFilterInternal");
        filterChain.doFilter(request, response);
    }
}
