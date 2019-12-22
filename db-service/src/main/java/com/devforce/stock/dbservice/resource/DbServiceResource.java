package com.devforce.stock.dbservice.resource;

import com.devforce.stock.dbservice.model.Quote;
import com.devforce.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {

        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}
