package com.dqj.fakeithomes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DoRequset {
@GET("news")
    Call<bean> getCall();

}

