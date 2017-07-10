/**   
* @Title: Post.java 
* @Package com.pay.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author （作者）  
* @date 2017年7月6日 下午2:05:41 
* @version V1.0   
*/
package com.pay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.allinpay.ets.client.SecurityUtil;

/**
 * @ClassName: Post
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author (作者)
 * @date 2017年7月6日 下午2:05:41
 * @version V1.0
 */
@Controller
public class Post
{

    @RequestMapping("/post")
    public ModelAndView indexPage(ModelMap model, HttpServletRequest request,
            HttpServletResponse resp)
    {

        String serverUrl = request.getParameter("serverUrl");
        request.setAttribute("serverUrl", serverUrl);
        String key = request.getParameter("key");
        request.setAttribute("key", key);
        String version = request.getParameter("version");
        request.setAttribute("version", version);
        String language = request.getParameter("language");
        request.setAttribute("language", language);
        String inputCharset = request.getParameter("inputCharset");
        request.setAttribute("inputCharset", inputCharset);
        String merchantId = request.getParameter("merchantId");
        request.setAttribute("merchantId", merchantId);
        String pickupUrl = request.getParameter("pickupUrl");
        request.setAttribute("pickupUrl", pickupUrl);
        String receiveUrl = request.getParameter("receiveUrl");
        request.setAttribute("receiveUrl", receiveUrl);

        String payType = request.getParameter("payType");
        request.setAttribute("payType", payType);

        String signType = request.getParameter("signType");
        request.setAttribute("signType", signType);
        String orderNo = request.getParameter("orderNo");
        request.setAttribute("orderNo", orderNo);
        String orderAmount = request.getParameter("orderAmount");
        request.setAttribute("orderAmount", orderAmount);

        String orderDatetime = request.getParameter("orderDatetime");
        request.setAttribute("orderDatetime", orderDatetime);
        String orderCurrency = request.getParameter("orderCurrency");
        request.setAttribute("orderCurrency", orderCurrency);
        String orderExpireDatetime = request
                .getParameter("orderExpireDatetime");
        request.setAttribute("orderExpireDatetime", orderExpireDatetime);

        String payerTelephone = request.getParameter("payerTelephone");
        request.setAttribute("payerTelephone", payerTelephone);
        String payerEmail = request.getParameter("payerEmail");
        request.setAttribute("payerEmail", payerEmail);
        String payerName = request.getParameter("payerName");
        request.setAttribute("payerName", payerName);
        String payerIDCard = request.getParameter("payerIDCard");

        String pid = request.getParameter("pid");
        request.setAttribute("pid", pid);
        String productName = request.getParameter("productName");
        request.setAttribute("productName", productName);
        String productId = request.getParameter("productId");
        request.setAttribute("productId", productId);
        String productNum = request.getParameter("productNum");
        request.setAttribute("productNum", productNum);
        String productPrice = request.getParameter("productPrice");
        request.setAttribute("productPrice", productPrice);
        String productDesc = request.getParameter("productDesc");
        request.setAttribute("productDesc", productDesc);
        String ext1 = request.getParameter("ext1");
        request.setAttribute("ext1", ext1);
        String ext2 = request.getParameter("ext2");
        request.setAttribute("ext2", ext2);
        String extTL = request.getParameter("extTL");// 通联商户拓展业务字段，在v2.2.0版本之后才使用到的，用于开通分账等业务
        request.setAttribute("extTL", extTL);
        String issuerId = request.getParameter("issuerId");
        request.setAttribute("issuerId", issuerId);
        String pan = request.getParameter("pan");

        String tradeNature = request.getParameter("tradeNature");
        request.setAttribute("tradeNature", tradeNature);
        String sign = "";

        // 若直连telpshx渠道，payerTelephone、payerName、payerIDCard、pan四个字段不可为空
        // 其中payerIDCard、pan需使用公钥加密（PKCS1格式）后进行Base64编码
        if (null != payerIDCard && !"".equals(payerIDCard))
        {
            try
            {
                // payerIDCard =
                // SecurityUtil.encryptByPublicKey("C:\\TLCert.cer",
                // payerIDCard);
                payerIDCard = SecurityUtil.encryptByPublicKey("/TLCert.cer",
                        payerIDCard);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if (null != pan && !"".equals(pan))
        {
            try
            {
                pan = SecurityUtil.encryptByPublicKey("/TLCert.cer", pan);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        request.setAttribute("pan", pan);
        request.setAttribute("payerIDCard", payerIDCard);

        // 构造订单请求对象，生成signMsg。
        com.allinpay.ets.client.RequestOrder requestOrder = new com.allinpay.ets.client.RequestOrder();
        if (null != inputCharset && !"".equals(inputCharset))
        {
            requestOrder.setInputCharset(Integer.parseInt(inputCharset));
        }
        requestOrder.setPickupUrl(pickupUrl);
        requestOrder.setReceiveUrl(receiveUrl);
        requestOrder.setVersion(version);
        if (null != language && !"".equals(language))
        {
            requestOrder.setLanguage(Integer.parseInt(language));
        }
        requestOrder.setSignType(Integer.parseInt(signType));
        requestOrder.setPayType(Integer.parseInt(payType));
        requestOrder.setIssuerId(issuerId);
        requestOrder.setMerchantId(merchantId);
        requestOrder.setPayerName(payerName);
        requestOrder.setPayerEmail(payerEmail);
        requestOrder.setPayerTelephone(payerTelephone);
        requestOrder.setPayerIDCard(payerIDCard);
        requestOrder.setPid(pid);
        requestOrder.setOrderNo(orderNo);
        requestOrder.setOrderAmount(Long.parseLong(orderAmount));
        requestOrder.setOrderCurrency(orderCurrency);
        requestOrder.setOrderDatetime(orderDatetime);
        requestOrder.setOrderExpireDatetime(orderExpireDatetime);
        requestOrder.setProductName(productName);
        if (null != productPrice && !"".equals(productPrice))
        {
            requestOrder.setProductPrice(Long.parseLong(productPrice));
        }
        if (null != productNum && !"".equals(productNum))
        {
            requestOrder.setProductNum(Integer.parseInt(productNum));
        }
        requestOrder.setProductId(productId);
        requestOrder.setProductDesc(productDesc);
        requestOrder.setExt1(ext1);
        requestOrder.setExt2(ext2);
        requestOrder.setExtTL(extTL);// 通联商户拓展业务字段，在v2.2.0版本之后才使用到的，用于开通分账等业务
        requestOrder.setPan(pan);
        requestOrder.setTradeNature(tradeNature);
        requestOrder.setKey(key); // key为MD5密钥，密钥是在通联支付网关会员服务网站上设置。

        String strSrcMsg = requestOrder.getSrc(); // 此方法用于debug，测试通过后可注释。
        String strSignMsg = requestOrder.doSign(); // 签名，设为signMsg字段值。

        request.setAttribute("strSrcMsg", strSrcMsg);
        request.setAttribute("strSignMsg", strSignMsg);

        ModelAndView mv = new ModelAndView("post");
        return mv;
    }

}
