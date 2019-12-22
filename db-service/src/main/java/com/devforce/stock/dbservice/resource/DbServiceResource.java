package com.devforce.stock.dbservice.resource;

import com.devforce.stock.dbservice.model.Quote;
import com.devforce.stock.dbservice.model.Quotes;
import com.devforce.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
        return getQuotes(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    @Transactional
    public List<String> delete(@PathVariable("username") final String username){

        quotesRepository.removeByUserName(username);

        return getQuotesByUserName(username);
    }
}
