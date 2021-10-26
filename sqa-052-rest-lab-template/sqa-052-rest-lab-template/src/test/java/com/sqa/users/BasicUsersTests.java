package com.sqa.users;

import com.sqa.controller.UsersController;
import com.sqa.model.user.User;
import com.sqa.model.user.UserResponse;
import com.sqa.model.user.Users;
import com.sqa.utils.FileUtils;
import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BasicUsersTests implements TestLogger {

    private static  User testUser;

    @BeforeAll
    public static void init(){
        testUser = FileUtils.getModelFromJson(".\\src\\test\\resources\\newUser.json", User.class);
    }

     /*
        01. Проверяем создание пользователя
     */
     @Order(1)
     @Test
     @DisplayName("Проверка что пользователь создался")
     public void verifyCreateUserTest() {
        UsersController
                .postUser(testUser)
                .then()
                .statusCode(201);
     }

    /*
        02. Получаем пользователя по емейлу
    */
    @Order(2)
    @Test
    @DisplayName("Проверка созданного пользователь запросом")
    public void verifyGetCreatedUserTest() {
        Users usersResponse = UsersController.getUserByEmail(testUser.getEmail());
        testUser.setId(usersResponse.getData().get(0).getId());
        assertEquals(testUser, usersResponse.getData().get(0), "New user");


    }

    /*
        03. Обновляем пользователя по емейлу
    */
    @Order(3)
    @Test
    public void verifyPatchCreatedUserTest() {
        User updatedUser = testUser.clone();
        updatedUser.setStatus("inactive");
        UserResponse usersResponse = UsersController.patchUserByEmail(testUser.getEmail(), updatedUser);
//        testUser.setId(usersResponse.getData().getId());
//        updatedUser.setId(usersResponse.getData().getId());
        assertEquals(updatedUser, usersResponse.getData(), "Updated user");

    }

    /*
        04. Удаляем пользователя по емейлу
    */
    @Order(4)
    @Test
    public void verifyDeleteUserTest() {
        UsersController.deleteUserByEmail(testUser.getEmail());
        assertEquals(0,
                UsersController.getUserByEmail(testUser.getEmail()).getData().size(),
                "Zero users found");

    }
}

