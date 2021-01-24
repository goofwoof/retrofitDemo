package com.retrofit.demo.service;

import com.retrofit.demo.remoteService.dao.User;
import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.remoteService.service.RemoteUpDownLoadService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoCallService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoPoolService;
import com.retrofit.demo.remoteService.service.RemoteUserInfoService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author puthlive
 */
@Service
public class UserInfoService {
    @Autowired
    private RemoteUserInfoService remoteUserInfoService;
    @Autowired
    private RemoteUserInfoCallService remoteUserInfoCallService;
    @Autowired
    private RemoteUserInfoPoolService remoteUserInfoPoolService;
    @Autowired
    private RemoteUpDownLoadService remoteUpDownLoadService;


    public User getUserGET(String id) {
        return remoteUserInfoService.getUserGET(id).getData();
    }

    public User getUserPOST(String id) {
        return remoteUserInfoService.getUserPOST(id).getData();
    }

    public Result<Object> deleteUser(String id) {
        return remoteUserInfoService.deleteUser(id);
    }

    public User getUserRetryFail(String id) {
        return remoteUserInfoService.getUserRetryFail(id).getData();
    }

    public User getUserRetry(String id) {
        return remoteUserInfoService.getUserRetry(id).getData();
    }

    public User getUserInfoPool(String id) {
        return remoteUserInfoPoolService.getUserInfoPool(id).getData();
    }

    public User getUserDegrade(String id) {
        return remoteUserInfoService.getUserDegrade(id).getData();
    }

    public User getUserByCall(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }

    public User getUserNeedHeaders(String id) throws IOException {
        return remoteUserInfoCallService.getUser(id).execute().body().getData();
    }

    public Object upload(MultipartFile file) throws IOException {
        MultipartBody.Part part = fileTransform(file);
        return remoteUpDownLoadService.upload(part);
    }

    public ResponseBody download(String file) {
        return remoteUpDownLoadService.download(file).body();
    }

    public Object upload(List<MultipartFile> files) {
        List<MultipartBody.Part> fileList = new ArrayList<>();
        files.forEach(multipartFile -> {
            try {
                fileList.add(fileTransform(multipartFile));
            } catch (IOException ignored) {
            }
        });
        return remoteUpDownLoadService.multiUpload(fileList);
    }

    private MultipartBody.Part fileTransform(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.parse("multipart/form-data"),file.getBytes());
        return MultipartBody.Part.createFormData("file", fileName, requestBody);
    }

    public String getDownloadKey(String file) {
        return remoteUpDownLoadService.getDownloadKey(file).body();
    }
}
