package com.devforce.stock.dbservice.repository;

import com.devforce.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuotesRepository extends CrudRepository<Quote, Integer> {
    List<Quote> findByUserName(String username);
    void removeByUserName(String username);
}
