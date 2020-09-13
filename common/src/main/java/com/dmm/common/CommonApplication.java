package com.dmm.common;

import com.dmm.common.uid.impl.CachedUidGenerator;
import com.dmm.common.uid.worker.DisposableWorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

@MapperScan(basePackages = "com/dmm/common/uid/worker/dao")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }


}
