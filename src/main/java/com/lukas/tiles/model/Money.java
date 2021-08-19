package com.lukas.tiles.model;

import java.util.Objects;

/**
 * The class which represents all money. Saved in cents
 */
public class Money {
    private long amount;

    public Money() {

    }

    public Money(long cents) {
        amount = cents;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void addAmount(long amount) {
        this.amount += amount;
    }

    /**
     * Sub amount only if the balance is high enough
     *
     * @param amount that gets removed in cent
     * @return if the transaction is successful
     */
    public boolean subAmountIfPossible(long amount) {
        if (this.amount < amount) {
            return false;
        }

        this.amount -= amount;
        return true;
    }

    /**
     * Force the subtraction, even if balance is not high enough
     *
     * @param amount that gets removed in cent
     */
    public void subAmount(long amount) {
        this.amount -= amount;
    }

    @Override
    public String toString() {
        long full = amount / 100;
        long cents = (amount - full * 100);
        String centsString = "" + cents;
        if (cents == 0) {
            centsString = "00";
        }
        return "$" + full + "." + centsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
