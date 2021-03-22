package com.google.sps.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.util.Collections;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/sign-in")
public class UserSigninServlet extends HttpServlet {

  static final long serialVersionUID = 0;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        // Specify the CLIENT_ID of the app that accesses the backend:
    .setAudience(Collections.singletonList("CLIENT_ID"))
        /* for you to be able to use the user information do
         *(1) comment the line 23 above
         *(2) uncomment the line 28 below and replace CLIENT_ID_N with your own client id
         */
        //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
    .build();

    // (Receive idTokenString from the post POST)
    String idTokenString = request.getParameter("ID_token");

    GoogleIdToken idToken = verifier.verify(idTokenString);
    if (idToken != null) {
        Payload payload = idToken.getPayload();
    
        // Use payload to retrieve user information information

    } else {
        System.out.println("Invalid ID token.");
    }
  }
}


