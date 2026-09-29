package com.cedacri.internship;

import com.cedacri.internship.config.HttpClientConfig;
import com.cedacri.internship.services.CurrencyService;
import com.cedacri.internship.services.impl.currency.FrankfurterCurrencyServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

        HttpClientConfig httpClientConfig = new HttpClientConfig();

        CurrencyService currencyService = new FrankfurterCurrencyServiceImpl("https://api.frankfurter.dev/v1/latest",httpClientConfig.getHttpClient());

        System.out.println(currencyService.getRates());

        System.out.println(currencyService.convertToCurrency(1000, "AUD"));
    }
}