package com.dsoumaila.coffeemachine;

import java.math.BigDecimal;

import static com.dsoumaila.coffeemachine.Drink.*;
import static java.math.BigDecimal.ZERO;

public class Pad {
    public static final BigDecimal COFFEE_OR_ORANGE_PRICE = new BigDecimal("0.6");
    public static final BigDecimal TEA_PRICE = new BigDecimal("0.4");
    public static final BigDecimal CHOCOLATE_PRICE = new BigDecimal("0.5");

    private Drink drink;
    private int quantityOfSugar;
    private BigDecimal price;
    private boolean isExtratHot;

    public Pad(Drink drink, int quantityOfSugar, BigDecimal price) {
        this.drink = drink;
        this.quantityOfSugar = quantityOfSugar;
        this.price = price;
        this.isExtratHot = false;
    }

    public Pad(Drink drink, int quantityOfSugar, BigDecimal price, boolean isExtratHot) {
        this(drink, quantityOfSugar, price);
        this.isExtratHot = isExtratHot;
    }

    public String getTypeOfDrink() {
        return  drink.getValue();
    }

    public boolean isOrange() {
        return getDrink().equals(ORANGE);
    }

    public BigDecimal getTheRestOfMoney() {
        if (isCoffeePriceMissing(drink, price)) {
            return COFFEE_OR_ORANGE_PRICE.subtract(price);
        }

        if (isTeaPriceMissing(drink, price)) {
            return TEA_PRICE.subtract(price);
        }

        if (isChocolatePriceMissing(drink, price)){
            return CHOCOLATE_PRICE.subtract(price);
        }

        if (isOrangePriceMissing(drink, price)) {
            return COFFEE_OR_ORANGE_PRICE.subtract(price);
        }

        return ZERO;
    }

    private boolean isChocolatePriceMissing(Drink drink, BigDecimal price) {
        return drink.equals(CHOCOLATE) && CHOCOLATE_PRICE.compareTo(price) >= 0;
    }

    private boolean isTeaPriceMissing(Drink drink, BigDecimal price) {
        return drink.equals(TEA) && TEA_PRICE.compareTo(price) >= 0;
    }

    private boolean isCoffeePriceMissing(Drink drink, BigDecimal price) {
        return drink.equals(COFFEE) && COFFEE_OR_ORANGE_PRICE.compareTo(price) >= 0;
    }

    private boolean isOrangePriceMissing(Drink drink, BigDecimal price) {
        return drink.equals(ORANGE) && COFFEE_OR_ORANGE_PRICE.compareTo(price) >= 0;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getQuantityOfSugar() {
        return quantityOfSugar;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isExtratHot() {
        return isExtratHot;
    }
}
