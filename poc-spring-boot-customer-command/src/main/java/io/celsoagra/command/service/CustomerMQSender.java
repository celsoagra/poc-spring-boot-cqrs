package io.celsoagra.command.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.celsoagra.command.entity.Customer;

/**
 * Camada de negócio relacionada ao Cliente
 * 
 * @author celsoagra
 *
 */
@Service
public class CustomerMQSender {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
 
    @Autowired
    private Queue queue;
 
    public void send(Customer customer) throws JsonProcessingException {
    	String info = objectMapper.writeValueAsString(customer);
    	
        rabbitTemplate.convertAndSend(this.queue.getName(), info);
    }

}
