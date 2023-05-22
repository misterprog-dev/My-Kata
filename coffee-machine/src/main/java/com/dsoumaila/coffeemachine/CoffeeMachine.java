package com.dsoumaila.coffeemachine;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class CoffeeMachine {
    private Pad pad;

    public CoffeeMachine(Pad pad) {
        this.pad = pad;
    }

    public String display() {
        return message(pad.getTheRestOfMoney());
    }

    private String message(BigDecimal restOfMoney) {
        if (restOfMoney.compareTo(ZERO) > 0) {
            return "M:Missing " + restOfMoney.setScale(1) + " €";
        }

        if (pad.isOrange()) {
            return pad.getTypeOfDrink() + "::";
        }

        String drink = pad.getTypeOfDrink() + (pad.isExtratHot() ? "h" : "");

        return drink + (pad.getQuantityOfSugar() == 0 ? "::" : ":" + pad.getQuantityOfSugar() + ":0");
    }
}
