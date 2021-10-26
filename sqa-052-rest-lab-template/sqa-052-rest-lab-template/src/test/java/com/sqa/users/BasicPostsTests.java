package com.sqa.users;

import com.sqa.controller.PostsController;
import com.sqa.controller.UsersController;
import com.sqa.model.post.Post;
import com.sqa.model.post.Posts;
import com.sqa.model.user.User;
import com.sqa.model.user.UserResponse;
import com.sqa.model.user.Users;
import com.sqa.utils.FileUtils;
import com.sqa.utils.TestLogger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicPostsTests implements TestLogger {

    private static String userEmail = "businessman@mail.ru";
    private static User testUser;
    private static Post testPost;

    @BeforeAll
    public static void init() {
//        UsersController.postUser(
//                FileUtils.getModelFromJson(".\\src\\test\\resources\\businessUser.json",
//                        User.class));
        testUser = UsersController.getUserByEmail(userEmail).getData().get(0);
        testPost = FileUtils.getModelFromJson(".\\src\\test\\resources\\newPost.json",
                Post.class);
        testPost.setUserId(testUser.getId()).setTitle(String.format("Test %s", RandomStringUtils.randomAlphabetic(3)));
    }

    /*
           01. Проверяем создание публикации для пользователя
        */
    @Order(1)
    @Test
    public void verifyCreatePostTest() {
        PostsController
                .addPostForUser(testUser.getId(), testPost)
                .then()
                .statusCode(201);
    }

    /*
        02. Получаем публикацию пользователя
    */
    @Order(2)
    @Test
    public void verifyGetCreatedPostTest() {
        Posts postsResponse = PostsController.getPostOfUser(testUser.getId(), testPost.getTitle());
        testPost.setId(postsResponse.getData().get(0).getId());
        assertEquals(testPost, postsResponse.getData().get(0), "New post");
    }


    /*
        03. Обновляем публикацию пользователя
    */
    @Test
    public void verifyPatchCreatedPostTest() {

    }

    /*
        04. Удаляем публикацию пользователя
    */
    @Test
    public void verifyDeletePostTest() {

    }
}

