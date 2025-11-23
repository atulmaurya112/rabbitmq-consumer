package com.learn.rabbitmq.serviceimpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.learn.rabbitmq.config.RabbitConfig;
import com.learn.rabbitmq.dto.UserDto;

@Service
public class RabbitConsumerServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(RabbitConsumerServiceImpl.class);

	@RabbitListener(queues = RabbitConfig.STRING_QUEUE)
	public void consumeString(String value) {
		try {
			log.info("Consumed String: {}", value);
		} catch (Exception e) {
			log.error("Error consuming String: {}", e.getMessage());
		}
	}

	@RabbitListener(queues = RabbitConfig.DTO_QUEUE)
	public void consumeDto(UserDto dto) {
		try {
			log.info("Consumed DTO: {}", dto);
		} catch (Exception e) {
			log.error("Error consuming DTO: {}", e.getMessage());
		}
	}

	@RabbitListener(queues = RabbitConfig.MAP_QUEUE)
	public void consumeMap(Map<String, Object> map) {
		try {
			log.info("Consumed Map: {}", map);
		} catch (Exception e) {
			log.error("Error consuming Map: {}", e.getMessage());
		}
	}

	@RabbitListener(queues = RabbitConfig.LIST_QUEUE)
	public void consumeList(List<String> list) {
		try {
			log.info("Consumed List: {}", list);
		} catch (Exception e) {
			log.error("Error consuming List: {}", e.getMessage());
		}
	}

}