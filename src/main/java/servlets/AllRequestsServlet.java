package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");
        response.setContentType("text/html;charset=utf-8");

        if (key==null || key.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } else
            response.setStatus(HttpServletResponse.SC_OK);

        Map<String,Object> pageVariables = new HashMap<>();
        pageVariables.put("key",key);

        //response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        response.getWriter().println(key);

    }

}
