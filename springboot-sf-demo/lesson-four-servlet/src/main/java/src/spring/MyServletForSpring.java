package src.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/*@WebServlet(
        name = "myServlet",
        urlPatterns = "/myServlet",
        initParams = {
                @WebInitParam(name="myname",value = "myname")
        }
)*/
public class MyServletForSpring extends HttpServlet {

    private String value;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        value = config.getInitParameter("myname");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        ServletContext servletContext = getServletContext();
        servletContext.log("myServlet doGet");
        writer.write("<html><body>Hello,World,my value=" + this.value + "</body></html>");
    }


}
