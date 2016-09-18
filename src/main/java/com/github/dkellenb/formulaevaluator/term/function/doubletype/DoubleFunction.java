package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import com.github.dkellenb.formulaevaluator.term.function.FunctionTerm;

/**
 * Tagging interface for BigDecimal functions.
 */
public interface DoubleFunction extends FunctionTerm<Double> {

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

}
