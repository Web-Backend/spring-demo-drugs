package com.longhoang.controllers;

import com.longhoang.models.Drug;
import com.longhoang.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/drugs")
    public ModelAndView listDrug(@PageableDefault(value = 5)Pageable pageable) {
        Page<Drug> drugs = drugService.findAll(pageable);
        return new ModelAndView("list", "drugs", drugs);
    }

    @GetMapping("/create-drug")
    public ModelAndView createDrugForm() {
        return new ModelAndView("create", "drug", new Drug());
    }

    @PostMapping("/create-drug")
    public ModelAndView saveDrug(@ModelAttribute("drug") Drug drug, BindingResult bindingResult, @RequestParam("name") String name,
                                 @RequestParam("price") double price, @RequestParam("description") String description) {
        drug.validate(drug, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        } else {
            drugService.save(drug);
            ModelAndView modelAndView = new ModelAndView("create", "drug", new Drug());
            drug.setName(name);
            drug.setPrice(price);
            drug.setDescription(description);
            modelAndView.addObject("message", "Created successfully");
            return modelAndView;
        }
    }
}
