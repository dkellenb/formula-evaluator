package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericModuloOperator;

/**
 * Double specific variable value provider.
 */
public class DoubleModuloOperator
    extends GenericModuloOperator<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominator the denominator
   */
  public DoubleModuloOperator(Term<Double> numerator, Term<Double> denominator) {
    super(numerator, denominator);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf,
                                     Double numerator, Double denominator) {
    return numerator % denominator;
  }

  @Override
  protected boolean isZero(Double value) {
    return value != null && zero().equals(value);
  }

}
