package com.ziyata.footballfragmentmvp.ui.favorite;

import android.content.Context;

import com.ziyata.footballfragmentmvp.data.local.FootballlDatabase;
import com.ziyata.footballfragmentmvp.model.TeamsItem;

import java.util.ArrayList;
import java.util.List;

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
            List<TeamsItem> list = footballlDatabase.footballDao().selectFavorite();
            view.showDataList(list);
        }else{
            view.showFailureMessage("Tidak ada data di favorite");
        }
        view.hideRefresh();
    }

    @Override
    public void SearchTeams(Context context, String searchText) {
        if (!searchText.isEmpty()) {
            footballlDatabase = FootballlDatabase.getFootballlDatabase(context);
            // Memasukkan semua data favorite ke dalam variable list
            List<TeamsItem> teamsItemList = footballlDatabase.footballDao().selectFavorite();
            // Membuat penampung data yang sudah kita seleksi bedasarkan inputan user
            List<TeamsItem> mTeamsItemList = new ArrayList<>();

            if (teamsItemList != null) {

                // Melakukan perulangan untuk mengecek data yang ada di dalam table favorite
                for (TeamsItem data : teamsItemList) {

                    // Pengecekan team  bedasarkan dengan permintaan user
                    String namaTeam = data.getStrTeam().toLowerCase();

                    if (namaTeam.contains(searchText.toLowerCase())){

                        // Memasukkan team yang sama dengan inputan user ke dalam wadah ke dua
                        mTeamsItemList.add(data);
                    }

                }
                // Mengirimkan wadah ke dua ke view
                view.showDataList(mTeamsItemList);

            } else {
                // Apabila inputan user kosong ambil data tanpa keyword
                getDataListTeams(context);
            }
        }
    }
}

