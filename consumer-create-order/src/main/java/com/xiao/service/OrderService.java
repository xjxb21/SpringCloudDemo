package com.xiao.service;

import com.xiao.common.sequence.ZookeeperAbstractSeqGenerator;
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
        ZookeeperAbstractSeqGenerator generator = ZookeeperAbstractSeqGenerator.getInstance();
        Long waiteTime = 50L;
        return generator.getSeqId(waiteTime);
    }
}
