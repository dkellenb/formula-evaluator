package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.term.operator.OperatorTerm;

/**
 * Tagging interface for all BigDecimal operators.
 */
interface BigDecimalOperator extends OperatorTerm<BigDecimal> {

  /**
   * Zero.
   * @return zero
   */
  default BigDecimal zero() {
    return BigDecimal.ZERO;
  }

  /**
   * One.
   * @return one
   */
  default BigDecimal one() {
    return BigDecimal.ONE;
  }

  /**
   * Returns the result class.
   *
   * @return the result class.
   */
  default Class<BigDecimal> getResultClass() {
    return BigDecimal.class;
  }

}
