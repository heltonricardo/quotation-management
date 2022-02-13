package br.inatel.quotation_management.service;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.exception.NotAllowedException;
import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.StockQuote;
import br.inatel.quotation_management.repository.StockQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockQuoteService {

    private final StockQuoteRepository stockQuoteRepository;
    private final StockManagerService stockManagerService;

    public void create(StockQuote stockQuote) throws QMException {

        boolean notAllowed = stockManagerService
                        .notExistsByStockQuoteId(stockQuote.getStockId());

        if (notAllowed) {
            throw new NotAllowedException();
        }

        boolean stockQuoteAlreadyExists =
                stockQuoteRepository.findById(stockQuote.getId()).isPresent()
                || findByStockId(stockQuote.getStockId()).isPresent();

        if (stockQuoteAlreadyExists) {
            throw new AlreadyExistsException();
        }

        stockQuoteRepository.save(stockQuote);
    }

    public Optional<StockQuote> findByStockId(String stockId) {

        return stockQuoteRepository.findByStockId(stockId);
    }

    public Iterable<StockQuote> findAll() {

        return stockQuoteRepository.findAll();
    }
}
