package com.example.galaxy.web;

import com.example.galaxy.services.GalacticNumeralService;
import com.example.galaxy.services.InputTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
public class GalaxyNumeralsController {

    private GalacticNumeralService galacticNumeralService;
    private InputTranslateService inputTranslateService;

    @Autowired
    public GalaxyNumeralsController(GalacticNumeralService galacticNumeralService,
                                    InputTranslateService inputTranslateService) {
        this.galacticNumeralService = galacticNumeralService;
        this.inputTranslateService = inputTranslateService;
    }

    @GetMapping(value = "/galactic/numeral/{x}")
    public String numeralToNumber(@PathParam(value = "x") String x) {
        System.out.println(x);
        //return galacticNumeralService.fromGalacticNumerals(numeral);
        return "";
    }

    @PostMapping(value = "/galactic/translate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String translate(@RequestParam("file") MultipartFile file) throws IOException {
        inputTranslateService.translateInquiry(file);
        return "Success";
    }

}
