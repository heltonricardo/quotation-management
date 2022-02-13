package br.inatel.quotation_management.controller;

import br.inatel.quotation_management.dto.StockRequestDTO;
import br.inatel.quotation_management.model.Stock;
import br.inatel.quotation_management.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> create(
            @RequestBody StockRequestDTO stockRequestDTO) {

        Stock stockRequest = stockRequestDTO.toObj();

        HttpStatus responseCode = stockService.create(stockRequest).isPresent()
                ? HttpStatus.CREATED
                : HttpStatus.CONFLICT;

        return new ResponseEntity<>(responseCode);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Optional<Stock>> findByStockId(
            @PathVariable String stockId) {

        Optional<Stock> responseBody = stockService.findByStockId(stockId);

        if (responseBody.isPresent()) {
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Iterable<Stock>> findAll() {

        Iterable<Stock> responseBody = stockService.findAll();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
