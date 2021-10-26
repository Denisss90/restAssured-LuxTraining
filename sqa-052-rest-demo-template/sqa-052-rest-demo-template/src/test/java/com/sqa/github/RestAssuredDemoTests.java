package com.sqa.github;

import com.sqa.model.github.Issue;
import com.sqa.utils.TestLogger;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredDemoTests implements TestLogger {

    private static final String BASE_URL = "https://api.github.com/";
    private static final String HEALTHCHECK_ENDPOINT = "/zen";
    private String issueTitle = String.format("issue %s", RandomStringUtils.randomAlphabetic(5));
    private String issueDescription = "Description of new issue";

    /*Re
        01. Проверяем, что приходит 200 код в ответ на простой GET
    */
    @Test
    public void verifyHealthcheckTest() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get(HEALTHCHECK_ENDPOINT)
                .then()
                .statusCode(200)
                .log().all();

    }

    /*
        02. Проверяем, что приходит непустое тело ответа на простой GET
    */
    @Test
    public void verifyDefunktBodyTest() {
        Response response= given()
                .baseUri(BASE_URL)
                .when()
                .get(HEALTHCHECK_ENDPOINT);
        response.then().body(Matchers.not(Matchers.empty()));

    }

    /*
        03. Проверяем, что тело ответа содержит поле, равное значению
    */
    @Test
    public void verifyIssuesContainTest() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/repos/ilyademchenko/rest/issues")
                .then()
                .body("message", equalTo("Not Found"));
    }

    /*
        04. Проверяем, что тело ответа содержит поле после авторизации
    */
    @Test
    public void verifyIssuesAuthorized() {
        given()
                .baseUri(BASE_URL)
                .header("Authorization","Bearer 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")
                .when()
                .get("/repos/ilyademchenko/rest/issues")
                .then()
                .body("title", hasItem("lux-training 09"))
                .log().all();
    }

    /*
        05. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyIssuesNoUserAgent() {
        given()
                .baseUri(BASE_URL)
                .header("Authorization","Bearer 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")
                .header("Accept", "application/xml")
                .when()
                .get("/repos/ilyademchenko/rest/issues")
                .then()
                .statusCode(415)
                .body("message", containsString("Must accept 'application/json'"))
                .log().all();
    }

    /*
        06. Проверяем, что ишью публикуется (тело запроса в строке)
    */
    @Test
    public void verifyPostIssues() {
        given()
                .baseUri(BASE_URL)
                .header("Authorization","Bearer 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")
                .header("Accept", "application/json")
                .body("{\n" +
                        "    \"title\":\"lux-training 07\",\n" +
                        "    \"body\": \"Description of issue\"\n" +
                        "}"
                )
                .when()
                .post("/repos/ilyademchenko/rest/issues")
                .then()
                .statusCode(201)
                .body("title",containsString("lux-training 07"));
    }

    /*
        07. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyPostIssuesUrlParam() {
        given()
                // тут другой ресурс
                .baseUri("https://gorest.co.in/public/v1")
                .relaxedHTTPSValidation()
                .header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
                .param("title", "test-title")
                .param("body", "test-body")
                .when()
                .post("/users/1813/posts")
                .then()
                .statusCode(201)
                .body("data", Matchers.hasEntry("title", "test-title"))
                .log().all();

    }

    /*
        08. Проверяем, что ишью публикуется (тело запроса в POJO)
    */
    @Test
    public void verifyPostPojo() {
        Issue requestIssue = new Issue();
        requestIssue
                .setTitle(issueTitle)
                .setBody(issueDescription);

        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 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")
                .body(requestIssue)
                .when()
                .post("/repos/ilyademchenko/rest/issues");

        Issue responseIssue = response.body().as(Issue.class);

        assertAll(
                () -> assertEquals(201, response.statusCode()),
                () -> assertEquals(requestIssue.getTitle(), responseIssue.getTitle(), "Issue title"),
                () -> assertEquals(requestIssue.getBody(), responseIssue.getBody(), "Issue description"));
        log("END: Verify POST issues");

    }

    /*
        09. Проверяем, что ишью публикуется (тело запроса в Map)
    */
    @Test
    public void verifyPostMap() {

    }

    /*
        10. Проверяем, что ишью публикуется (тело запроса в POJO, поиск с помощью json path)
    */
    @Test
    public void verifyPostPojoWithJsonPath() {
        Issue requestIssue = new Issue();
        requestIssue
                .setTitle(issueTitle)
                .setBody(issueDescription);

        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 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")
                .body(requestIssue)
                .when()
                .post("/repos/ilyademchenko/rest/issues");

        assertAll(()-> assertEquals(issueTitle, response.jsonPath().get("title"),"Issue title"),
                ()-> assertEquals(issueDescription, response.jsonPath().get("body"),"Issue Description"));


    }
}
