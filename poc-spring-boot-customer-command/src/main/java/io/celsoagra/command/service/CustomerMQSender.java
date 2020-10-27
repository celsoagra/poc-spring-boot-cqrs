package io.celsoagra.command.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.celsoagra.command.dto.UpdateCustomerDTO;
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
 
	@Value("${queue.name}")
    private String queueName;
	
	@Value("${queue.name.update}")
    private String queueNameUpdate;
 
    public void send(Customer customer) throws JsonProcessingException {
    	String info = objectMapper.writeValueAsString(customer);
    	
        rabbitTemplate.convertAndSend(queueName, info);
    }
    
    public void sendAndUpdate(Long id, UpdateCustomerDTO dto) throws JsonProcessingException {
    	Map map = new HashMap<String, String>();
    	map.put("id", id);
    	map.put("name", dto.getName());
    	
    	String info = objectMapper.writeValueAsString(map);
    	
        rabbitTemplate.convertAndSend(queueNameUpdate, info);
    }
    
    public void sendAndRemove(Customer customer) throws JsonProcessingException {
    	Map map = new HashMap<String, String>();
    	map.put("id", customer.getId());	
    	String info = objectMapper.writeValueAsString(map);
    	
        rabbitTemplate.convertAndSend(queueNameUpdate, info);
    }

}
