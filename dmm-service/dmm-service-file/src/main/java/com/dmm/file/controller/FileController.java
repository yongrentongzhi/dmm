package com.dmm.file.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.file.service.FileService;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.TrackerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 返回 图片的全路径
     *
     * @param file 页面的文件对象
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file) {
        String[] upload = fileService.upload(file.getOriginalFilename(), StringUtils.getFilenameExtension(file.getOriginalFilename()));
        //  upload[0] group1
        //  upload[1] M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
        String path = fileService.getTrackerUrl() + "/" + upload[0] + "/" + upload[1];
        return new Result<String>(StatusCode.OK, true, "上传成功，返回文件路径", path);
    }

    @GetMapping(value = "/{groupName}/{remoteFileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FileInfo> FileInfo(@PathVariable("groupName") String groupName,
                                     @PathVariable("remoteFileName") String remoteFileName) {
        FileInfo fileInfo = fileService.getFileInfo(groupName,remoteFileName);
        return new Result<FileInfo>(StatusCode.OK,true,"获取文件信息",fileInfo);
    }

    @DeleteMapping(value = "/{groupName}/{remoteFileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result deleteFile(@PathVariable("groupName") String groupName,
                             @PathVariable("remoteFileName") String remoteFileName) {
        fileService.deleteFile(groupName, remoteFileName);
        return new Result(StatusCode.OK, true, "删除文件");
    }

}
