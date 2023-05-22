package com.dsoumaila.coffeemachine;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.dsoumaila.coffeemachine.Drink.ORANGE;
import static com.dsoumaila.coffeemachine.Drink.TEA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkMachineTest {

    @Test
    void should_display_type_of_drink() {
        // GIVEN
        Pad pad = new Pad(TEA, 0, new BigDecimal("0.4"));
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("T::", message);
    }

    @Test
    void should_display_drink_with_sugar_and_stick() {
        // GIVEN
        Pad pad = new Pad(TEA, 1, new BigDecimal("0.4"));
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("T:1:0", message);
    }

    @Test
    void should_display_drink_with_number_of_sugar_and_stick() {
        // GIVEN
        Pad pad = new Pad(TEA, 3, new BigDecimal("0.4"));
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("T:3:0", message);
    }

    @Test
    void should_warn_user_for_missing_money() {
        // GIVEN
        Pad pad = new Pad(TEA, 3, new BigDecimal("0.2"));
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("M:Missing 0.2 €", message);
    }

    @Test
    void should_make_orange_juice() {
        // GIVEN
        Pad pad = new Pad(ORANGE, 3, new BigDecimal("0.6"));
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("O::", message);
    }

    @Test
    void should_display_extra_hot_drink() {
        // GIVEN
        Pad pad = new Pad(TEA, 3, new BigDecimal("0.4"), true);
        CoffeeMachine coffeeMachine = new CoffeeMachine(pad);

        // WHEN
        String message = coffeeMachine.display();

        // THEN
        assertEquals("Th:3:0", message);
    }
}
