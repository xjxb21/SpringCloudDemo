package com.ekong.service;

import com.ekong.common.sequence.ZookeeperSeqGenerator;
import org.springframework.stereotype.Service;

/**
 * Created by xiao on 2017/3/23.
 */

@Service("orderService")
public class OrderService {

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
