package com.xiao.mapper;

import com.xiao.pojo.DO.OrderStatusDO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiao on 2017/3/27.
 */

public interface OrderMapper {

    /**
     * 添加订单
     * @param orderStatusDO
     * @return 成功返回1
     */
    int addOrder(@Param("orderStatusDO") OrderStatusDO orderStatusDO);
}

