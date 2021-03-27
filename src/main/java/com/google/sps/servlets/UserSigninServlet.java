package com.google.sps.servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.util.Collections;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;

/**
 * Handles requests sent to the /hello URL. Try running a server and navigating
 * to /hello!
 */
@WebServlet("/sign-in")
public class UserSigninServlet extends HttpServlet {

    static final long serialVersionUID = 0;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setClientIds("CLIENT_ID")
                .build();

        // (Receive idTokenString from the post POST)
        String idTokenString = request.getParameter("ID_token");

        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();
        
            // Use payload to retrieve user information information
            Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
            KeyFactory keyFactory = datastore.newKeyFactory().setKind("User");
            String userID = payload.getUserId();
            FullEntity userEntity = Entity.newBuilder(keyFactory.newKey(userID))
                                    .set("userID", userID)  
                                    .set("email", payload.getEmail())  
                                    .build();        
            datastore.put(userEntity);

            //redirect to userhomepage after successful signin
            response.sendRedirect("/homepage.html");
        } else {
            //redirect back to signin page when sign in fails
            response.sendRedirect("/index.html");
        }
  }
}


