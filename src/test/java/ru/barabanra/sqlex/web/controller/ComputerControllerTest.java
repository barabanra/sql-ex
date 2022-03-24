package ru.barabanra.sqlex.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.barabanra.sqlex.config.PostgresTestContainerRule;
import ru.barabanra.sqlex.config.TestUtils;
import ru.barabanra.sqlex.dto.properties.PersistenceType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Import(TestUtils.class)
public class ComputerControllerTest {

    @Container
    private final static PostgresTestContainerRule testContainer = PostgresTestContainerRule.getInstance();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtils testUtils;

    @Test
    public void findByPriceLessThanTest() throws Exception {
        findByPriceLessThanTest(PersistenceType.JPA);
        findByPriceLessThanTest(PersistenceType.TEMPLATE);
        findByPriceLessThanTest(PersistenceType.JOOQ);
    }

    public void findByPriceLessThanTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/computerFindByPriceLessThanTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers?priceLessThan=500" +
                "&persistenceType=" + persistenceType))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

}