package br.inatel.quotation_management.service;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.exception.NotAllowedException;
import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.Stock;
import br.inatel.quotation_management.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ManagerService managerService;

    public void create(Stock stock) throws QMException {

        boolean notAllowed =
                managerService.notExistsByStockId(stock.getStockId());

        if (notAllowed) {
            throw new NotAllowedException();
        }

        boolean stockAlreadyExists =
                stockRepository.findById(stock.getId()).isPresent()
                || findByStockId(stock.getStockId()).isPresent();

        if (stockAlreadyExists) {
            throw new AlreadyExistsException();
        }

        stockRepository.save(stock);
    }

    public Optional<Stock> findByStockId(String stockId) {

        return stockRepository.findByStockId(stockId);
    }

    public Iterable<Stock> findAll() {

        return stockRepository.findAll();
    }
}
