package com.rian.bookservice.repository;

import com.rian.bookservice.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<BookModel, Long> {
}
