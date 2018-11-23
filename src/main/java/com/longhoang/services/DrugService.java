package com.longhoang.services;

import com.longhoang.models.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DrugService {
    Page<Drug> findAll(Pageable pageable);

    Drug findById(Long id);

    void save(Drug drug);

    void remove(Long id);
}
