package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * none.
 *
 * @author 陈泉
 * @package com.jeequan.jeepay.pay.rqrs.payorder.payway
 * @create 2021/11/15 17:52
 */
@Data
public class StripePcOrderRQ extends CommonPayDataRQ {

    /**
     * 商品描述信息
     **/
    @NotBlank(message = "取消支付返回站点")
    private String cancelUrl;

     @NotBlank(message = "priceId信息不能为空")
     private String priceId;
    //
    // @NotBlank(message = "商品数量信息不能为空")
    // private Long number;

    public StripePcOrderRQ() {
        this.setWayCode(CS.PAY_WAY_CODE.STRIPE_PC);
    }
}
