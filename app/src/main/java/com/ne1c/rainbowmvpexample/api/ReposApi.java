package com.ne1c.rainbowmvpexample.api;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

public interface ReposApi {
    @GET("/search/repositories?q=android&size=10&sort=stars")
    Observable<ArrayList<RepoModel>> getTopAndroidRepos();
}
