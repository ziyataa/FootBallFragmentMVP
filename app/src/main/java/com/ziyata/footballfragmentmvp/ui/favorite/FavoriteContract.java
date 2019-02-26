package com.ziyata.footballfragmentmvp.ui.favorite;

import android.content.Context;

import com.ziyata.footballfragmentmvp.model.TeamsItem;

import java.util.List;

public interface FavoriteContract {

    interface View{
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }

    interface  Presenter{
        void  getDataListTeams(Context context);
        void SearchTeams(Context context, String searchText);
    }
}
