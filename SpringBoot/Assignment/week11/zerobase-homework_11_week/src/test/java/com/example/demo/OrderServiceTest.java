package com.example.demo;

import com.example.demo.exception.ExceptionCode;
import com.example.demo.exception.ZeroBaseException;
import com.example.demo.infra.entity.Book;
import com.example.demo.infra.entity.BookOrder;
import com.example.demo.infra.entity.BookStock;
import com.example.demo.infra.entity.User;
import com.example.demo.infra.repository.BookOrderRepository;
import com.example.demo.infra.repository.BookRepository;
import com.example.demo.infra.repository.BookStockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @BeforeEach
    void setUp() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(new Book(true, 0)));
        when(bookStockRepository.findByBookId((anyLong()))).thenReturn(Optional.of(new BookStock(10)));
        when(bookOrderRepository.save(any(BookOrder.class))).thenReturn(new BookOrder(1L, null, null));
    }

    @Test
    @DisplayName("재고가 정상으로 차감되며 주문이 성공한다.")
    void success__order() {
        // FIXME
        // given
        final int stockCount = 10;
        BookStock bookStock = BookStock.builder().stock(stockCount).build();
        // when
        when(bookStockRepository.findByBookId(anyLong())).thenReturn(Optional.of(bookStock));
        orderService.order(User.asNew(1L), 1L);

        assertThat(bookStock.getStock()).isLessThan(stockCount);

        verify(bookRepository).findById(anyLong());
        verify(bookStockRepository).findByBookId((anyLong()));
        verify(bookOrderRepository).save(any(BookOrder.class));
    }

    @Test
    @DisplayName("Book 존재하지 않으면 익셉션 발생(NOT_FOUND_BOOK)")
    void throwException__when__not_found_book() {
        // FIXME
        // given when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> orderService.order(User.asNew(1L), 1L))
                .isInstanceOf(ZeroBaseException.class)
                .hasFieldOrPropertyWithValue("code", ExceptionCode.NOT_FOUND_BOOK);
    }

    @Test
    @DisplayName("BookStock 존재하지 않으면 익셉션 발생(NOT_FOUND_BOOK_STOCK)")
    void throwException__when__not_found_bookstock() {
        // FIXME
        // given when
        when(bookStockRepository.findByBookId(anyLong())).thenReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> orderService.order(User.asNew(1L), 1L))
                .isInstanceOf(ZeroBaseException.class)
                .hasFieldOrPropertyWithValue("code", ExceptionCode.NOT_FOUND_BOOK_STOCK);
    }

    @Test
    @DisplayName("판매중이지 않은 책을 주문하면 익셉션 발생(FAIL_BOOK_ORDER)")
    void throwException__when__book_sale_off() {
        // FIXME
        // given
        Book book = Book.builder().sale(false).build();
        // when
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        // then
        assertThatThrownBy(() -> orderService.order(User.asNew(1L), 1L))
                .isInstanceOf(ZeroBaseException.class)
                .hasFieldOrPropertyWithValue("code", ExceptionCode.BOOK_IS_NOT_SALE);
    }

    @Test
    @DisplayName("재고가 부족할때 익셉션 발생(FAIL_BOOK_ORDER)")
    void throwException__when__not_enough_stock() {
        // FIXME
        // given
        BookStock bookStock = BookStock.builder().stock(0).build();
        // when
        when(bookStockRepository.findByBookId(anyLong())).thenReturn(Optional.of(bookStock));
        // then
        assertThatThrownBy(() -> orderService.order(User.asNew(1L), 1L))
                .isInstanceOf(ZeroBaseException.class)
                .hasFieldOrPropertyWithValue("code", ExceptionCode.FAIL_BOOK_ORDER);
    }
}
