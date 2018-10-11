package com.zkh.web;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zkh.bean.WindData;
import com.zkh.util.GenWindData;

@Controller
public class WindDataController {
	private final static String KAFKA_PRODUCER_TOPIC = "wordCount";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	@ResponseBody
	@RequestMapping("/getWindData")
	public List<WindData> genWindData() throws Exception{
		List<WindData> list = new ArrayList<WindData>(60);
		ObjectMapper mapper = new ObjectMapper();
		String createTime = formatter.format(new Date());
		for (int i = 0; i < 10; i++) {
			double windSpeed = GenWindData.genWindSpeed();
			WindData vo = new WindData(GenWindData.genWindField(), GenWindData.genFan(), windSpeed, GenWindData.genElec(windSpeed),createTime );
	        String json = mapper.writeValueAsString(vo);
	        System.out.println(json);
			Properties properties = new Properties();
			InputStream in = WindDataController.class.getClassLoader().getResourceAsStream("producer.properties");
			properties.load(in);
			Producer<String, String> producer = new KafkaProducer<String, String>(properties);
			ProducerRecord<String, String> producerRecord =
					//new ProducerRecord<String, String>(KAFKA_PRODUCER_TOPIC, createTime, mapper.writeValueAsString(list));
					new ProducerRecord<String, String>(KAFKA_PRODUCER_TOPIC, createTime, json);
			producer.send(producerRecord);
			producer.close();
			list.add(vo);
		}
		return list;
	}
}
