package io.celsoagra.command.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.celsoagra.command.boundary.city.dto.FindCityDTO;
import io.celsoagra.command.boundary.city.repository.CityRepository;
import io.celsoagra.command.dto.CreateCustomerDTO;
import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.service.CustomerMQSender;
import io.celsoagra.command.service.CustomerService;
import javassist.NotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureWireMock(port = 8082)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Spy
	private CustomerRepository customerRepository;
	
	@Mock
	private CustomerMQSender mqSender;
	
	@Mock
	private CityRepository cityRepository;
	
    @BeforeEach
    void setMockOutput() throws Exception {
    	Mockito.doNothing().when(mqSender).send(Mockito.any());
    	Mockito.when(cityRepository.findByName(Mockito.any())).thenReturn(new FindCityDTO("recife", "PE"));
    }
    
	
	@Order(1)
	@Test
	@DisplayName("Realizando consulta")
	void find() throws Exception {
		CreateCustomerDTO dto = new CreateCustomerDTO("Celso Agra", "M", LocalDate.of(1988, Month.JULY, 26), "recife"); 
		
		Customer customer = customerService.create(dto);
    	
    	// assertEquals("passo fundo", customer.getCity());
    	
		// assertNotNull(customerService.findByName("Celso Agra"));
	}
	
	
	
}
