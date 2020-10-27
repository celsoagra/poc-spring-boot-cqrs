package io.celsoagra.command;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import io.celsoagra.command.service.CityService;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
public class CityServiceTest {
	
	@Autowired
	CityService cityService;

	@DisplayName("Testando Wiremock")
	@Test
	public void testando_wiremock_app() throws Exception {
		stubFor(get(urlPathMatching("/city"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("testing-library: WireMock")));

		String result = cityService.findByName("test");
		
		verify(getRequestedFor(urlEqualTo("/city")));
		assertEquals("testing-library: WireMock", result);
	}

}
