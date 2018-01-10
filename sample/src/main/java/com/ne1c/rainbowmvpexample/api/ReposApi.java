package com.ne1c.rainbowmvpexample.api;

import retrofit2.http.GET;
import rx.Observable;

public interface ReposApi {
    @GET("/search/repositories?q=android&size=10&sort=stars")
    Observable<RepoResponse> getTopAndroidRepos();
}
