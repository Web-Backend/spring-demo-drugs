package com.longhoang.controllers;

import com.longhoang.models.Drug;
import com.longhoang.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/drugs")
    public ModelAndView listDrug(@PageableDefault(value = 5)Pageable pageable, @RequestParam Optional<String> nameSearch) {
        Page<Drug> drugs;
        if (nameSearch.isPresent()) {
            drugs = drugService.findAllByNameContaining(nameSearch.get(), pageable);
        } else {
            drugs = drugService.findAll(pageable);
        }
        return new ModelAndView("list", "drugs", drugs);
    }

    @GetMapping("/create-drug")
    public ModelAndView createDrugForm() {
        return new ModelAndView("create", "drug", new Drug());
    }

    @PostMapping("/create-drug")
    public ModelAndView saveDrug(@ModelAttribute("drug") Drug drug, BindingResult bindingResult) {
        drug.validate(drug, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        } else {
            drugService.save(drug);
            ModelAndView modelAndView = new ModelAndView("create", "drug", new Drug());
            modelAndView.addObject("message", "Created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/drugs/edit/{id}")
    public ModelAndView editDrugForm(@PathVariable("id") Long id) {
        return new ModelAndView("edit", "drug", drugService.findById(id));
    }

    @PostMapping("/drugs/edit/")
    public ModelAndView updateDrug(@ModelAttribute("drug") Drug drug) {
        drugService.save(drug);
        ModelAndView modelAndView = new ModelAndView("edit", "drug", drug);
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }

    @GetMapping("/drugs/delete/{id}")
    public ModelAndView deleteDrugForm(@PathVariable Long id) {
        return new ModelAndView("delete", "drug", drugService.findById(id));
    }

    @PostMapping("/drugs/delete/")
    public ModelAndView deleteDrug(@ModelAttribute Drug drug) {
        drugService.remove(drug.getId());
        ModelAndView modelAndView = new ModelAndView("delete", "drug", drug);
        modelAndView.addObject("message", "Deleted successfully");
        return modelAndView;
    }

    @GetMapping("/drugs/detail/{id}")
    public ModelAndView viewDetail(@PathVariable Long id) {
        return new ModelAndView("detail", "drug", drugService.findById(id));
    }
}
