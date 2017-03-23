package com.xiao.controller;

import com.xiao.base.BaseController;
import com.xiao.common.BaseRespInfo;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiao on 2017/3/23.
 */

@RequestMapping("/order")
public class OrderController extends BaseController {

    /**
     *  创建订单
     * @return
     */
    @RequestMapping("/createOrder")
    public BaseRespInfo createOrder() {
        return new BaseRespInfo();
    }

}
