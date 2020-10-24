package io.celsoagra.ingest.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.celsoagra.ingest.entity.Customer;
import io.celsoagra.ingest.service.CustomerService;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Component
public class CustomerMQReceiver {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private CustomerService customerService;
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerMQReceiver.class);

	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String fileBody) throws JsonMappingException, JsonProcessingException {
		Customer customer = objectMapper.readValue(fileBody, Customer.class);
		LOGGER.info("body: " + customer.toString());
		
		customerService.create(customer);
	}
}
