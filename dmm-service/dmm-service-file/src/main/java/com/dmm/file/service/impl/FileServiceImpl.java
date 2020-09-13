package com.dmm.file.service.impl;


import com.dmm.file.service.FileService;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private StorageClient storageClient;
    @Autowired
    private TrackerClient trackerClient;

    @Override
    public String[] upload( String FileName, String ext) {
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(FileName, ext, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return new String[0];
    }


    @Override
    public FileInfo getFileInfo(String groupName, String remoteFileName) {
        FileInfo fileInfo = null;
        try {
            fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    @Override
    public void deleteFile(String groupName, String remoteFileName) {


        try {
            storageClient.delete_file(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTrackerUrl() {


        //创建trackerserver 对象
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tracker 的ip的信息
        String hostString = trackerServer.getInetSocketAddress().getHostString();

        //http://192.168.211.132:8080/group1/M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg img
        int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
        return "http://" + hostString + ":" + g_tracker_http_port;

    }


}
