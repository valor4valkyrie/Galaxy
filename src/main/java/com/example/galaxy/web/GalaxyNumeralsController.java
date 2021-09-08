package com.example.galaxy.web;

import com.example.galaxy.services.GalacticNumeralService;
import com.example.galaxy.services.InputTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/galactic/number/{numeral}")
    public int numeralToNumber(@PathVariable String numeral) {
        return galacticNumeralService.fromGalacticNumerals(numeral);
    }

    @GetMapping(value = "/galactic/numeral/{number}")
    public String numeralToNumber(@PathVariable int number) {
        return galacticNumeralService.toGalacticNumerals(number);
    }

    @PostMapping(value = "/galactic/translate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String translate(@RequestParam("file") MultipartFile file) throws IOException {
        return inputTranslateService.translateInquiry(file);
    }

}
