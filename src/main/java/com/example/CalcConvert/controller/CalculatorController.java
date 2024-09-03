package com.example.CalcConvert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculatorController {

    private final List<String> answers = new ArrayList<>();

    @GetMapping("/calculator")
    public String getCalculator(){
        return "Calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double num1,
                            @RequestParam double num2,
                            @RequestParam String operation,
                            Model model) {
        double result;
        String expression;

        switch (operation) {
            case "+":
                result = num1 + num2;
                expression = num1 + " + " + num2 + " = " + result;
                break;
            case "-":
                result = num1 - num2;
                expression = num1 + " - " + num2 + " = " + result;
                break;
            case "*":
                result = num1 * num2;
                expression = num1 + " * " + num2 + " = " + result;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                    expression = num1 + " / " + num2 + " = " + result;
                } else {
                    expression = "Ошибка: деление на ноль";
                }
                break;
            default:
                expression = "Ошибка: неизвестная операция";
        }

        answers.add(expression);
        model.addAttribute("answers", answers);
        return "Answer";
    }

    @GetMapping("/answers")
    public String showAnswers(Model model) {
        model.addAttribute("answers", answers);
        return "Answer";
    }
}
