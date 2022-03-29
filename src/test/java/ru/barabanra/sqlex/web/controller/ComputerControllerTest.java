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
    public void findAllComputersTest() throws Exception {
        findAllComputersTest(PersistenceType.TEMPLATE);
        findAllComputersTest(PersistenceType.JPA);
        findAllComputersTest(PersistenceType.JOOQ);
    }

    public void findAllComputersTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/findAllComputersTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers"
                        + "?persistenceType=" + persistenceType
                )
        )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void findByCDInListValuesTest() throws Exception {
        findByCDInListValuesTest(PersistenceType.TEMPLATE);
        findByCDInListValuesTest(PersistenceType.JPA);
        findByCDInListValuesTest(PersistenceType.JOOQ);
    }

    public void findByCDInListValuesTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/findByCDInListValuesTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers"
                        + "?persistenceType=" + persistenceType
                        + "&cdTypeList=12x,24x"
                )
        )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void findByCDInOneValueTest() throws Exception {
        findByCDInOneValueTest(PersistenceType.TEMPLATE);
        findByCDInOneValueTest(PersistenceType.JPA);
        findByCDInOneValueTest(PersistenceType.JOOQ);
    }

    public void findByCDInOneValueTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/findByCDInOneValueTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers"
                        + "?persistenceType=" + persistenceType
                        + "&cdTypeList=12x"
                )
        )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void findByCDAndPriceLessThanTest() throws Exception {
        findByCDAndPriceLessThan(PersistenceType.TEMPLATE);
        findByCDAndPriceLessThan(PersistenceType.JPA);
        findByCDAndPriceLessThan(PersistenceType.JOOQ);
    }

    public void findByCDAndPriceLessThan(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/computerFindByPriceAndCDTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers"
                        + "?priceLessThan=600"
                        + "&persistenceType=" + persistenceType
                        + "&cdTypeList=12x"
                )
        )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void findByPriceLessThanTest() throws Exception {
        findByPriceLessThanTest(PersistenceType.JPA);
        findByPriceLessThanTest(PersistenceType.TEMPLATE);
        findByPriceLessThanTest(PersistenceType.JOOQ);
    }

    public void findByPriceLessThanTest(PersistenceType persistenceType) throws Exception {
        String response = testUtils.getFileAsString("/json/response/computerFindByPriceLessThanTest.json");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/computers"
                + "?priceLessThan=500"
                + "&persistenceType=" + persistenceType))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

}