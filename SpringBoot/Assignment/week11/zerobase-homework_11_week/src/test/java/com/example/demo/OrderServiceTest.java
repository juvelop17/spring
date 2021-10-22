package com.example.demo;

import com.example.demo.infra.repository.BookOrderRepository;
import com.example.demo.infra.repository.BookRepository;
import com.example.demo.infra.repository.BookStockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    BookRepository bookRepository;
    @Mock
    BookStockRepository bookStockRepository;
    @Mock
    BookOrderRepository bookOrderRepository;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("재고가 정상으로 차감되며 주문이 성공한다.")
    void success__order() {
        // FIXME
    }

    @Test
    @DisplayName("Book 존재하지 않으면 익셉션 발생(NOT_FOUND_BOOK)")
    void throwException__when__not_found_book() {
        // FIXME
    }

    @Test
    @DisplayName("BookStock 존재하지 않으면 익셉션 발생(NOT_FOUND_BOOK_STOCK)")
    void throwException__when__not_found_bookstock() {
        // FIXME
    }

    @Test
    @DisplayName("판매중이지 않은 책을 주문하면 익셉션 발생(FAIL_BOOK_ORDER)")
    void throwException__when__book_sale_off() {
        // FIXME
    }

    @Test
    @DisplayName("재고가 부족할때 익셉션 발생(FAIL_BOOK_ORDER)")
    void throwException__when__not_enough_stock() {
        // FIXME
    }
}
