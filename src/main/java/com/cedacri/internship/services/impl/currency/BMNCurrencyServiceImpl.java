package com.cedacri.internship.services.impl.currency;

import com.cedacri.internship.services.CurrencyService;

import java.io.IOException;
import java.util.Map;

public class BMNCurrencyServiceImpl implements CurrencyService {

    @Override
    public Map<String, Double> getRates() throws IOException, InterruptedException {
        return Map.of("MDL", 20.0);
    }
}
