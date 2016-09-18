package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericDivisionOperator;

/**
 * Double specific variable value provider.
 */
public class DoubleDivisionOperator
    extends GenericDivisionOperator<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominators one or many denominators
   */
  @SafeVarargs
  public DoubleDivisionOperator(Term<Double> numerator, Term<Double>... denominators) {
    super(numerator, denominators);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf,
                                 Double numerator, Double denominator) {
    return numerator / denominator;
  }

  @Override
  protected boolean isZero(Double value) {
    return value != null && zero().equals(value);
  }

}
