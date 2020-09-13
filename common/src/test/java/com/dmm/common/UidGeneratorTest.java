package com.dmm.common;

import com.dmm.common.uid.UidGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UidGeneratorTest {
    @Autowired
    private UidGenerator uidGenerator;

    @Test
    public void getUid(){
        System.out.println(uidGenerator.getUID());
    }

}
