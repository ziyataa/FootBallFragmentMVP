package com.ziyata.footballfragmentmvp.ui.teams;

import com.ziyata.footballfragmentmvp.model.TeamsItem;

import java.util.List;

public interface TeamsContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListItem();
        void getSearchTeams(String searchText);
    }
}
