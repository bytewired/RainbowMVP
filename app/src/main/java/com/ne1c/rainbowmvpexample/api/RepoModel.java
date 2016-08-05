package com.ne1c.rainbowmvpexample.api;

import com.google.gson.annotations.SerializedName;

public class RepoModel {
    public String name;

    @SerializedName("html_url")
    public String url;
    public String description;

    @SerializedName("stargazers_count")
    public int stars;
}
