package com.gupao.springcloudstreamkafka.web.controller;

import com.gupao.springcloudstreamkafka.consumer.KafkaConsumerListener;
import com.gupao.springcloudstreamkafka.stream.consumer.MessageConsumerBean;
import com.gupao.springcloudstreamkafka.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 小哥哥，写点注释吧
 * @Author yangsong3
 * @Date 2018/9/18 19:57
 **/
@RestController
public class KafkaProducerController {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final String kafkaTopic;
    private final MessageProducerBean messageProducerBean;
    @Autowired
    public KafkaProducerController(KafkaTemplate<String,String> kafkaTemplate,
                                   @Value("${myKafka.topic}") String kafkaTopic,
                                   MessageProducerBean messageProducerBean) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
        this.messageProducerBean = messageProducerBean;
    }

    /**
     * KafkaTemp形式发送消息
     *消息接送 -> {@link KafkaConsumerListener}
     * @param message
     * @return
     */
    @GetMapping("gjmes/send")
    public String sendMessage(@RequestParam String message){
        kafkaTemplate.send(kafkaTopic,message);
        return "OK";
    }

    /**
     * 通过消息生产者 {@link MessageProducerBean} 方式发送
     * @param message
     * @return
     */
    @GetMapping("gjmes/send2")
    public String sendMessage2(@RequestParam String message){
        messageProducerBean.send(message);
        return "OK";
    }
}
