package com.sqa.users;

import com.sqa.model.user.User;
import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndToEndTests implements TestLogger {

    /*
        Новый пользователь создаёт новую публикацию.
    */
    @Test
    public void verifyNewUserCanCreateNewPost() {

    }

    /*
        Существующий пользователь получает все публикации, и клонирует последнюю созданную.
    */
    @Test
    public void verifyExistingUserCanGetAllPostsAndCreateNewOne() {

    }

    /*
        Пользователь меняет данные своего аккаунта и проверяет что все публикации по прежнему привязаны к аккаунту.
    */
    @Test
    public void verifyUserUpdatesAccountAndAllOldPostsAreAvailable() {

    }

    /*
        Проверка доступа к одной публикации несколькими пользователями.
    */
    @Test
    public void verifySeveralUsersHasAccessToPublication() {

    }

    /*
        Пользователь удаляет все существующие публикации и дальше удаляет свой аккаунт (AfterAll).
    */
    @AfterAll
    public static void cleanupTest() {

    }
}
