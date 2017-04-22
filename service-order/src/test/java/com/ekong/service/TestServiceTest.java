package com.ekong.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiao on 2017/3/28.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestServiceTest {

    @Autowired
    TestService service;

    @Test
    public void testService1() throws Exception {
    }

    @Test
    public void testService2() throws Exception {
        service.testService2();
    }

}