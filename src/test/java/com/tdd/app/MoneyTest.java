package com.tdd.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class MoneyTest {
  @Test
  public void testEqualityDollar() {
    assertTrue(Money.dollar(5).equals(Money.dollar(5)));
    assertFalse(Money.dollar(5).equals(Money.dollar(6)));
  }

  @Test
  public void testEqualityFranc() {
    assertTrue(Money.franc(5).equals(Money.franc(5)));
    assertFalse(Money.franc(5).equals(Money.franc(6)));
  }
  @Test
  public void testCurrency() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  public void testSimpleAddition() {
    Expression sum = Money.dollar(5).plus(Money.dollar(5)).reduce(new Bank(), "USD");
    assertEquals(Money.dollar(10), sum);
  }
}
