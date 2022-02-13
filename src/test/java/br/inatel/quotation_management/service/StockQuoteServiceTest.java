package br.inatel.quotation_management.service;

import br.inatel.quotation_management.UnitTestBase;
import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.exception.NotAllowedException;
import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.model.StockQuote;
import br.inatel.quotation_management.repository.StockManagerRepository;
import br.inatel.quotation_management.repository.StockQuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockQuoteServiceTest extends UnitTestBase {

    @InjectMocks
    StockQuoteService stockQuoteService;

    @Mock
    StockQuoteRepository stockQuoteRepository;

    @Mock
    StockManagerService stockManagerService;

    private static String ID;
    private static String STOCK_ID;

    @BeforeAll
    static void setUp() {
        ID = UUID.randomUUID().toString();
        STOCK_ID = "abcd1";
    }

    @Test
    @DisplayName("Should create a stock quote")
    void create_success() throws QMException {

        StockQuote stockQuote = StockQuote.builder()
                .id(ID)
                .stockId(STOCK_ID)
                .quotes(new ArrayList<>())
                .build();

        doReturn(false)
                .when(stockManagerService)
                .notExistsByStockQuoteId(STOCK_ID);

        doReturn(Optional.empty())
                .when(stockQuoteRepository)
                .findByStockId(STOCK_ID);

        doReturn(Optional.empty())
                .when(stockQuoteRepository)
                .findById(ID);

        assertDoesNotThrow(() -> stockQuoteService.create(stockQuote));

        verify(stockQuoteRepository).save(stockQuote);
    }


    @Test
    @DisplayName("Should not create stock quote when not allowed")
    void createFailwhennotallowed() throws QMException {

        StockQuote stockQuote = StockQuote.builder()
                .id(ID)
                .stockId(STOCK_ID)
                .quotes(new ArrayList<>())
                .build();

        doReturn(true)
                .when(stockManagerService)
                .notExistsByStockQuoteId(STOCK_ID);

        assertThrows(NotAllowedException.class,
                () -> stockQuoteService.create(stockQuote));

        verify(stockQuoteRepository, never()).save(stockQuote);
    }


    @Test
    @DisplayName("Should not create stock quote when already exists")
    void createFailWhenAlreadyExistsByStockId() throws QMException {

        StockQuote stockQuote = StockQuote.builder()
                .id(ID)
                .stockId(STOCK_ID)
                .quotes(new ArrayList<>())
                .build();

        doReturn(false)
                .when(stockManagerService)
                .notExistsByStockQuoteId(STOCK_ID);

        doReturn(Optional.of(stockQuote))
                .when(stockQuoteRepository)
                .findByStockId(STOCK_ID);

        doReturn(Optional.empty())
                .when(stockQuoteRepository)
                .findById(ID);


        assertThrows(AlreadyExistsException.class,
                () -> stockQuoteService.create(stockQuote));

        verify(stockQuoteRepository, never()).save(stockQuote);
    }

    @Test
    @DisplayName("Should not create stock quote when already exists")
    void createFailWhenAlreadyExistsById() throws QMException {

        StockQuote stockQuote = StockQuote.builder()
                .id(ID)
                .stockId(STOCK_ID)
                .quotes(new ArrayList<>())
                .build();

        doReturn(false)
                .when(stockManagerService)
                .notExistsByStockQuoteId(STOCK_ID);

        doReturn(Optional.of(stockQuote))
                .when(stockQuoteRepository)
                .findById(ID);


        doReturn(Optional.empty())
                .when(stockQuoteRepository)
                .findByStockId(STOCK_ID);

        assertThrows(AlreadyExistsException.class,
                () -> stockQuoteService.create(stockQuote));

        verify(stockQuoteRepository, never()).save(stockQuote);
    }
}
