package com.ziyata.footballfragmentmvp.data.remote;

import com.ziyata.footballfragmentmvp.model.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamsResponse> getAllTeams(@Query("s") String s, @Query("c") String c);
}
