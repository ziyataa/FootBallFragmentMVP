package com.ziyata.footballfragmentmvp.ui.teams;

import com.ziyata.footballfragmentmvp.data.remote.ApiClient;
import com.ziyata.footballfragmentmvp.data.remote.ApiInterface;
import com.ziyata.footballfragmentmvp.model.TeamsResponse;
import com.ziyata.footballfragmentmvp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsPresenter implements TeamsContract.Presenter {

    private final TeamsContract.View view;
    private ApiInterface apiInterface = ApiClient.getCliend().create(ApiInterface.class);

    public TeamsPresenter(TeamsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListItem() {
        view.showProgress();
        Call<TeamsResponse> call = apiInterface.getAllTeams(Constants.S, Constants.C);
        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                view.hideProgress();
                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else{
                    view.showFailureMessage("Data Kosong");
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getSearchTeams(String searchText) {
        // Mengecek apakah inputan user ada isinya atau tidak
        if (!searchText.isEmpty()){
            // apabila ada lakukan request ke API
            view.showProgress();
            Call<TeamsResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamsResponse>() {
                @Override
                public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                    view.hideProgress();
                    if (response.body() != null){
                        if (response.body().getTeams() != null){
                            view.showDataList(response.body().getTeams());
                        }else {
                            view.showFailureMessage("Team tidak ada");
                        }
                    }
                }

                @Override
                public void onFailure(Call<TeamsResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });
        }else {
            // Apabila kosong maka lakukan pengambilan data team tanpa search
            getDataListItem();
        }
    }
}
