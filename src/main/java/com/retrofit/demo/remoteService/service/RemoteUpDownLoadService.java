package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.responseEntity.ResultEmpty;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

import java.util.List;


/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
public interface RemoteUpDownLoadService {
    @POST("upload")
    @Multipart
    @Streaming
    ResultEmpty upload(@Part MultipartBody.Part file);

    @POST("multiUpload")
    @Multipart
    @Streaming
    ResultEmpty multiUpload(@Part List<MultipartBody.Part> files);

    @POST("download")
    @FormUrlEncoded
    @Streaming
    Response<ResponseBody> download(@Field("file") String fileName);
}
