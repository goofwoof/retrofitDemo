package com.retrofit.demo.api;


import com.retrofit.demo.remoteService.responseEntity.Result;
import com.retrofit.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 文件上传下载
 * @author puthlive
 */
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
    public Object multiUpload(MultipartHttpServletRequest request) {
        List<MultipartFile> files = request.getFiles("file");
        return userInfoService.upload(files);
    }

    @PostMapping("/download")
    public Object download(@RequestParam("file") String file, HttpServletResponse response) {
        okhttp3.ResponseBody download = userInfoService.download(file);

        response.reset();
        response.setContentType(download.contentType().type());
        response.setContentLength((int) download.contentLength());
        response.setHeader("Content-Disposition", "attachment;filename=" + file);
        try(BufferedInputStream bis = new BufferedInputStream(download.byteStream())) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return Result.builder().code(9299398).msg("fail download file").build();
        }
        return null;
    }

    @GetMapping("/download")
    public Object downloadGet(@RequestParam("file") String file) {
        return userInfoService.download(file);
    }
}
