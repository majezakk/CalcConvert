package com.example.CalcConvert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ConverterController {

    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();
    static {
        EXCHANGE_RATES.put("USD", 1.0);
        EXCHANGE_RATES.put("EUR", 0.904);
        EXCHANGE_RATES.put("GBP", 0.762);
        EXCHANGE_RATES.put("JPY", 146.22);
        EXCHANGE_RATES.put("RUB", 90.56);
    }

    @GetMapping("/converter")
    public String getConverter(){
        return "Converter";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam double amount,
                          @RequestParam String fromCurrency,
                          @RequestParam String toCurrency,
                          Model model) {
        double fromRate = EXCHANGE_RATES.get(fromCurrency);
        double toRate = EXCHANGE_RATES.get(toCurrency);
        double result = amount * (toRate / fromRate);
        String ConResult = String.format("%.2f %s = %.2f %s", amount, fromCurrency, result, toCurrency);
        model.addAttribute("result", ConResult);
        return "Converter";
    }
}
