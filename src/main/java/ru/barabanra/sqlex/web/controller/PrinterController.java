package ru.barabanra.sqlex.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.response.PrinterResponseDto;
import ru.barabanra.sqlex.dto.service.PrinterDto;
import ru.barabanra.sqlex.mapper.PrinterMapper;
import ru.barabanra.sqlex.service.PrinterService;

import java.util.List;

@RestController
@Tag(name = "Принтеры")
@RequiredArgsConstructor
@RequestMapping("/api/v1/printers")
public class PrinterController {

    private final PrinterService printerService;
    private final PrinterMapper printerMapper;

    @GetMapping
    @Operation(description = "Получение принтеров")
    public List<PrinterResponseDto> findAll(@RequestParam(name = "persistenceType") PersistenceType persistenceType) {
        List<PrinterDto> all = printerService.findAll(persistenceType);
        return printerMapper.map(all);
    }

}