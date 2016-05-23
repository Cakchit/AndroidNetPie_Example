package netpie.library.example.rest.service;

import java.util.List;

import netpie.library.example.rest.model.ApiResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface NetPieService {

    @GET("/topic/{app_id}/{topic}")
    public void getData(
            @Path("app_id") String id,
            @Path("topic") String topic,
            @Query("auth") String key_secret,
            Callback<List<ApiResponse>> callback);

    @PUT("/topic/{app_id}/{topic}")
    public void subTopic(
            @Path("app_id") String id,
            @Path("topic") String topic,
            @Query("auth") String key_secret,
            Callback<ApiResponse> callback);

    @FormUrlEncoded
    @PUT("/topic/{app_id}/{topic}")
    public void pubTopic(
            @Path("app_id") String id,
            @Path("topic") String topic,
            @Query("retain") String retain,
            @Query("auth") String key_secret,
            @Field(value = "") String d,
            Callback<ApiResponse> callback);
}
