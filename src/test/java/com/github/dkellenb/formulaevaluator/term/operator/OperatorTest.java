package com.github.dkellenb.formulaevaluator.term.operator;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.term.value.ConstantTerm;
import com.github.dkellenb.formulaevaluator.term.value.GenericVariable;

/**
 * Generic test class for all operators
 */
public class OperatorTest {

  /**
   * Creation of a variable (used for printFormula testing).
   *
   * @param name variable name
   * @return new instance
   */
  protected static GenericVariable<BigDecimal> v(String name) {
    return new GenericVariable<>(name);
  }

  /**
   * Creation of constant value (used for calculation testing).
   *
   * @param value the value
   * @return constant term
   */
  protected static ConstantTerm<BigDecimal> c(BigDecimal value) {
    return new ConstantTerm<>(value);
  }

  protected static final BigDecimal TWO = new BigDecimal(2);

  protected static final BigDecimal THREE = new BigDecimal(3);

  protected static final BigDecimal FOUR = new BigDecimal(4);

  protected static final BigDecimal FIVE = new BigDecimal(5);

  protected static final BigDecimal SIX = new BigDecimal(6);

  protected static final BigDecimal EIGHT = new BigDecimal(8);

  protected static final BigDecimal NINE = new BigDecimal(9);

}
