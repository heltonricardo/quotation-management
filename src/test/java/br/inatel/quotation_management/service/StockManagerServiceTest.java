package br.inatel.quotation_management.service;

import br.inatel.quotation_management.UnitTestBase;
import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.repository.StockManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockManagerServiceTest extends UnitTestBase {

    @InjectMocks
    StockManagerService stockManagerService;

    @Mock
    StockManagerRepository stockManagerRepository;

    private static String STOCK_ID;
    private static String DESCRIPTION;

    @BeforeAll
    static void setUp() {
        STOCK_ID = "abcd1";
        DESCRIPTION = "This is a description";
    }

    @Test
    @DisplayName("Should create a stock")
    void create_success() throws AlreadyExistsException {

        StockManager stockManager = StockManager.builder()
                .id(STOCK_ID)
                .description(DESCRIPTION)
                .build();

        doReturn(false)
                .when(stockManagerRepository)
                .existsById(STOCK_ID);

        assertDoesNotThrow(() -> stockManagerService.create(stockManager));

        verify(stockManagerRepository).save(stockManager);
    }

    @Test
    @DisplayName("Should not create a stock when already exists")
    void create_fail() throws AlreadyExistsException {

        StockManager stockManager = StockManager.builder()
                .id(STOCK_ID)
                .description(DESCRIPTION)
                .build();

        doReturn(true)
                .when(stockManagerRepository)
                .existsById(STOCK_ID);

        Assertions.assertThrows(AlreadyExistsException.class,
                () -> stockManagerService.create(stockManager));

        verify(stockManagerRepository, never()).save(stockManager);
    }

    @Test
    @DisplayName("Should return true for existing stock")
    void existsByStockQuoteId() {

        doReturn(true).when(stockManagerRepository)
                .existsById(STOCK_ID);

        boolean existsByStockId = stockManagerService.existsByStockId(STOCK_ID);

        assertTrue(existsByStockId);
    }

    @Test
    @DisplayName("Should return false for not existing stock")
    void notExistsByStockQuoteId() {

        doReturn(false).when(stockManagerRepository)
                .existsById(STOCK_ID);

        boolean existsByStockId = stockManagerService.existsByStockId(STOCK_ID);

        assertFalse(existsByStockId);
    }
}
