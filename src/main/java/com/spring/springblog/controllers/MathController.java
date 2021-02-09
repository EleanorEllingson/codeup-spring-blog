package com.spring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2){
        return number1 + " + " + number2 + " = " + (number1 + number2) + "!";
    }

    @RequestMapping(path = "/minus/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int number1, @PathVariable int number2){
        return number1 + " - " + number2 + " = " + (number1 - number2) + "!";
    }

    @RequestMapping(path = "/times/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int number1, @PathVariable int number2){
        return number1 + " * " + number2 + " = " + (number1 * number2) + "!";
    }

    @RequestMapping(path = "/divide/{number1}/and/{number2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int number1, @PathVariable int number2){
        return number1 + " / " + number2 + " = " + (number1 / number2) + "!";
    }


}
