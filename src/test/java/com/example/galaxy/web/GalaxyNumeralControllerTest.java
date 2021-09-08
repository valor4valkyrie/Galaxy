package com.example.galaxy.web;

import com.example.galaxy.GalaxyApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GalaxyApplication.class})
@WebAppConfiguration
public class GalaxyNumeralControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testNumeralToNumberSuccess() throws Exception {
        mockMvc.perform(get("/galactic/number/XXI"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("21")));
    }

    @Test
    void testNumeralToNumberFailure() throws Exception {
        mockMvc.perform(get("/galactic/number/fst"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    void testNumberToNumeralSuccess() throws Exception {
        mockMvc.perform(get("/galactic/numeral/64"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("LXIV")));
    }

    @Test
    void testNumberToNumeralFailure() throws Exception {
        mockMvc.perform(get("/galactic/numeral/321321311321313131321231"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testTranslateSuccess() throws Exception {
        mockMvc.perform(multipart("/galactic/translate")
                        .file(getRequestFile("src/test/java/resources/web/requestFile.txt"))
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(getResponse("src/test/java/resources/web/responseFile.txt")))
                );
    }

    @Test
    void testTranslateKlingonSuccess() throws Exception {
        mockMvc.perform(multipart("/galactic/translate")
                        .file(getRequestFile("src/test/java/resources/web/klingonRequest.txt"))
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(getResponse("src/test/java/resources/web/klingonResponse.txt")))
                );
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
