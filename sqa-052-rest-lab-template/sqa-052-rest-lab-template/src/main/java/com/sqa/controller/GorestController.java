package com.sqa.controller;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class GorestController {
    private static final String BASE_URL = "https://gorest.co.in/public/v1";
    private static final String TOKEN = "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36";
    private static final String CONTENT_TYPE="application/json";

    public static RequestSpecification request = RestAssured.given()
            // Что б забить на сертификаты
            .relaxedHTTPSValidation()
            .baseUri(BASE_URL)
            .header("Authorization",TOKEN)
            .header("Content-Type",CONTENT_TYPE);

}
