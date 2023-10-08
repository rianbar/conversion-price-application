package com.rian.cambioservice.controller;

import com.rian.cambioservice.model.CambioModel;
import com.rian.cambioservice.repository.CambioRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("cambio-service")
public class CambioController {

    private final Environment environment;
    private final CambioRepository cambioRepository;

    public CambioController(Environment environment, CambioRepository cambioRepository) {
        this.environment = environment;
        this.cambioRepository = cambioRepository;
    }

    @GetMapping("/{amount}/{from}/{to}")
    public CambioModel getCambio(
            @PathVariable("amount")BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
            ) {

        var cambio = cambioRepository.findByFromAndTo(from, to);
        if (cambio == null) throw new RuntimeException("Currency Unsupported");

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        var port = environment.getProperty("local.server.port");
        cambio.setEnvironment(port);

        return cambio;
    }
}
