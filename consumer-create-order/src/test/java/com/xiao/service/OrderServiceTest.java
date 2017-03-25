package com.xiao.service;

import com.xiao.common.sequence.ZookeeperAbstractSeqGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiao on 2017/3/23.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    ZookeeperAbstractSeqGenerator generator;

    @Before
    public void initialize() {
        this.generator = ZookeeperAbstractSeqGenerator.getInstance();
    }

    @Test
    public void getOrderId() {
        System.out.println(">>>>>>>>>>>>>>>>>>test getOrderId>>>>>>>>>>>>>>>>>>>>");

        //调用 非并发
        for (int i = 0; i < 30; i++) {
            ZookeeperAbstractSeqGenerator generator = ZookeeperAbstractSeqGenerator.getInstance();
            String seqId = generator.getSeqId(50L);
            System.out.println("------- " + seqId);
        }
    }

}