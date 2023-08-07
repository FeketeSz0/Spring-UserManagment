package jdbc.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomErrorHandler", urlPatterns = "/error")
public class CustomErrorHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errorHandling(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errorHandling(req, resp);
    }

    private void errorHandling(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int code = (int) req.getAttribute("javax.servlet.error.status_code");
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>We are sorry</h1>");
        if (code == 404) {
            resp.getWriter().println("<p>But this page is not exists</p>");
        } else {
            resp.getWriter().println("<p>Unfortunate error has occurred</p>");
        }
    }
}

