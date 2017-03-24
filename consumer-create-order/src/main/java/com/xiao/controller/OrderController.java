package com.xiao.controller;

import com.xiao.base.BaseController;
import com.xiao.common.BaseRespInfo;
import com.xiao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiao on 2017/3/23.
 */

@RequestMapping("/order")
@RestController
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    /**
     *  创建订单
     * @return
     */
    @RequestMapping("/createOrder")
    public BaseRespInfo createOrder() {
        String orderId = orderService.getOrderId();
        BaseRespInfo respInfo = new BaseRespInfo();
        respInfo.setObject(orderId);
        return respInfo;
    }

}
