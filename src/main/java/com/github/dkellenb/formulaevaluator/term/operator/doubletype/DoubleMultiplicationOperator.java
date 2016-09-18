package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericMultiplicationOperation;

/**
 * Double specific variable value provider.
 */
public class DoubleMultiplicationOperator
    extends GenericMultiplicationOperation<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param multiplier the multiplier term
   * @param multiplicands one or many multiplicands
   */
  @SafeVarargs
  public DoubleMultiplicationOperator(Term<Double> multiplier, Term<Double>... multiplicands) {
    super(multiplier, multiplicands);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf,
                                     Double multiplier, Double multiplicant) {
    return multiplier * multiplicant;
  }

}
