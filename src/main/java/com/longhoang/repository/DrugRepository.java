package com.longhoang.repository;

import com.longhoang.models.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrugRepository extends PagingAndSortingRepository<Drug, Long> {
    Page<Drug> findAllByNameContaining(String name, Pageable pageable);
}
