package com.retrofit.demo.api;


import com.retrofit.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class UpDownloadAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpDownloadAPI.class);
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件-retrofit Demo";
        }
        return userInfoService.upload(file);
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public Object multiUpload(MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> files = request.getFiles("file");
        return userInfoService.upload(files);
    }
}
