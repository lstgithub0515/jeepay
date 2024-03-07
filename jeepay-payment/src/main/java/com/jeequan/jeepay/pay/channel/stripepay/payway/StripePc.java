package com.jeequan.jeepay.pay.channel.stripepay.payway;

import cn.hutool.json.JSONUtil;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.utils.AmountUtil;
import com.jeequan.jeepay.pay.channel.pppay.PppayPaymentService;
import com.jeequan.jeepay.pay.channel.stripepay.StripepayPaymentService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.model.PaypalWrapper;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.PPPcOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.PPPcOrderRS;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.StripePcOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.StripePcOrderRS;
import com.jeequan.jeepay.pay.util.ApiResBuilder;
import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.http.serializer.Json;
import com.paypal.orders.*;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * none.
 *
 * @author 陈泉
 * @package com.jeequan.jeepay.pay.channel.pppay.payway
 * @create 2021/11/15 18:59
 */
@Slf4j
@Service("stripepayPaymentByStripePCService")
public class StripePc extends StripepayPaymentService {
    @Override
    public String preCheck(UnifiedOrderRQ bizRQ, PayOrder payOrder) {
        return null;
    }

    /*Stripe.apiKey = "sk_test_51ObwshJ9BMMXaWbIPK4e2FUC2lGaQhndVjh0TRwBFrhOrySkF8njuiTCgOCXqstuvIFu1cQSZUScWHhCVkR7Y1PK00k6KpPzP9";
    Map<String, String> resultMap = new HashMap<>();

    String successUrl = "http://localhost:4242/success.html";
    //String successUrl = "https://buy.stripe.com/test_9AQdUD5Kl9DGaEE000";
    String failUrl = "http://localhost:4242/cancel.html";
    //String failUrl = "https://buy.stripe.com/test_9AQdUD5Kl9DGaEE000";

    SessionCreateParams params =
            SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)  // 订阅模式
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(failUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(number)
                                    .setPrice(priceId)
                                    .build())
                    .build();
    Session session = Session.create(params);

    String sessionId = session.getId();
    //log.info("sessionId :{}",session.getId());

    String url = session.getUrl();  // 结账界面
        System.out.println("结账界面：" + url);

    //resultMap.put("sessionId",sessionId);
        return url;*/
    @Override
    public AbstractRS pay(UnifiedOrderRQ rq, PayOrder payOrder, MchAppConfigContext mchAppConfigContext) throws
            Exception {
        StripePcOrderRQ bizRQ = (StripePcOrderRQ) rq;

        Stripe.apiKey = "sk_test_51ObwshJ9BMMXaWbIPK4e2FUC2lGaQhndVjh0TRwBFrhOrySkF8njuiTCgOCXqstuvIFu1cQSZUScWHhCVkR7Y1PK00k6KpPzP9";
        // Map<String, String> resultMap = new HashMap<>();
        //
        String successUrl = "http://localhost:4242/success.html";
        //String successUrl = "https://buy.stripe.com/test_9AQdUD5Kl9DGaEE000";
        String failUrl = "http://localhost:4242/cancel.html";
        //String failUrl = "https://buy.stripe.com/test_9AQdUD5Kl9DGaEE000";
        //
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)  // 订阅模式
                        .setSuccessUrl(successUrl)
                        .setCancelUrl(failUrl)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPrice(bizRQ.getPriceId())
                                        .build())
                        .build();
        Session session = Session.create(params);

        String sessionId = session.getId();
        //log.info("sessionId :{}",session.getId());

        String url = session.getUrl();  // 结账界面
        //
        // //resultMap.put("sessionId",sessionId);
        //     return url;

        // 构造函数响应数据
        StripePcOrderRS res = ApiResBuilder.buildSuccess(StripePcOrderRS.class);
        ChannelRetMsg channelRetMsg = new ChannelRetMsg();
        res.setPayUrl(url);

        res.setChannelRetMsg(channelRetMsg);
        return res;
    }
}
