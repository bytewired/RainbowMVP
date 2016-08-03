package com.ne1c.rainbowmvpexample.api;

import retrofit2.http.GET;

public interface ReposApi {
    @GET("/search/repositories?q=android&size=10&sort=stars")
    void getTopAndroidRepos();
}
