package ru.barabanra.sqlex.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.barabanra.sqlex.dto.request.LaptopFilter;
import ru.barabanra.sqlex.dto.response.LaptopResponseDto;
import ru.barabanra.sqlex.dto.service.LaptopDto;
import ru.barabanra.sqlex.mapper.LaptopMapper;
import ru.barabanra.sqlex.service.LaptopService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Laptops")
@RequestMapping("/api/v1/laptops")
public class LaptopController {

    private final LaptopService laptopService;
    private final LaptopMapper laptopMapper;

    @GetMapping
    @Operation(description = "Получение планшетов")
    public List<LaptopResponseDto> findBy(@Valid LaptopFilter laptopFilter) {
        List<LaptopDto> allBy = laptopService.findBy(laptopFilter);
        return laptopMapper.mapResponse(allBy);
    }

}