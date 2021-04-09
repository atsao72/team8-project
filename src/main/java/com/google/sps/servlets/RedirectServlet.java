package main.java.com.google.sps.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

/**
 * handles redirecting the user to their homepage when they signed in successfully
 */
@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    static final long serialVersionUID = 0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/home-page.html");
  }
}


