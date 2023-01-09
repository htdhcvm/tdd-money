package com.tdd.app;

public class Money implements Expression{
  protected int amount;
  protected String currency;

  Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public String currency(){
    return this.currency;
  }

  public String toString() {
    return amount + " " + currency;
  }

  public Money reduce(Bank bank, String to) {
    int rate = bank.rate(currency, to);
    return new Money(amount / rate, to);
  }

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public boolean equals(Object object) {
    Money money= (Money) object;
    return amount == money.amount
      && this.currency().equals(money.currency());
  }

  public Expression times(int multiplier) {
    return new Money(amount * multiplier, currency);
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }
}
