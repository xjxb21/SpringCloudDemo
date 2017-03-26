package com.xiao.service;

import com.xiao.common.sequence.ZookeeperSeqGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xiao on 2017/3/23.
 */

@Service("orderService")
public class OrderService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取订单ID号
     * @return
     */
    public String getOrderId() {
        ZookeeperSeqGenerator generator = ZookeeperSeqGenerator.getInstance();
        Long waiteTime = 500L;
        return generator.getSeqId(waiteTime);
    }
}
