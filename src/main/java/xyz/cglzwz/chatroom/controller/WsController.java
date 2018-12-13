package xyz.cglzwz.chatroom.controller;

        import org.apache.commons.logging.Log;
        import org.apache.commons.logging.LogFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.messaging.handler.annotation.MessageMapping;
        import org.springframework.messaging.handler.annotation.SendTo;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import xyz.cglzwz.chatroom.domain.WiselyMessage;
        import xyz.cglzwz.chatroom.domain.WiselyResponse;

/**
 * 演示控制器
 *
 * @author chgl16
 * @date 2018-12-13 16:07
 * @version 1.0
 */

@Controller
public class WsController {
    private static final Log log =LogFactory.getLog(WsController.class);

    @Autowired
    private WiselyResponse wiselyResponse;

    /**
     * 1.当浏览器向服务器发送请求时，通过@MessageMapping映射
     * 2.当服务器有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
     *
     * @param wiselyMessage
     * @return
     * @throws Exception
     */
    @MessageMapping(value = "/broadcast")
    @SendTo(value = "/topic/getResponse")
    public WiselyResponse say(WiselyMessage wiselyMessage) throws Exception {
        // 等待三秒才响应
//        Thread.sleep(3000);
        // 给全网响应广播内容
        wiselyResponse.setResponseMessage("广播：" + wiselyMessage.getMessage());
        log.info("广播内容: " + wiselyMessage.getMessage());
        return wiselyResponse;
    }

    /**
     * 视图解析映射
     *
     * @return
     */
    @RequestMapping("/")
    public String toWs() {
        return "ws";
    }

}
