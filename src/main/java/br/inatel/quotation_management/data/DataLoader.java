package br.inatel.quotation_management.data;

import br.inatel.quotation_management.exception.QMException;
import br.inatel.quotation_management.model.Manager;
import br.inatel.quotation_management.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final ManagerService managerService;

    @Override
    public void run(ApplicationArguments args) {

        List<Manager> stocks = Arrays.asList(
                Manager.builder()
                        .id("petr3")
                        .description("test petr")
                        .build(),
                Manager.builder()
                        .id("vale5")
                        .description("test vale")
                        .build()
        );

        stocks.forEach(s -> {
            try {
                managerService.create(s);
            } catch (QMException ignored) {
            }
        });
    }
}
