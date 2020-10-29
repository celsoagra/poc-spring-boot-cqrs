package io.celsoagra.command;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import io.celsoagra.command.boundary.city.dto.FindCityDTO;
import io.celsoagra.command.boundary.city.repository.CityRepository;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
public class CityServiceTest {
	
	@Autowired
	CityRepository cityRepository;

	@DisplayName("Testando Wiremock")
	@Test
	public void testando_wiremock_app() throws Exception {
		stubFor(get(urlPathMatching("/city"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("testing-library: WireMock")));

		FindCityDTO result = cityRepository.findByName("test");
		
		// verify(getRequestedFor(urlEqualTo("/city")));
		// assertEquals("testing-library: WireMock", result);
	}

}
