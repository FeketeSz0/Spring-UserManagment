package api;

import api.dto.UserForm;
import api.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEndpoints {

    private RestTemplate restTemplate;
    private final String url = "http://fekete-app:8080/api/users/";
    private HttpHeaders headers;

    @BeforeEach
    void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
    }

    @Test
    void getUser() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<UserForm> response = restTemplate.exchange(
                url + "1",
                HttpMethod.GET,
                entity,
                UserForm.class);
        UserForm userForm = response.getBody();
        int statusCodeValue = response.getStatusCodeValue();
        assertNotNull(userForm);
        assertEquals(200, statusCodeValue);
        assertEquals(userForm.getLogin(), "MichaelS");
    }

    @Test
    void getAllUsers() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<User>> reference = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                reference);
        List<User> users = response.getBody();
        int statusCodeValue = response.getStatusCodeValue();
        assertNotNull(users);
        assertEquals(200, statusCodeValue);
    }

    @Test
    void addUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\n" +
                "    \"login\": \"addTest\",\n" +
                "    \"password\": 123,\n" +
                "    \"confirmPassword\": 123,\n" +
                "    \"email\": \"test@example.com\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"birthday\": \"1990-05-15\",\n" +
                "    \"role\": \"Admin\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);
        String id = response.getBody();
        assertNotNull(id);
        assertEquals(200, response.getStatusCodeValue());

       deleteUserCleanup(id);
    }

    @Test
    void deleteUser() {
        String id = addUserSetUp();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> deleteResponse = restTemplate.exchange(
                url + id,
                HttpMethod.DELETE,
                entity,
                String.class);
        assertEquals(200, deleteResponse.getStatusCodeValue());
        assertEquals("\"User deleted\"", deleteResponse.getBody());
    }

    @Test
    void updateUser() {
        String id = addUserSetUp();
        String requestBody = "{\n" +
                "    \"login\": \"test\",\n" +
                "    \"password\": 123,\n" +
                "    \"confirmPassword\": 123,\n" +
                "    \"email\": \"updated@example.com\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"birthday\": \"1990-05-15\",\n" +
                "    \"role\": \"Admin\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url + id,
                HttpMethod.PUT,
                entity,
                String.class);
        assertEquals("\"user updated\"", response.getBody());
        deleteUserCleanup(id);
    }

    @Test
    void addUserOnExistingUsername() {
        String requestBody = "{\n" +
                "    \"login\": \"user\",\n" +
                "    \"password\": 123,\n" +
                "    \"confirmPassword\": 123,\n" +
                "    \"email\": \"test@example.com\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"birthday\": \"1990-05-15\",\n" +
                "    \"role\": \"Admin\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        HttpClientErrorException.BadRequest errorException = assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.exchange(url, HttpMethod.POST, entity, Void.class));
        assertEquals(HttpStatus.BAD_REQUEST, errorException.getStatusCode());
    }

    private String addUserSetUp() {
        String requestBody = "{\n" +
                "    \"login\": \"test\",\n" +
                "    \"password\": 123,\n" +
                "    \"confirmPassword\": 123,\n" +
                "    \"email\": \"test@example.com\",\n" +
                "    \"firstName\": \"test\",\n" +
                "    \"lastName\": \"test\",\n" +
                "    \"birthday\": \"1990-05-15\",\n" +
                "    \"role\": \"Admin\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> postResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);
        return postResponse.getBody();
    }
    private void deleteUserCleanup(String id) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                url + id,
                HttpMethod.DELETE,
                entity,
                String.class);
    }
}
