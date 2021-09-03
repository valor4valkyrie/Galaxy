package com.example.galaxy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals("2006", galacticNumeralService.fromGalacticNumerals(twoZeroZeroSix));
    }

    @Test
    void fromGalacticNumeralsValidMCMXLIVTest() {
        String nineTeenFourtyFour = "MCMXLIV";

        assertEquals("1944", galacticNumeralService.fromGalacticNumerals(nineTeenFourtyFour));
    }

    @Test
    void fromGalacticNumeralsValidCIXTest() {
        String oneHundredNine = "CIX";

        assertEquals("109", galacticNumeralService.fromGalacticNumerals(oneHundredNine));
    }

    @Test
    void fromGalacticNumeralsValidIXTest() {
        String nine = "IX";

        assertEquals("9", galacticNumeralService.fromGalacticNumerals(nine));
    }

    @Test
    void fromGalacticNumeralsValidXTest() {
        String nine = "X";

        assertEquals("10",galacticNumeralService.fromGalacticNumerals(nine));
    }

    @Test
    void fromGalacticNumeralsValidXCTest() {
        String ninety = "XC";

        assertEquals("90", galacticNumeralService.fromGalacticNumerals(ninety));
    }

    @Test
    void fromGalacticNumeralsValidXXIXTest() {
        String twentyNine = "XXIX";

        assertEquals("29",galacticNumeralService.fromGalacticNumerals(twentyNine));
    }

    @Test
    void fromGalacticNumeralsInvalidXXXXTest() {
        String fourThousand = "XXXX";

        assertEquals(GalacticNumeralService.INVALID_MESSAGE, galacticNumeralService.fromGalacticNumerals(fourThousand));
    }

    @Test
    void fromGalacticNumeralsInvalidIXXXXTest() {
        String invalid = "IXXXX";

        assertEquals(GalacticNumeralService.INVALID_MESSAGE, galacticNumeralService.fromGalacticNumerals(invalid));
    }

    @Test
    void fromGalacticNumeralsInvalidDDTest() {
        String invalidThousand = "DD";

        assertEquals(GalacticNumeralService.INVALID_MESSAGE, galacticNumeralService.fromGalacticNumerals(invalidThousand));
    }

    @Test
    void fromGalacticNumeralsInvalidICTest() {
        String invalidNinetyNine = "IC";

        assertEquals(GalacticNumeralService.INVALID_MESSAGE, galacticNumeralService.fromGalacticNumerals(invalidNinetyNine));
    }


    //Tests for converting numbers to numerals
    @Test
    void fromGalacticNumeralsValid1234Test() {
        String oneTwoThreeFour = "MCCXXXIV";

        assertEquals(oneTwoThreeFour, galacticNumeralService.toGalacticNumerals(1234));
    }

    @Test
    void fromGalacticNumeralsValid999Test() {
        String oneTwoThreeFour = "CMXCIX";

        assertEquals(oneTwoThreeFour, galacticNumeralService.toGalacticNumerals(999));
    }

    @Test
    void fromGalacticNumeralsInvalid4000Test() {
        String fourThousand = "MMMDMDM";

        assertEquals(fourThousand, galacticNumeralService.toGalacticNumerals(4000));
    }

    @Test
    void fromGalacticNumeralsInvalid57800Test() {
        String fiftySevenThousandEightHundred = "MMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMM" +
                "DMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDMDMMMDCCC";

        assertEquals(fiftySevenThousandEightHundred, galacticNumeralService.toGalacticNumerals(57800));
    }
}
