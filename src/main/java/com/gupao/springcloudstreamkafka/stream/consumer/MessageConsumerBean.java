package com.gupao.springcloudstreamkafka.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/9/24 13:06
 **/
@Component
@EnableBinding(Sink.class)
public class MessageConsumerBean {
    @Autowired
    @Qualifier(Sink.INPUT)
    SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    //第一种方式，接收消息
    @PostConstruct
    public void init(){
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.err.println("@PostConstruct :" + message.getPayload());
            }
        });
    }
    //第二种方式接收消息
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(String message){
        System.err.println("@ServiceActivator :" + message);
    }
}
