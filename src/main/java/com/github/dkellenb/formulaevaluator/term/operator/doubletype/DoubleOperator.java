package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.term.operator.OperatorTerm;

/**
 * Tagging interface for all Double operators.
 */
interface DoubleOperator extends OperatorTerm<Double> {

  /**
   * Zero.
   * @return zero
   */
  default Double zero() {
    return 0d;
  }

  /**
   * One.
   * @return one
   */
  default Double one() {
    return 1d;
  }

  /**
   * Returns the result class.
   *
   * @return the result class.
   */
  default Class<Double> getResultClass() {
    return Double.class;
  }

}
