package com.jeequan.jeepay.pay.channel.stripepay;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.exception.ResponseException;
import com.jeequan.jeepay.pay.channel.AbstractChannelNoticeService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.LineItemCollection;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionListLineItemsParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * none.
 *
 * @author 陈泉
 * @package com.jeequan.jeepay.pay.channel.pppay
 * @create 2021/11/15 20:58
 */
@Service
@Slf4j
public class StripepayChannelNoticeService extends AbstractChannelNoticeService {
    @Override
    public String getIfCode() {
        return CS.IF_CODE.STRIPEPAY;
    }

    @Override
    public MutablePair<String, Object> parseParams(HttpServletRequest request, String urlOrderId,
                                                   NoticeTypeEnum noticeTypeEnum) {
        // 同步和异步需要不同的解析方案
        // 异步需要从 webhook 中读取，所以这里读取方式不太一样
        if (noticeTypeEnum == NoticeTypeEnum.DO_NOTIFY) {
            JSONObject params = JSONUtil.parseObj(getReqParamJSON().toJSONString());
            String orderId = params.getByPath("resource.purchase_units[0].invoice_id", String.class);
            return MutablePair.of(orderId, params);
        } else {
            if (urlOrderId == null || urlOrderId.isEmpty()) {
                throw ResponseException.buildText("ERROR");
            }
            try {
                JSONObject params = JSONUtil.parseObj(getReqParamJSON().toString());
                return MutablePair.of(urlOrderId, params);
            } catch (Exception e) {
                log.error("error", e);
                throw ResponseException.buildText("ERROR");
            }
        }
    }

    @Override
    public ChannelRetMsg doNotice(HttpServletRequest request, Object params, PayOrder payOrder,
                                  MchAppConfigContext mchAppConfigContext, NoticeTypeEnum noticeTypeEnum) {
        try {
            if (noticeTypeEnum == NoticeTypeEnum.DO_RETURN) {
                return doReturn(request, params, payOrder, mchAppConfigContext);
            }
            return doNotify(request, params, payOrder, mchAppConfigContext);
        } catch (Exception e) {
            log.error("error", e);
            throw ResponseException.buildText("ERROR");
        }
    }

    public ChannelRetMsg doReturn(HttpServletRequest request, Object params, PayOrder payOrder,
                                  MchAppConfigContext mchAppConfigContext) throws IOException {
        JSONObject object = (JSONObject) params;
        // 获取 Paypal 订单 ID
        String ppOrderId = object.getStr("token");
        // 统一处理订单
        return mchAppConfigContext.getPaypalWrapper().processOrder(ppOrderId, payOrder);
    }

    public ChannelRetMsg doNotify(HttpServletRequest request, Object params, PayOrder payOrder,
                                  MchAppConfigContext mchAppConfigContext) throws IOException {
        JSONObject object = (JSONObject) params;
        // 获取 Paypal 订单 ID
        String ppOrderId = object.getByPath("resource.id", String.class);
        // 统一处理订单
        return mchAppConfigContext.getPaypalWrapper().processOrder(ppOrderId, payOrder, true);
    }
}
//     @PostMapping("/webhook")
//     public Event getEvent(Request request, Response response) throws StripeException {
//         return handle(request, response);
//     }
//
//     public Event handle(Request request, Response response) throws StripeException {
//         Stripe.apiKey = "sk_test_51ObwshJ9BMMXaWbIPK4e2FUC2lGaQhndVjh0TRwBFrhOrySkF8njuiTCgOCXqstuvIFu1cQSZUScWHhCVkR7Y1PK00k6KpPzP9";
//         String payload = request.body();
//         String sigHeader = request.headers("Stripe-Signature");
//         Event event = null;
//
//         try {
//             event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
//         } catch (Exception e) {
//             // Invalid payload
//             response.status(400);
//             return event;
//         } /*catch (SignatureVerificationException e) {
//             // Invalid signature
//             response.status(400);
//             return "";
//         }*/
//
//         // 检查事件状态
//         if (StripeEventType.PAYMENT_INTENT_SUCCEEDED.getEventType().equals(event.getType())) {
//             Optional<StripeObject> object = event.getDataObjectDeserializer().getObject();
//             if (!object.isPresent()){
//                 response.status(400);
//                 return event;
//             }
//
//             Session sessionEvent= (Session) object.get();
//             SessionRetrieveParams params =
//                     SessionRetrieveParams.builder()
//                             .addExpand("line_items")
//                             .build();
//
//             Session session = Session.retrieve(sessionEvent.getId(), params, null);
//
//             SessionListLineItemsParams listLineItemsParams =
//                     SessionListLineItemsParams.builder()
//                             .build();
//
//             // Retrieve the session. If you require line items in the response, you may include them by expanding line_items.
//             LineItemCollection lineItems = session.listLineItems(listLineItemsParams);
//             // Fulfill the purchase...
//             fulfillOrder(lineItems);
//
//             System.out.println("事件内容：" + event.getData());
//         }
//
// //        switch (event.getType()) {
// //            case "payment_intent.succeeded":
// //                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
// //                // Then define and call a method to handle the successful payment intent.
// //                // handlePaymentIntentSucceeded(paymentIntent);
// //                break;
// //            case "payment_method.attached":
// //                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
// //                // Then define and call a method to handle the successful attachment of a PaymentMethod.
// //                // handlePaymentMethodAttached(paymentMethod);
// //                break;
// //            // ... handle other event types
// //            default:
// //                System.out.println("Unhandled event type: " + event.getType());
// //        }
//
//         response.status(200);
//         return event;
//     }