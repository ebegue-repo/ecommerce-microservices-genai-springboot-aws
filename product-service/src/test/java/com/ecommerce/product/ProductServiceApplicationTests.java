package com.ecommerce.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully
    }

}