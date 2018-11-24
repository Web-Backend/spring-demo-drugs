package com.longhoang.services.impl;

import com.longhoang.models.Drug;
import com.longhoang.repository.DrugRepository;
import com.longhoang.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugRepository drugRepository;

    @Override
    public Page<Drug> findAll(Pageable pageable) {
        return drugRepository.findAll(pageable);
    }

    @Override
    public Drug findById(Long id) {
        return drugRepository.findOne(id);
    }

    @Override
    public void save(Drug drug) {
        drugRepository.save(drug);
    }

    @Override
    public void remove(Long id) {
        drugRepository.delete(id);
    }
}
