package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericDivisionOperator;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalDivisionOperator
    extends GenericDivisionOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominators one or many denominators
   */
  @SafeVarargs
  public BigDecimalDivisionOperator(Term<BigDecimal> numerator, Term<BigDecimal>... denominators) {
    super(numerator, denominators);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal numerator, BigDecimal denominator) {
    return numerator.divide(denominator, conf.getCalculationMathContext());
  }

  @Override
  protected boolean isZero(BigDecimal value) {
    return value != null && BigDecimal.ZERO.compareTo(value) == 0;
  }

}
