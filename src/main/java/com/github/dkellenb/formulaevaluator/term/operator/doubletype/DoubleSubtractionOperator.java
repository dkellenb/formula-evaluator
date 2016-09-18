package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericSubtractionOperator;

/**
 * Double specific variable value provider.
 */
public class DoubleSubtractionOperator
    extends GenericSubtractionOperator<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param minuend the minuend term
   * @param subtrahends one or many subtrahends
   */
  @SafeVarargs
  public DoubleSubtractionOperator(Term<Double> minuend, Term<Double>... subtrahends) {
    super(minuend, subtrahends);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, Double v1, Double v2) {
    return v1 - v2;
  }

}
