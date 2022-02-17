package io.github.heltonricardo.quotation_management.controller;

import io.github.heltonricardo.quotation_management.exception.AlreadyExistsException;
import io.github.heltonricardo.quotation_management.model.StockManager;
import io.github.heltonricardo.quotation_management.service.StockManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockManagerController {

    private final StockManagerService stockManagerService;

    @PostMapping
    public ResponseEntity<StockManager> create(
            @RequestBody StockManager manager) {

        HttpStatus responseCode = HttpStatus.OK;

        try {
            stockManagerService.create(manager);
        } catch (AlreadyExistsException e) {
            responseCode = HttpStatus.CONFLICT;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(responseCode);
    }

    @GetMapping
    public ResponseEntity<Iterable<StockManager>> findAll() {

        Iterable<StockManager> responseBody = stockManagerService.findAll();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
