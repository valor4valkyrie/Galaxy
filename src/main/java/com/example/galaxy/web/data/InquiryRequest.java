package com.example.galaxy.web.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = InquiryRequest.class)
public class InquiryRequest {

    String inquiry;

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }
}
