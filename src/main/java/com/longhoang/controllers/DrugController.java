package com.longhoang.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DrugController {
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("list");
    }
}
