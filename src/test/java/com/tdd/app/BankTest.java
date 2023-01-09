package com.tdd.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankTest {
  @Test
  public void testSimpleAddition() {
    Money five = Money.dollar(5);
    Expression sum = five.plus(five);
    Bank bank = new Bank();
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  public void testPlusReturnsSum() {
    Money five = Money.dollar(5);
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertEquals(five, sum.augend);
    assertEquals(five, sum.addend);
  }

  @Test
  public void testReduceSum() {
    Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
    Bank bank = new Bank();
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  public void testReduceMoney() {
    Bank bank = new Bank();
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void testIdentityRate() {
    assertEquals(1, new Bank(). rate("USD", "USD"));
  }

  @Test
  public void testReduceMoneyDifferentCurrency() {
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void testMixedAddition() {
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);

    Expression fiveBucks = Money.dollar(5);
    Expression tenFrancs = Money.franc(10);

    Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
    assertEquals(Money.dollar(10), result);
  }


  @Test
  public void testSumPlusMoney() {
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);

    Expression fiveBucks = Money.dollar(5);
    Expression tenFrancs = Money.franc(10);

    Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(15), result);
  }

  @Test
  public void testSumTimes() {
    Expression fiveBucks = Money.dollar(5); 
    Expression tenFrancs = Money.franc(10);

    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);
    Expression sum = new Sum(fiveBucks, tenFrancs). times(2); Money result = bank.reduce(sum, "USD"); assertEquals(Money.dollar(20), result);
  }
}
