package io.celsoagra.command;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
public class WiremockTest {

	@Test
	public void testando_wiremock_app() throws Exception {
		stubFor(get(urlPathMatching("/baeldung/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("\"testing-library\": \"WireMock\"")));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet( String.format("http://localhost:%s/baeldung/wiremock", "8080")  );
		HttpResponse httpResponse = httpClient.execute(request);
		String stringResponse = convertResponseToString(httpResponse);

		verify(getRequestedFor(urlEqualTo("/baeldung/wiremock")));
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertEquals("application/json", httpResponse.getFirstHeader("Content-Type").getValue());
		assertEquals("\"testing-library\": \"WireMock\"", stringResponse);
	}

	private String convertResponseToString(HttpResponse response) throws IOException {
		InputStream responseStream = response.getEntity().getContent();
		Scanner scanner = new Scanner(responseStream, "UTF-8");
		String responseString = scanner.useDelimiter("\\Z").next();
		scanner.close();
		return responseString;
	}
	

	@Test
	public void testando_wiremock_mappings() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet request = new HttpGet( String.format("http://localhost:%s/some/thing", "8080")  );
		HttpResponse httpResponse = httpClient.execute(request);
		HttpEntity entity = httpResponse.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		
		verify(getRequestedFor(urlEqualTo("/some/thing")));
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertEquals("text/plain", httpResponse.getFirstHeader("Content-Type").getValue());
		assertEquals("Hello world!", responseString);
	}
}
