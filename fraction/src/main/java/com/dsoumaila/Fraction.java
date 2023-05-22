package com.dsoumaila;

public class Fraction {
    private final int numerator;
    private final int denominator;
    public static final Fraction INFINITE = new Fraction(0, Integer.MAX_VALUE) {
        @Override
        public Fraction add(Fraction fraction) {
            return this;
        }

        @Override
        public Fraction substract(Fraction fraction) {
            return this;
        }
    };

    public static final Fraction ZERO = new Fraction(0, 1) {
        @Override
        public Fraction add(Fraction fraction) {
            return fraction;
        }
    };

    public static final Fraction UNDEFINED = new Fraction(0, 0) {
        @Override
        public Fraction add(Fraction fraction) {
            return this;
        }
    };

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction createFraction(Integer numerator, Integer denominator) {
        if (denominator == 0) {
            return INFINITE;
        }

        if (numerator == 0) {
            return ZERO;
        }
        return new Fraction(numerator, denominator);
    }

    public Fraction add(Fraction fraction) {
        int numerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        int denominator = this.denominator * fraction.denominator;
        Fraction result = new Fraction(numerator, denominator);
        return result.simplify();
    }

    public Fraction substract(Fraction secondFraction) {
        return this.add(secondFraction.opposite());
    }

    public Fraction simplify() {
        Integer pgcd = PGCD.pgcd(numerator, denominator);
        return new Fraction(numerator/pgcd, denominator/pgcd);
    }

    public Fraction opposite() {
        return new Fraction(-numerator, denominator);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        return fraction.numerator == numerator && fraction.denominator == denominator;
    }
}
