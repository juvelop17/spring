package com.example.demo.domain;

import com.example.demo.web.infra.exception.ExceptionCode;
import com.example.demo.web.infra.exception.ZeroBaseException;
import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookOrder;
import com.example.demo.domain.entity.BookStock;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.BookOrderRepository;
import com.example.demo.domain.repository.BookRepository;
import com.example.demo.domain.repository.BookStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final BookRepository bookRepository;
    private final BookStockRepository bookStockRepository;
    private final BookOrderRepository bookOrderRepository;

    public Long order(User user, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ZeroBaseException(ExceptionCode.NOT_FOUND_BOOK));
        BookStock bookStock = bookStockRepository.findByBookId(bookId)
                .orElseThrow(() -> new ZeroBaseException(ExceptionCode.NOT_FOUND_BOOK_STOCK));

        checkOnSale(book);
        checkEnoughStock(bookStock);

        bookStock.decreaseStock();
        bookStockRepository.save(bookStock);
        BookOrder bookOrder = bookOrderRepository.save(createBookOrder(user, book));
        return bookOrder.getId();
    }

    private BookOrder createBookOrder(User user, Book book) {
        return BookOrder
                .builder()
                .user(user)
                .book(book)
                .build();
    }

    private void checkEnoughStock(BookStock bookStock) {
        if (!bookStock.enoughStock())
            throw new ZeroBaseException(ExceptionCode.FAIL_BOOK_ORDER, "도서 재고가 부족합니다.");
    }

    private void checkOnSale(Book book) {
        if (!book.onSale())
            throw new ZeroBaseException(ExceptionCode.BOOK_IS_NOT_SALE);
    }
}
