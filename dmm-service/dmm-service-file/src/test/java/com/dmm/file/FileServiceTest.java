package com.dmm.file;

import com.dmm.file.service.FileService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    public void uploadTest(){
        System.out.println(fileService.upload("C:\\Users\\Administrator\\Pictures\\iostream2xx.png", "png"));
    }

}


