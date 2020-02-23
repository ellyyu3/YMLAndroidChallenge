package com.example.jinliyu.ymlandroidchallenge.data;

import com.example.jinliyu.ymlandroidchallenge.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUsersApi {

    @GET("/users/{username}/followers")
    Observable<List<User>> getFollowersByUsername(@Path("username") String username);

    @GET("/users/{username}")
    Single<User> getUserDetail(@Path("username") String userName);


}
