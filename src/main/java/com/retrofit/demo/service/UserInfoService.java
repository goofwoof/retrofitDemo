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
import java.util.concurrent.ExecutionException;

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

    /**
     * TYPE1: METHOD-GET
     * @param id
     * @return
     */
    public User getUserGET(String id) {
        return remoteUserInfoService.getUserGET(id).getData();
    }

    /**
     * TYPE1: METHOD-POST
     * @param id
     * @return
     */
    public User getUserPOST(String id) {
        return remoteUserInfoService.getUserPOST(id).getData();
    }

    /**
     * TYPE1: METHOD-DELETE
     * @param id
     * @return
     */
    public Result<Object> deleteUser(String id) {
        return remoteUserInfoService.deleteUser(id);
    }

    /**
     * TYPE2: HEADERS/COOKIES
     * @param id
     * @return
     * @throws IOException
     */
    public User getUserNeedHeaders(String id) throws IOException {
        return remoteUserInfoCallService.getUserByCall(id).execute().body().getData();
    }

    /**
     * TYPE3: RETRY-FAILURE
     * @param id
     * @return
     */
    public User getUserRetryFail(String id) {
        return remoteUserInfoService.getUserRetryFail(id).getData();
    }

    /**
     * TYPE3: RETRY-SUCCESS
     * @param id
     * @return
     */
    public User getUserRetry(String id) {
        return remoteUserInfoService.getUserRetry(id).getData();
    }

    /**
     * TYPE4: POOL
     * @param id
     * @return
     */
    public User getUserInfoPool(String id) {
        return remoteUserInfoPoolService.getUserInfoPool(id).getData();
    }

    /**
     * TYPE4: POOL-CompletableFuture
     * @param id
     * @return
     */
    public User getUserInfoPoolAsync(String id) throws ExecutionException, InterruptedException {
        return remoteUserInfoPoolService.getUserInfoPoolAsync(id).get().getData();
    }

    /**
     * TYPE5: DEGRADE
     * @param id
     * @return
     */
    public User getUserDegrade(String id) {
        return remoteUserInfoService.getUserDegrade(id).getData();
    }

    /**
     * TYPE6: ASYNC-ASYNC
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public User getUserAsync(String id) throws ExecutionException, InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            remoteUserInfoPoolService.getUserAsync(id+i);
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
        return remoteUserInfoPoolService.getUserAsync(id+"a").get().getData();
    }

    /**
     * TYPE6: ASYNC-SYNC-POOL
     * @param id
     * @return
     */
    public User getUserSync(String id) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            remoteUserInfoPoolService.getUserInfoPool(id+i);
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
        return remoteUserInfoPoolService.getUserInfoPool(id+"a").getData();
    }

    /**
     * TYPE6: ASYNC-SYNC
     * @param id
     * @return
     */
    public User getUserInfoSyncNoPool(String id) {
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            remoteUserInfoService.getUserGET(id+i);
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
        return remoteUserInfoService.getUserGET(id+"a").getData();
    }

    /**
     * TYPE7: UPLOAD-OFO
     * @param file
     * @return
     * @throws IOException
     */
    public Object upload(MultipartFile file) throws IOException {
        MultipartBody.Part part = fileTransform(file);
        return remoteUpDownLoadService.upload(part);
    }

    /**
     * TYPE7: UPLOAD-OFM
     * @param files
     * @return
     */
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


    /**
     * TYPE7: DOWNLOAD
     * @param file
     * @return
     */
    public ResponseBody download(String file) {
        return remoteUpDownLoadService.download(file).body();
    }
}
