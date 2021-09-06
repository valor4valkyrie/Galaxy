package com.example.galaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Service
public class InputTranslateService {

    private GalacticNumeralService galacticNumeralService;

    @Autowired
    public InputTranslateService(GalacticNumeralService galacticNumeralService) {

        this.galacticNumeralService = galacticNumeralService;
    }

    public String translateInquiry(MultipartFile inquiryRequest) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(inquiryRequest.getInputStream()));

        br.lines().forEach(line -> {

        });

        return "";
    }
}
