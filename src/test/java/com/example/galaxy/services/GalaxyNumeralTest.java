package com.example.galaxy.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GalaxyNumeralTest {

    GalacticNumeralService galacticNumeralService;

    @BeforeEach
    void setup() {
        galacticNumeralService = new GalacticNumeralService();
    }

    //Tests for converting numerals to numbers
    @Test
    void fromGalacticNumeralsValidMMVITest() {
        String twoZeroZeroSix = "MMVI";

        Assertions.assertEquals(2006, galacticNumeralService.fromGalacticNumerals(twoZeroZeroSix));
    }

    @Test
    void fromGalacticNumeralsValidMCMXLIVTest() {
        String nineTeenFourtyFour = "MCMXLIV";

        Assertions.assertEquals(1944, galacticNumeralService.fromGalacticNumerals(nineTeenFourtyFour));
    }

    @Test
    void fromGalacticNumeralsValidCIXTest() {
        String oneHundredNine = "CIX";

        Assertions.assertEquals(109, galacticNumeralService.fromGalacticNumerals(oneHundredNine));
    }

    @Test
    void fromGalacticNumeralsValidIXTest() {
        String nine = "IX";

        Assertions.assertEquals(9, galacticNumeralService.fromGalacticNumerals(nine));
    }

    @Test
    void fromGalacticNumeralsValidXTest() {
        String nine = "X";

        Assertions.assertEquals(10, galacticNumeralService.fromGalacticNumerals(nine));
    }

    @Test
    void fromGalacticNumeralsValidXCTest() {
        String ninety = "XC";

        Assertions.assertEquals(90, galacticNumeralService.fromGalacticNumerals(ninety));
    }

    @Test
    void fromGalacticNumeralsValidXXIXTest() {
        String twentyNine = "XXIX";

        Assertions.assertEquals(29, galacticNumeralService.fromGalacticNumerals(twentyNine));
    }

    @Test
    void fromGalacticNumeralsInvalidXXXXTest() {
        String fourThousand = "XXXX";

        Assertions.assertEquals(0, galacticNumeralService.fromGalacticNumerals(fourThousand));
    }

    @Test
    void fromGalacticNumeralsInvalidIXXXXTest() {
        String invalid = "IXXXX";

        Assertions.assertEquals(0, galacticNumeralService.fromGalacticNumerals(invalid));
    }

    @Test
    void fromGalacticNumeralsInvalidDDTest() {
        String invalidThousand = "DD";

        Assertions.assertEquals(0, galacticNumeralService.fromGalacticNumerals(invalidThousand));
    }

    @Test
    void fromGalacticNumeralsInvalidICTest() {
        String invalidNinetyNine = "IC";

        Assertions.assertEquals(0, galacticNumeralService.fromGalacticNumerals(invalidNinetyNine));
    }


    //Tests for converting numbers to numerals
    @Test
    void fromGalacticNumeralsValid1234Test() {
        String oneTwoThreeFour = "MCCXXXIV";

        Assertions.assertEquals(oneTwoThreeFour, galacticNumeralService.toGalacticNumerals(1234));
    }

    @Test
    void fromGalacticNumeralsValid999Test() {
        String oneTwoThreeFour = "CMXCIX";

        Assertions.assertEquals(oneTwoThreeFour, galacticNumeralService.toGalacticNumerals(999));
    }

    @Test
    void fromGalacticNumeralsInvalid4000Test() {
        String fourThousand = "MMMDMDM";

        Assertions.assertEquals(fourThousand, galacticNumeralService.toGalacticNumerals(4000));
    }

    @Test
    void fromGalacticNumeralsInvalid57800Test() {
        String fiftySevenThousandEightHundred = "MMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMM" +
                "DMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDCCC";

        Assertions.assertEquals(
                fiftySevenThousandEightHundred,
                galacticNumeralService.toGalacticNumerals(57800)
        );
    }
}
