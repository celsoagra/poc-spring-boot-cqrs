package io.celsoagra.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.celsoagra.command.dto.CreateCustomerDTO;
import io.celsoagra.command.entity.Customer;
import io.celsoagra.command.repository.CustomerRepository;
import io.celsoagra.command.service.CustomerMQSender;
import io.celsoagra.command.service.CustomerService;
import io.celsoagra.command.utils.enumeration.Gender;
import javassist.NotFoundException;

@SpringBootTest
public class MockitoAnnotationTest {

	@Mock
    private CustomerRepository customerRepository;
	
	@Mock
	private CustomerMQSender mqSender;

	@InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setMockOutput() throws JsonProcessingException {
    	Customer customer = new Customer("teste", Gender.M, LocalDate.now(), "recife");
    	Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);
    	Mockito.doNothing().when(mqSender).send(Mockito.any());
    	
    	Mockito.when(customerRepository.count()).thenReturn(111L);
    }
    
    @Test
    public void count() {
        long userCount = customerRepository.count();
     
        assertEquals(111L, userCount);
        Mockito.verify(customerRepository).count();
    }
    
    @Test
    public void add() throws JsonProcessingException, NotFoundException {
    	CreateCustomerDTO dto = new CreateCustomerDTO("Celso", Gender.M.toString(), LocalDate.now(), "recife");
    	Customer c = customerService.create(dto);
     
        assertEquals("teste", c.getName());
    }
	
}
