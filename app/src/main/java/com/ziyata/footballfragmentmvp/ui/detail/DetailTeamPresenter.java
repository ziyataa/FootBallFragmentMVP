package com.ziyata.footballfragmentmvp.ui.detail;

import android.content.Context;
import android.os.Bundle;

import com.ziyata.footballfragmentmvp.data.local.FootballlDatabase;
import com.ziyata.footballfragmentmvp.model.TeamsItem;
import com.ziyata.footballfragmentmvp.utils.Constants;

public class DetailTeamPresenter implements DetailTeamContract.Presenter {

    private final DetailTeamContract.View view;
    private FootballlDatabase footballlDatabase;

    public DetailTeamPresenter(DetailTeamContract.View view){
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null){
            TeamsItem teamsItem = (TeamsItem) bundle.getSerializable(Constants.KEY_DATA);
            view.showDetailTeam(teamsItem);
        }else{
            view.showFailureMessage("Data Kosong");
        }
    }

    @Override
    public void addToFavorite(Context context, TeamsItem teamsItem) {
        footballlDatabase = FootballlDatabase.getFootballlDatabase(context);
        footballlDatabase.footballDao().insertItem(teamsItem);
        view.showSuccessMessage("Tersimpan");
    }

    @Override
    public void removeFavorite(Context context, TeamsItem teamsItem) {
        footballlDatabase = FootballlDatabase.getFootballlDatabase(context);
        footballlDatabase.footballDao().delete(teamsItem);
        view.showSuccessMessage("Terhapus");
    }

    @Override
    public Boolean checkFavorite(Context context, TeamsItem teamsItem) {
        Boolean bFavorite = false;
        footballlDatabase = FootballlDatabase.getFootballlDatabase(context);
        return bFavorite  = footballlDatabase.footballDao().selectedItem(teamsItem.getIdTeam()) != null;
    }
}
