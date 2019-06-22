package com.example.hulyquran.API;

import com.example.hulyquran.API.ModelRadioChanels.RadioResponse;
import com.example.hulyquran.API.RecietersVoice.RecietersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {



    @GET("radio/radio_ar.json")
    Call<RadioResponse> getAllRAdioResponse();

    @GET("verse/radio_ar.json")
    Call<RecietersResponse> getAllRecietersResponse();


}
