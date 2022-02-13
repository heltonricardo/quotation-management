package br.inatel.quotation_management.controller;

import br.inatel.quotation_management.exception.AlreadyExistsException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.service.StockManagerService;
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
@RequestMapping("/manager")
public class StockManagerController {

    private final StockManagerService managerService;

    @PostMapping
    public ResponseEntity<StockManager> create(@RequestBody StockManager manager) {

        HttpStatus responseCode = HttpStatus.OK;

        try {
            managerService.create(manager);
        } catch (AlreadyExistsException e) {
            responseCode = HttpStatus.CONFLICT;
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(responseCode);
    }

    @GetMapping
    public ResponseEntity<Iterable<StockManager>> findAll() {

        Iterable<StockManager> responseBody = managerService.findAll();

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
