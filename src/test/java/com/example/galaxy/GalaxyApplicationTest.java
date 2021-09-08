package com.example.galaxy;

import com.example.galaxy.web.GalaxyNumeralsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GalaxyApplicationTest {

    private GalaxyNumeralsController galaxyNumeralsController;

    @Autowired
    public GalaxyApplicationTest(GalaxyNumeralsController galaxyNumeralsController) {
        this.galaxyNumeralsController = galaxyNumeralsController;
    }

    @Test
    void contextLoads() {
        assertThat(galaxyNumeralsController).isNotNull();
    }
}
