package com.rian.bookservice.controller;

import com.rian.bookservice.model.BookModel;
import com.rian.bookservice.proxy.CambioProxy;
import com.rian.bookservice.repository.BookRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("book-service")
public class BookController {

    private final Environment environment;
    private final BookRepository bookRepository;
    private final CambioProxy cambioProxy;

    public BookController(Environment environment, BookRepository bookRepository, CambioProxy cambioProxy) {
        this.environment = environment;
        this.bookRepository = bookRepository;
        this.cambioProxy = cambioProxy;
    }

    @GetMapping("/{id}/{currency}")
    public Optional<BookModel> findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {

        var book = bookRepository.findById(id);
        if (book.isEmpty()) throw new RuntimeException("book not found");

        var cambio = cambioProxy.getCambio(BigDecimal.valueOf(book.get().getPrice()), "USD", currency);

        var port  = environment.getProperty("local.server.port");
        book.get().setEnvironment(port + " Feign");

        assert cambio != null;
        book.get().setPrice(cambio.getConvertedValue());

        return book;
    }

    /*
    HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.get().getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
    var response = new RestTemplate()
            .getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);

    var cambio = response.getBody();

    var port  = environment.getProperty("local.server.port");
        book.get().setEnvironment(port);
        assert cambio != null;
        book.get().setPrice(cambio.getConvertedValue());

        return book;
     */
}
