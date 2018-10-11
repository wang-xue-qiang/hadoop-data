/*package com.zkh.web;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class KafkaController {
	
	*//**
	 * 使用日志打印消息
	 *//*
	private static Logger logger = LoggerFactory.getLogger(KafkaController.class);
	*//**
     * 注入kafkaTemplate
     *//*
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    *//**
     * 发送消息的方法
     *
     * @param key  推送数据的key
     * @param data 推送数据的data
     *//*
    private void send(String key, String data) {
        kafkaTemplate.send("test", key, data);
    }

    @RequestMapping("/kafka")
    public String testKafka() {
        int iMax = 6;
        for (int i = 1; i < iMax; i++) {
            send("key" + i, "data" + i);
        }
        return "success";
    }



    @KafkaListener(topics = "test")
    public void receive(ConsumerRecord<?, ?> consumer) {
        logger.info("{} - {}:{}", consumer.topic(), consumer.key(), consumer.value());
    }

}
*/