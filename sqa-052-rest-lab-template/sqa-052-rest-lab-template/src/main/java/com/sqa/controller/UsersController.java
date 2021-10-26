package com.sqa.controller;

import com.sqa.model.user.User;
import com.sqa.model.user.UserResponse;
import com.sqa.model.user.Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersController extends GorestController {
    private static final String USERS_ENDPOINT = "/users";

    public  static Response postUser(User user){
        return RestAssured.given()
                .spec(request)
                .body(user)
                .post(USERS_ENDPOINT);

    }
    // или прятать ассерт в контроллер, а на уровень теста возвращать уже модель?
    public static Users getUserByEmail(String email) {
        Response response = RestAssured
                .given()
                .spec(request)
                .param("email", email)
                .get(USERS_ENDPOINT);
        response.then().statusCode(200);
        return response.getBody().as(Users.class);
    }

    public static UserResponse patchUserByEmail(String email, User updatedUser) {
        int userId = RestAssured
                .given()
                .spec(request)
                .param("email", email)
                .get(USERS_ENDPOINT)
                .as(Users.class)
                .getData().get(0).getId();
        Response response = RestAssured
                .given()
                .spec(request)
                .body(updatedUser)
                .patch(String.format("%s/%s", USERS_ENDPOINT, userId));
        response.then().statusCode(200);
        return response.getBody().as(UserResponse.class);
    }

    public static void deleteUserByEmail(String email) {
        int userId = RestAssured
                .given(request)
                .param("email", email)
                .get(USERS_ENDPOINT)
                .as(Users.class)
                .getData().get(0).getId();
        Response response = RestAssured
                .given(request)
                .delete(String.format("%s/%s", USERS_ENDPOINT, userId));
        response.then().statusCode(204);
    }

}
