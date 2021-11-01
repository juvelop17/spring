package com.example.demo.domain.repository;


import com.example.demo.domain.entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {
    Optional<BookStock> findByBookId(long bookId);
}
