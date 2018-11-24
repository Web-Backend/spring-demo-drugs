package com.longhoang.models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table(name = "drugs")
public class Drug implements Validator {
    @Column(name = "id", length = 20)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "price", length = 20)
    private double price;

    @Column(name = "description", length = 155)
    private String description;

    public Drug() {
    }

    public Drug(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Drug.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Drug drug = (Drug) target;
        String name = drug.getName();
        ValidationUtils.rejectIfEmpty(errors, "name", "index.name.empty");
        if (name.length() < 5 || name.length() > 30) {
            errors.rejectValue("name", "index.name.length");
        }
        String price = String.valueOf(drug.getPrice());
        ValidationUtils.rejectIfEmpty(errors, "price", "index.price.empty");
        if (Double.parseDouble(price) < 5) {
            errors.rejectValue("price", "index.price");
        }
        if (!price.matches("^[0-9\\.]*$")) {
            errors.rejectValue("price", "index.price.matches");
        }
    }
}

