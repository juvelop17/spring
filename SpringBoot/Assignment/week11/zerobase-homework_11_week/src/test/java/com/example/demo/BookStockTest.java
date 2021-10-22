package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookStockTest {

    @Test
    @DisplayName("BookStock 재고가 정상적으로 차감된다")
    void success__decreaseStock() {
        // FIXME
    }

    @Test
    @DisplayName("BookStock 재고가 0일때, 차감을 시도하면 익셉션 발생(NOT_ENOUGH_STOCK)")
    void throwException__when__not_enough_stock() {
        // FIXME
    }
}
