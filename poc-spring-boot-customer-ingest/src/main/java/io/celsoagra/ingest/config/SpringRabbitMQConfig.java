package io.celsoagra.ingest.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRabbitMQConfig {

	@Value("${queue.name}")
    private String queueName;
	
	@Value("${queue.name.update}")
    private String queueNameUpdate;
	
	@Value("${queue.name.dlq}")
    private String queueNameDLQ;
	
	@Bean
    public Queue queue() {
		Queue queue = new Queue(queueName, true);
		queue.addArgument("x-dead-letter-exchange", ""); // defaultExchange
		queue.addArgument("x-dead-letter-routing-key", queueNameDLQ);
		queue.addArgument("x-message-ttl", 5000);
		return queue;
    }
	
	@Bean
    public Queue queueUpdate() {
		Queue queue = new Queue(queueNameUpdate, true);
		queue.addArgument("x-dead-letter-exchange", ""); // defaultExchange
		queue.addArgument("x-dead-letter-routing-key", queueNameDLQ);
		queue.addArgument("x-message-ttl", 5000);
		return queue;
    }
	
	@Bean
    public Queue queueDLQ() {
		return new Queue(queueNameDLQ, true);
    }
}
