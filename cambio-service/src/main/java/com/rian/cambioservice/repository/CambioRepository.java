package com.rian.cambioservice.repository;

import com.rian.cambioservice.model.CambioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<CambioModel, Long> {

    CambioModel findByFromAndTo(String from, String to);
}
