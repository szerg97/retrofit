package com.szalai.adapter.service;

import com.google.gson.Gson;
import com.szalai.adapter.controller.FileParamsDto;
import com.szalai.adapter.controller.FileStatusDto;
import com.szalai.adapter.controller.HealthDto;
import com.szalai.adapter.model.Client;
import jakarta.annotation.PostConstruct;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class AdapterService {

    private Client client;

    @PostConstruct
    public void init(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        client = retrofit.create(Client.class);
    }

    public Response<HealthDto> getHealth() {
        try {
            return client.getHealth().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Response<FileStatusDto> createFile(FileParamsDto params) {
        try {
            String jsonContent = convertMapToJson(params.getParams()); // Implement this method
            RequestBody reqFile = RequestBody.create(MediaType.parse("text/plain"), jsonContent);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", "filename.txt", reqFile);
            return client.postFile(body).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertMapToJson(Map<String, Object> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
