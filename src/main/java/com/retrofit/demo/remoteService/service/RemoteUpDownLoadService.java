package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.responseEntity.ResultEmpty;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.*;

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

    @POST("getDownloadKey")
    @Streaming
    Response<String> getDownloadKey(String fileName);
}
