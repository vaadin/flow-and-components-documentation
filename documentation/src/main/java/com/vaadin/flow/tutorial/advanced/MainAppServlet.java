package com.vaadin.flow.tutorial.advanced;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.flow.tutorial.annotations.CodeFor;

@CodeFor("advanced/tutorial-sswc.asciidoc")
@WebServlet(urlPatterns = { "/*" })
public class MainAppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Object authToken = req.getSession().getAttribute("auth_token");
        boolean isAuthenticated = authToken != null;

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println(
                    "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            if (!isAuthenticated) {
                out.println(
                        "<link rel='import' href='/vaadin/web-component/login-form.html'>");
            }
            out.println("<body>");
            if (isAuthenticated) {
                out.println("<h1>Welcome "
                        + UserService.getInstance().getName(authToken)
                        + "</h1>");
            } else {
                out.println(
                        "<login-form userlbl='Username' pwdlbl='Password'></login-form>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}