package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.retrofit.demo.remoteService.responseEntity.ResultEmpty;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

import java.util.Map;


/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
public interface RemoteUpDownLoadService {
    @POST("upload")
    @Multipart
    ResultEmpty upload(@Part MultipartBody.Part file);

    @POST("multiUpload")
    @Multipart
    ResultEmpty multiUpload(@PartMap Map<String, MultipartBody.Part> files);
}
