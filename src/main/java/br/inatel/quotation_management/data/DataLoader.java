package br.inatel.quotation_management.data;

import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.StockManager;
import br.inatel.quotation_management.service.StockManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final StockManagerService stockManagerService;

    @Override
    public void run(ApplicationArguments args) {

        List<StockManager> stockQuotes = Arrays.asList(
                StockManager.builder()
                        .id("petr3")
                        .description("test petr")
                        .build(),
                StockManager.builder()
                        .id("vale5")
                        .description("test vale")
                        .build()
        );

        stockQuotes.forEach(s -> {
            try {
                stockManagerService.create(s);
            } catch (QMException ignored) {
            }
        });
    }
}
