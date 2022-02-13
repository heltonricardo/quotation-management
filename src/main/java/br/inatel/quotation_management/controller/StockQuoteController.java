package br.inatel.quotation_management.controller;

import br.inatel.quotation_management.dto.StockQuoteRequestDTO;
import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.exception.NotAllowedException;
import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.StockQuote;
import br.inatel.quotation_management.service.StockQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock-quote")
public class StockQuoteController {

    private final StockQuoteService stockQuoteService;

    @PostMapping
    public ResponseEntity<StockQuote> create(
            @RequestBody StockQuoteRequestDTO stockQuoteRequestDTO) {

        StockQuote stockQuoteRequest = stockQuoteRequestDTO.toObj();

        HttpStatus responseCode = HttpStatus.CREATED;

        try {
            stockQuoteService.create(stockQuoteRequest);
        } catch (NotAllowedException e) {
            System.out.println(e.getMessage());
            responseCode = HttpStatus.NOT_ACCEPTABLE;
        } catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
            responseCode = HttpStatus.CONFLICT;
        } catch (QMException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(responseCode);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Optional<StockQuote>> findByStockId(
            @PathVariable String stockId) {

        Optional<StockQuote> responseBody =
                stockQuoteService.findByStockQuoteId(stockId);

        if (responseBody.isPresent()) {
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Iterable<StockQuote>> findAll() {

        Iterable<StockQuote> responseBody = stockQuoteService.findAll();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
