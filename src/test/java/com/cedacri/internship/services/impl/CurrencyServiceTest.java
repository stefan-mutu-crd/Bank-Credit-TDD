package com.cedacri.internship.services.impl;

import com.cedacri.internship.services.CurrencyService;
import com.cedacri.internship.services.impl.currency.FrankfurterCurrencyServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    private CurrencyService currencyService;

    @BeforeEach
    void setUp(){
        currencyService = new FrankfurterCurrencyServiceImpl("https://api.test", httpClient);
    }

    @Test
    public void getRates_MockHttpResponse_ReturnThreeValues() throws IOException, InterruptedException {
        String json = """
                {
                  "amount": 1.0,
                  "base": "EUR",
                  "date": "2026-09-28",
                  "rates": { "USD": 1.08, "RON": 4.97, "MDL": 19.45 }
                }
                """;

        when(httpResponse.body()).thenReturn(json);
        when(httpClient.<String>send(any(HttpRequest.class), any())).thenReturn(httpResponse);

        Map<String, Double> rates = currencyService.getRates();

        assertEquals(3, rates.size());
        assertEquals(1.08, rates.get("USD"));
    }

    @Test
    void convertToCurrency() {

    }
}
