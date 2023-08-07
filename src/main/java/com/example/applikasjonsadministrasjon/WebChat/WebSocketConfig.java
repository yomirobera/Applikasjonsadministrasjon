package com.example.applikasjonsadministrasjon.WebChat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  

    @Override
public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/queue"); // Enables a broker to send messages to queues
    config.setApplicationDestinationPrefixes("/app");
    config.setUserDestinationPrefix("/user"); // This line allows using user destination prefixes
}

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/websocket-chat").setAllowedOrigins("http://localhost:3000", "http://localhost:8080").withSockJS();

    }

}
