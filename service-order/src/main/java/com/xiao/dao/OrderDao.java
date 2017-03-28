package com.xiao.dao;

import com.xiao.mapper.OrderMapper;
import com.xiao.pojo.DO.OrderStatusDO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiao on 2017/3/27.
 */
@Repository("orderDao")
public class OrderDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int addOrder(OrderStatusDO orderStatusDO) {
        return sqlSessionTemplate.insert(OrderMapper.class.getName() + ".addOrder", orderStatusDO);
    }
}
