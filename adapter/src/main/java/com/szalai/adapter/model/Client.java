package com.szalai.adapter.model;

import com.szalai.adapter.controller.FileStatusDto;
import com.szalai.adapter.controller.HealthDto;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface Client {

    @GET("api/v1/client/health")
    Call<HealthDto> getHealth();

    @Multipart
    @POST("api/v1/client/file")
    Call<FileStatusDto> postFile(@Part MultipartBody.Part file);
}
