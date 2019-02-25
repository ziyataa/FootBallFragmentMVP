package com.ziyata.footballfragmentmvp.ui.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.ziyata.footballfragmentmvp.model.TeamsItem;

public interface DetailTeamContract {

    interface View{
        void showDetailTeam(TeamsItem teamsItem);
        void showFailureMessage(String msg);
        void showSuccessMessage(String msg);
    }

    interface Presenter{
        void getDetailTeam(Bundle bundle);
        void addToFavorite(Context context, TeamsItem teamsItem);
        void removeFavorite(Context context, TeamsItem teamsItem);
        Boolean checkFavorite(Context context, TeamsItem teamsItem);
    }
}
