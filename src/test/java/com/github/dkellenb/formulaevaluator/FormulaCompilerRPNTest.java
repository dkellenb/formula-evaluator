package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test Reverse Polish Notation.
 */
public class FormulaCompilerRPNTest {

  /** see method name. */
  @Test
  public void testSimpleRPNNotation() {
    assertEquals("1 2 +", FormulaCompiler.create("1+2").toRPN());
    assertEquals("1 2 4 / +", FormulaCompiler.create("1+2/4").toRPN());
    assertEquals("1 2 + 4 /", FormulaCompiler.create("(1+2)/4").toRPN());
    assertEquals("1.9 2.8 + 4.7 /", FormulaCompiler.create("(1.9+2.8)/4.7").toRPN());
    assertEquals("1.98 2.87 + 4.76 /", FormulaCompiler.create("(1.98+2.87)/4.76").toRPN());
    assertEquals("3 4 2 * 1 5 - 2 3 ^ ^ / +", FormulaCompiler.create("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3").toRPN());
  }

  /** see method name. */
  @Test
  public void testRPNWithFunctionNotation() {
    assertEquals("23.6 SIN", FormulaCompiler.create("SIN(23.6)").toRPN());
    assertEquals("-7 8 MAX", FormulaCompiler.create("MAX(-7,8)").toRPN());
    assertEquals("3.7 SIN 2.6 -8.0 MAX MAX", FormulaCompiler.create("MAX(SIN(3.7),MAX(2.6,-8.0))").toRPN());
  }

}
