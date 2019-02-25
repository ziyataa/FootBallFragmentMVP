package com.ziyata.footballfragmentmvp.ui.favorite;

import android.content.Context;

import com.ziyata.footballfragmentmvp.data.local.FootballlDatabase;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final FavoriteContract.View view;
    private FootballlDatabase footballlDatabase;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context context) {
        footballlDatabase = FootballlDatabase.getFootballlDatabase(context);
        if (footballlDatabase.footballDao().selectFavorite() != null){
               view.showDataList(footballlDatabase.footballDao().selectFavorite());
        }else{
            view.showFailureMessage("Tidak ada data di favorite");
        }
        view.hideRefresh();
    }
}