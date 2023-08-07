package jdbc.utils;


import com.google.gson.Gson;
import jdbc.dto.RecaptchaResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RecaptchaUtil {
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";


    public static boolean verifyRecaptcha(String recaptchaResponse, String secretKey) {

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            URI uri = new URI(RECAPTCHA_VERIFY_URL);

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("secret=" + secretKey + "&response=" + recaptchaResponse))
                    .build();


            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            RecaptchaResponse recaptchaResponseObj = gson.fromJson(response.body(), RecaptchaResponse.class);

            return recaptchaResponseObj.isSuccess();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
