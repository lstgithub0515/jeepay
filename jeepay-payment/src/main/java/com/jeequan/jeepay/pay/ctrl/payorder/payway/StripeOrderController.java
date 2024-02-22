package com.jeequan.jeepay.pay.ctrl.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.model.ApiRes;
import com.jeequan.jeepay.pay.ctrl.payorder.AbstractPayOrderController;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.AliBarOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.StripePcOrderRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lst
 * @Date: 2024/2/21
 **/
@Slf4j
@RestController
public class StripeOrderController extends AbstractPayOrderController {
    /**
     * 统一下单接口
     * **/
    @PostMapping("/api/pay/stripeOrder")
    public ApiRes aliBarOrder(){

        //获取参数 & 验证
        StripePcOrderRQ bizRQ = getRQByWithMchSign(StripePcOrderRQ.class);

        // 统一下单接口
        return unifiedOrder(CS.PAY_WAY_CODE.STRIPE_PC, bizRQ);

    }
}
