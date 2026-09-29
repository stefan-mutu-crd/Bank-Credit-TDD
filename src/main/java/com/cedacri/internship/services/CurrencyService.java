package com.cedacri.internship.services;

import java.io.IOException;
import java.util.Map;

public interface CurrencyService {

    Map<String, Double> getRates() throws IOException, InterruptedException;

    default double convertToCurrency(double value, String currency) throws IOException, InterruptedException {
        return value * getRates().get(currency);
    }
}
