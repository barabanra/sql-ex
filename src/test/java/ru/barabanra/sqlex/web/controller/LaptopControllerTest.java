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
class LaptopControllerTest {

    @Container
    private final static PostgresTestContainerRule testContainer = PostgresTestContainerRule.getInstance();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestUtils testUtils;

    @Test
    public void findAllLaptopsByHdMoreThanTest() throws Exception {
        findAllLaptopsByHdMoreThanTest(PersistenceType.TEMPLATE);
        findAllLaptopsByHdMoreThanTest(PersistenceType.JPA);
        findAllLaptopsByHdMoreThanTest(PersistenceType.JOOQ);
    }

    public void findAllLaptopsByHdMoreThanTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/findAllLaptopsByHdMoreThanTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/laptops"
                        + "?persistenceType=" + persistenceType
                        + "&hdMoreThan=10"
                ))
                .andExpect(status().isOk())
                .andExpect(content().json(response, true));
    }

    @Test
    public void findAllLaptopsTest() throws Exception {
        findAllLaptopsTest(PersistenceType.TEMPLATE);
        findAllLaptopsTest(PersistenceType.JPA);
        findAllLaptopsTest(PersistenceType.JOOQ);
    }

    public void findAllLaptopsTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/findAllLaptopsTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/laptops"
                        + "?persistenceType=" + persistenceType
                ))
                .andDo(c -> {
                    System.out.println(c.getResponse().getContentAsString());
                    System.out.println(response.replace("\n", "").replaceAll(" ", ""));
                })
                .andExpect(status().isOk())
                .andExpect(content().json(response, true));
    }

}