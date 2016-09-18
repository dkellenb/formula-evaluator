package com.github.dkellenb.formulaevaluator.term.operator.doubletype;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.GenericAdditionOperator;

/**
 * Double specific variable value provider.
 */
public class DoubleAdditionOperator
    extends GenericAdditionOperator<Double>
    implements DoubleOperator {

  /**
   * Initializes the calculator based on the summands.
   *
   * @param summand passend summand
   * @param summands passed summands
   */
  @SafeVarargs
  public DoubleAdditionOperator(Term<Double> summand, Term<Double>... summands) {
    super(summand, summands);
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, Double summand1, Double summand2) {
    return summand1 + summand2;
  }

}
