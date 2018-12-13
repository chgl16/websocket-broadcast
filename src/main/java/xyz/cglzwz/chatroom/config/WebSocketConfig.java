package xyz.cglzwz.chatroom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置WebSocket
 * 注意：AbstractWebSocketMessageBrokerConfigurer已经过时
 * # 通过@EnableWebSocketMessageBroker注解开启STOMP子协议来传输基于代理(Message broker)的消息，
 *   这时控制器支持使用@MessageMapper,就像使用@RequestMapper一样映射
 * 现在在用的接口是WebSocketMessageBrokerConfigurer,这个接口定义的方法带了default可以不实现
 *
 * @author chgl16
 * @date 2018-12-13 15:36
 * @version 1.0
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册STOMP协议的节点endpoint
     * 并映射到指定的URL
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointWisely").withSockJS();
    }

    /**
     * 配置消息代理
     * 不实现也可以，对应的是客户端订阅，在控制器的@SendTo中声明即可
     *
     * @param registry
     */
    /*
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 广播式应配置一个/topic消息代理,
        registry.enableSimpleBroker("/topic");
    }
    */
}
