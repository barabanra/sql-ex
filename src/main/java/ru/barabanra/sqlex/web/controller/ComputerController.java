package ru.barabanra.sqlex.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.barabanra.sqlex.dto.properties.PersistenceType;
import ru.barabanra.sqlex.dto.response.ComputerResponseDto;
import ru.barabanra.sqlex.dto.service.ComputerDto;
import ru.barabanra.sqlex.mapper.ComputerMapper;
import ru.barabanra.sqlex.service.ComputerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Computers")
@RequestMapping("/api/v1/computers")
public class ComputerController {

    private final ComputerService computerService;
    private final ComputerMapper computerMapper;

    @Operation(description = "Получение компьютеров")
    @GetMapping
    public List<ComputerResponseDto> findBy(@RequestParam(name = "priceLessThan") BigDecimal priceLessThan,
                                            @RequestParam(name = "persistenceType") PersistenceType persistenceType) {
        List<ComputerDto> byPriceLessThan = computerService.findByPriceLessThan(persistenceType, priceLessThan);
        return computerMapper.mapResponse(byPriceLessThan);
    }

}