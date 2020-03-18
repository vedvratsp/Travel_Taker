package com.example.travel_taker.Login.Twitter.network;

import com.example.travel_taker.Login.Twitter.network.models.Oauth2Token;
import com.example.travel_taker.Login.Twitter.network.models.TweetStatuses;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TwitterApi {

    @POST("oauth2/token")
    @FormUrlEncoded
    Call<Oauth2Token> getAccessToken(@Header("Authorization") String authorization,
                                     @Field("grant_type") String grantType);

    @GET("1.1/search/tweets.json")
    Call<TweetStatuses> getStatuses(@Header("Authorization") String authorization,
                                    @Query("q") String query);

}
