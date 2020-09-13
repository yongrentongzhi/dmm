package com.dmm.goods.feign;

import com.dmm.common.pojo.Result;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(value = "file")
public interface FileFeign {
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file);

    @GetMapping(value = "/{groupName}/{remoteFileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FileInfo> FileInfo(@PathVariable("groupName") String groupName,
                                     @PathVariable("remoteFileName") String remoteFileName);

    @DeleteMapping(value = "/{groupName}/{remoteFileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteFile(@PathVariable("groupName") String groupName,
                             @PathVariable("remoteFileName") String remoteFileName) ;


}
