package com.longhoang.repository;

import com.longhoang.models.Drug;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrugRepository extends PagingAndSortingRepository<Drug, Long> {

}
