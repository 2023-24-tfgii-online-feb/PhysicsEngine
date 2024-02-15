package com.dmm.tfg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for setting up WebSocket communication within the application.
 * Enables WebSocket message brokering through STOMP (Simple Text Oriented Messaging Protocol).
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker, setting application destination prefixes and enabling a simple
     * broker to carry the messages back to the client on destinations prefixed with "/topic".
     * Also sets the prefix for filtering destinations targeted directly to users based on their username with "/user".
     *
     * @param configurer The {@link MessageBrokerRegistry} to configure.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry configurer) {
        configurer.setApplicationDestinationPrefixes("/app");
        configurer.enableSimpleBroker("/topic");
    }


    /**
     * Registers STOMP endpoints mapping each to a specific URL and enabling SockJS fallback options.
     * SockJS is used to enable fallback options for browsers that don’t support WebSocket.
     *
     * @param registry The {@link StompEndpointRegistry} to register the STOMP endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/physics-engine-websocket")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
