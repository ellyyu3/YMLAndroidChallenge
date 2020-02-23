package com.example.jinliyu.ymlandroidchallenge.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Network class to pass base_url and return retrofit instance
 *
 */
public class RetrofitInstance {

    private static final String BASE_URL = "https://api.github.com";

    private static Retrofit retrofit = null;

    /**
     *
     *
     * @return retrofit instance
     */

    public static Retrofit getRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }


        return retrofit;
    }

}
