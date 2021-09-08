package com.example.galaxy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTranslateServiceTest {

    InputTranslateService inputTranslateService;
    GalacticNumeralService galacticNumeralService;

    @BeforeEach
    void setup() {
        galacticNumeralService = new GalacticNumeralService();
        inputTranslateService = new InputTranslateService(galacticNumeralService);
    }

    @Test
    void fileTranslateSuccessTest() throws Exception {

        MockMultipartFile mockFile = getRequestFile("src/test/java/resources/web/requestFile.txt");

        String expected = getResponse("src/test/java/resources/web/responseFile.txt");
        String actual = inputTranslateService.translateInquiry(mockFile);

        assertEquals(expected, actual);
    }

    @Test
    void klingonFileTranslateSuccessTest() throws Exception {

        MockMultipartFile mockFile = getRequestFile("src/test/java/resources/web/klingonRequest.txt");

        String expected = getResponse("src/test/java/resources/web/klingonResponse.txt");
        String actual = inputTranslateService.translateInquiry(mockFile);

        assertEquals(expected, actual);
    }

    private MockMultipartFile getRequestFile(String path) throws Exception {

        InputStream inputStream = new FileInputStream(path);

        return new MockMultipartFile("file", inputStream);
    }

    private String getResponse(String path) throws Exception {

        StringBuilder sb = new StringBuilder();

        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));

        br.lines().forEach(line -> sb.append(line + "\n"));

        return sb.toString();
    }
}
