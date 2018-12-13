package xyz.cglzwz.chatroom.domain;

import org.springframework.stereotype.Component;

/**
 * 服务端向浏览器发送的此类消息
 *
 * @author chgl16
 * @date 2018-12-13 16:04
 * @version 1.0
 */

@Component
public class WiselyResponse {
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
