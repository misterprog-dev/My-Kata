package com.dsoumaila.coffeemachine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.dsoumaila.coffeemachine.Drink.*;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

class PadTest {
    @Test
    public void should_get_the_rest_of_money_for_coffee() {
        // GIVEN
        Pad pad = new Pad(COFFEE, 2, new BigDecimal("0.2"));

        // WHEN
        BigDecimal result = pad.getTheRestOfMoney();

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("0.4"));
    }

    @Test
    public void should_get_the_rest_of_money_for_tea() {
        // GIVEN
        Pad pad = new Pad(TEA, 2, new BigDecimal("0.2"));

        // WHEN
        BigDecimal result = pad.getTheRestOfMoney();

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("0.2"));
    }

    @Test
    public void should_get_the_rest_of_money_for_chocolate() {
        // GIVEN
        Pad pad = new Pad(CHOCOLATE, 2, new BigDecimal("0.2"));

        // WHEN
        BigDecimal result = pad.getTheRestOfMoney();

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("0.3"));
    }

    @Test
    public void should_get_the_rest_of_money_for_orange() {
        // GIVEN
        Pad pad = new Pad(ORANGE, 2, new BigDecimal("0.2"));

        // WHEN
        BigDecimal result = pad.getTheRestOfMoney();

        // THEN
        assertThat(result).isEqualTo(new BigDecimal("0.4"));
    }

    @Test
    public void should_get_zero_for_the_rest_of_money() {
        // GIVEN
        Pad pad = new Pad(TEA, 2, new BigDecimal("0.4"));

        // WHEN
        BigDecimal result = pad.getTheRestOfMoney();

        // THEN
        assertThat(result).isEqualByComparingTo(ZERO);
    }
}
