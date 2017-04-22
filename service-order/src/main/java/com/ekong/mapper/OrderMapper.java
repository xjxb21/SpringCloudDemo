package com.ekong.mapper;

import com.ekong.pojo.DO.OrderStatusDO;

/**
 * Created by xiao on 2017/3/27.
 */

public interface OrderMapper {

    /**
     * 添加订单
     * @param orderStatusDO
     * @return 成功返回1
     */
    int addOrder(OrderStatusDO orderStatusDO);
}

