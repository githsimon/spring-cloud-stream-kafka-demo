package com.gupao.springcloudstreamkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/9/20 9:39
 **/
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics={"${mykafka.topic}"})
    public void onMessage(String message){
        System.err.println("KafkaListener receive message: " + message);
    }
}
