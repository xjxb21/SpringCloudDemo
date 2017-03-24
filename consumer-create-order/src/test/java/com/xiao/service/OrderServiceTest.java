package com.xiao.service;

import com.xiao.common.sequence.ZookeeperSeqGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiao on 2017/3/23.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    ZookeeperSeqGenerator generator;

    @Before
    public void initialize() {
        this.generator = ZookeeperSeqGenerator.getInstance();
    }

    @Test
    public void getOrderId() {
        System.out.println(">>>>>>>>>>>>>>>>>>test getOrderId>>>>>>>>>>>>>>>>>>>>");

        //调用 非并发
        for (int i = 0; i < 30; i++) {
            ZookeeperSeqGenerator generator = ZookeeperSeqGenerator.getInstance();
            String seqId = generator.getSeqId(50L);
            System.out.println("------- " + seqId);
        }
    }

}