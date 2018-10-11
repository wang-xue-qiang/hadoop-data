package com.zkh.task;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.bean.WindData;
import com.zkh.util.GenWindData;
@Component
//每隔5秒执行一次：*/5 * * * * ?
//每隔1分钟执行一次：0 */1 * * * ?
//每天23点执行一次：0 0 23 * * ?
//每天凌晨1点执行一次：0 0 1 * * ?
//每月1号凌晨1点执行一次：0 0 1 1 * ?
//每月最后一天23点执行一次：0 0 23 L * ?
//每周星期天凌晨1点实行一次：0 0 1 ? * L
//在26分、29分、33分执行一次：0 26,29,33 * * * ?
//每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
public class Task {
	private final static String KAFKA_PRODUCER_TOPIC = "wordCount";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
	@Scheduled(cron = "*/1 * * * * ?")
	public void scheduled() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
		String createTime = formatter.format(new Date());
		System.out.println("=============本批数据编号"+createTime+"开始====================");
		Properties properties = new Properties();
		InputStream in = Task.class.getClassLoader().getResourceAsStream("producer.properties");
		properties.load(in);
		for (int i = 0; i < 100; i++) {	
			double windSpeed = GenWindData.genWindSpeed();
			WindData vo = new WindData(GenWindData.genWindField(), GenWindData.genFan(), windSpeed, GenWindData.genElec(windSpeed),createTime );
			Producer<String, String> producer = new KafkaProducer<String, String>(properties);
			String json = mapper.writeValueAsString(vo);
			ProducerRecord<String, String> producerRecord =
					new ProducerRecord<String, String>(KAFKA_PRODUCER_TOPIC, createTime, json);
			producer.send(producerRecord);
			producer.close();	
		}
		System.out.println("=============本批数据编号"+createTime+"结束====================");
    }
	public static void main(String[] args) {
		System.out.println(formatter.format(new Date()));
	}
}
