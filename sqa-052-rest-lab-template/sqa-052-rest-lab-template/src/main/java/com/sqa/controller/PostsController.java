package com.sqa.controller;

import com.sqa.model.post.Post;
import com.sqa.model.post.PostResponse;
import com.sqa.model.post.Posts;
import com.sqa.model.user.User;
import com.sqa.model.user.UserResponse;
import com.sqa.model.user.Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostsController extends GorestController {
    private static final String POST_ENDPOINT_OF_USER = "/users/%d/posts";

    public static Response addPostForUser(Integer userId, Post post) {
        return RestAssured
                .given(request)
                .body(post)
                .post(getPostEndpointOfUser(userId));
    }

    public static PostResponse addPostForUserReturnPost(Integer userId, Post post) {
        return RestAssured
                .given(request)
                .body(post)
                .post(getPostEndpointOfUser(userId))
                .body()
                .as(PostResponse.class);
    }

    public static Posts getPostOfUser(Integer userId, String postTitle) {
        Response response = RestAssured
                .given(request)
                .param("title", postTitle)
                .get(getPostEndpointOfUser(userId));
        response.then().statusCode(200);
        return response.getBody().as(Posts.class);
    }

    public static Posts getPostOfUser(Integer userId, Integer postId) {
        Response response = RestAssured
                .given(request)
                .param("id", postId)
                .get(getPostEndpointOfUser(userId));
        response.then().statusCode(200);
        return response.getBody().as(Posts.class);
    }

    public static Posts getPostsOfUser(Integer userId) {
        Response response = RestAssured
                .given(request)
                .get(getPostEndpointOfUser(userId));
        response.then().statusCode(200);
        return response.getBody().as(Posts.class);
    }

    private static String getPostEndpointOfUser(Integer userId){
        return String.format(POST_ENDPOINT_OF_USER, userId);
    }


}
