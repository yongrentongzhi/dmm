package com.dmm.file.service;



import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;

import java.io.File;
import java.io.InputStream;

public interface FileService {
    /**
     * 上传文件
     * @param  fileName 本地文件路径
     * @param  ext 文件扩展名
     * @return String[] fastDfs返回的 1:文件上传所存储的组名 2:文件存储路径
     */
    String[] upload(String fileName,String ext);


    /**
     * 获取文件信息
     * @param groupName 文件上传所存储的组名
     * @param remoteFileName 文件存储路径
     * @return FileInfo fastDFS 提供的封装文件信息的类
     */
    FileInfo getFileInfo(String groupName,String remoteFileName);


    /**
     * 文件删除
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    void deleteFile(String groupName,String remoteFileName);

    /**
     * 获取Tracker的url地址
     * @return url地址
     */
    String getTrackerUrl();
}
