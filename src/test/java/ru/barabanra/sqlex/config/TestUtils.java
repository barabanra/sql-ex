package ru.barabanra.sqlex.config;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class TestUtils {

    @SneakyThrows
    public String getFileAsString(String path) {
        return IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream(path)), StandardCharsets.UTF_8);
    }

}
