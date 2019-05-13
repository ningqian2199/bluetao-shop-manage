package com.bluetao.weixin.handler;

import com.bluetao.utils.RedisUtils;
import com.bluetao.utils.RegexUtils;
import com.bluetao.weixin.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class MsgHandler extends AbstractHandler {


    private String rtnContent = "你是个小傻瓜";

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList()
                    .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                        .fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }


        //TODO 组装回复消息
        String msg = wxMessage.getContent();
        log.info("接受客户端消息，msg:{}",msg);
        boolean flag = RegexUtils.checkMobile(msg);
        if(flag){

            int random =  getRandom();
            redisUtils.setEx(msg,random+"",2000, TimeUnit.SECONDS);
            rtnContent= random+"";
        }

        return new TextBuilder().build(rtnContent, wxMessage, weixinService);



    }

    private int getRandom() {
        return (int) (Math.random()*100000);
    }

}
