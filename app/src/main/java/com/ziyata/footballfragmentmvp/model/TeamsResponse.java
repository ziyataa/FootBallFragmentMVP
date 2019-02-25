package com.ziyata.footballfragmentmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {
    @SerializedName("teams")
    private List<TeamsItem> teams;

    public List<TeamsItem> getTeams() {
        return teams;
    }

}
