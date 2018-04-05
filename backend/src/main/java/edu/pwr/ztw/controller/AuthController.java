package edu.pwr.ztw.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static String GOOGLE_CLIENT_ID = "617044410490-kkm8acuq742knaeomou7lgt2f1s7emoi.apps.googleusercontent.com";
    private static String GOOGLE_CLIENT_SECRET = "nfqrC0kFL7xYDQvl3ay_1iO5";

    @RequestMapping(path = "/google", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> googleAuth(@RequestBody String authCode) {
        try {
            authCode = getAuthCode(authCode);

            GoogleTokenResponse tokenResponse = getGoogleTokenResponse(authCode);
            String accessToken = tokenResponse.getAccessToken();
            return new ResponseEntity<>(accessToken, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("error", HttpStatus.UNAUTHORIZED);
    }

    private GoogleTokenResponse getGoogleTokenResponse(@RequestBody String authCode) throws IOException {
        GoogleTokenResponse tokenResponse = null;
        tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                "https://www.googleapis.com/oauth2/v4/token",
                GOOGLE_CLIENT_ID,
                GOOGLE_CLIENT_SECRET,
                authCode,
                "http://localhost:8080")
                .execute();
        return tokenResponse;
    }

    private String getAuthCode(@RequestBody String authCode) throws IOException {
        ObjectMapper json = new ObjectMapper();
        JsonNode node = json.readTree(authCode);
        authCode = node.get("authorizationCode").asText();
        return authCode;
    }
}
