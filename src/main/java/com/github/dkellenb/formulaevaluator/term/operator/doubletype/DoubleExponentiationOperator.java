package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericExponentiationOperatorTerm;

/**
 * FRACTIONAL operation.
 */
public class DoubleExponentiationOperator
    extends GenericExponentiationOperatorTerm<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base term
   * @param exponent one or many exponent
   */
  public DoubleExponentiationOperator(Term<Double> base, Term<Double> exponent) {
    super(base, exponent);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf,
                                     Double base, Double exponent) {
    return Math.pow(base, exponent);
  }

}
