package com.retrofit.demo.remoteService.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.degrade.Degrade;
import com.github.lianjiatech.retrofit.spring.boot.degrade.DegradeStrategy;
import com.github.lianjiatech.retrofit.spring.boot.retry.Retry;
import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.service.request.QueryReq;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author puthlive
 */
@RetrofitClient(baseUrl = "${user.info.url}")
public interface RemoteUserInfoService {
    @GET(value = "getUserInfo")
    Result<User> getUserGET(@Query("id") String id);

    @POST(value = "getUserInfo")
    Result<User> getUserPOST(@Body QueryReq req);

    @POST(value = "getUserPOSTField")
    @FormUrlEncoded
    Result<User> getUserPOSTField(@Field("id") String id);

    @POST(value = "getUserPOSTFieldMap")
    @FormUrlEncoded
    Result<User> getUserPOSTFieldMap(@FieldMap Map<String, Object> map);

    @DELETE(value = "deleteUser/{id}")
    Result<Object> deleteUser(@Path("id") String id);

    @POST(value = "getUserInfoRetry")
    @Retry(maxRetries = 3)
    Result<User> getUserRetryFail(@Query("id") String id);

    @POST(value = "getUserInfoRetry")
    @Retry(maxRetries = 10)
    Result<User> getUserRetry(@Query("id") String id);

    @POST(value = "getUserDegrade")
    @Retry(maxRetries = 10)
    @Degrade(count = 2, timeWindow = 10, degradeStrategy = DegradeStrategy.AVERAGE_RT)
    Result<User> getUserDegrade(@Query("id") String id);
}
