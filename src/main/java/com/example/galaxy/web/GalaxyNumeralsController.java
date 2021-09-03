package com.example.galaxy.web;

import com.example.galaxy.services.GalacticNumeralService;
import com.example.galaxy.web.data.InquiryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GalaxyNumeralsController {

    private GalacticNumeralService galacticNumeralService;

    @Autowired
    public GalaxyNumeralsController(GalacticNumeralService galacticNumeralService) {
        this.galacticNumeralService = galacticNumeralService;
    }

    @PostMapping(value = "/galactic/numerals/inquiry")
    public String queries(@RequestBody InquiryRequest inquiry) {
        return galacticNumeralService.fromGalacticNumerals(inquiry.getInquiry());
    }

}
