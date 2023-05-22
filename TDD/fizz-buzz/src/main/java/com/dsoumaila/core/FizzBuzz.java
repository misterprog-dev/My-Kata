package com.dsoumaila.core;

public class FizzBuzz {

    public static final int FizzMultiplier = 3;
    public static final int BuzzMultiplier = 5;
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";

    private Integer value;

    /**
     * Constructor with parameters
     *
     * @param value the value to use this Kata
     */
    public FizzBuzz(Integer value) {
        this.value = value;
    }

    /**
     * Show value
     *
     * @return the string of value
     */
    public String print() {
        if (isMultipleOf(value, FizzMultiplier) && isMultipleOf(value, BuzzMultiplier))
            return FIZZ + BUZZ;
        if (isMultipleOf(value, FizzMultiplier))
            return FIZZ;
        if (isMultipleOf(value, BuzzMultiplier))
            return BUZZ;
        return value.toString();
    }

    /**
     * Method to have multiple.
     *
     * @param number the number to have multiple.
     * @param multiple the number of number.
     * @return {Boolean}
     */
    private boolean isMultipleOf(Integer number, Integer multiple) {
        return number % multiple == 0;
    }
}
