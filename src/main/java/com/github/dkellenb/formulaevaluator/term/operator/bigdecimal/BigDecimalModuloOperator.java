package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericModuloOperator;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalModuloOperator
    extends GenericModuloOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominator the denominator
   */
  public BigDecimalModuloOperator(Term<BigDecimal> numerator, Term<BigDecimal> denominator) {
    super(numerator, denominator);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal numerator, BigDecimal denominator) {
    return numerator.remainder(denominator, conf.getCalculationMathContext());
  }

  @Override
  protected boolean isZero(BigDecimal value) {
    return value != null && BigDecimal.ZERO.compareTo(value) == 0;
  }

}
