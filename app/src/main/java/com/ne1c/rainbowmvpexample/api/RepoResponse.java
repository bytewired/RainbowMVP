package com.ne1c.rainbowmvpexample.api;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepoResponse {
    @SerializedName("items")
    public ArrayList<RepoModel> repos = new ArrayList<>();
}
