package com.xiao.controller;

import com.xiao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiao on 2017/3/27.
 */

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/testMaster")
    public Integer test1() throws Exception {
        return testService.testService1();
    }

    @RequestMapping("/testSlave")
    public Integer test2() throws Exception {
        return testService.testService2();
    }
}
