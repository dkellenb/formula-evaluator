package com.github.dkellenb.formulaevaluator.term.operator;

import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericModuloOperator<T>
    extends GenericDivisionOperator<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator
   * @param divisor the divisor
   */
  public GenericModuloOperator(Term<T> numerator, Term<T> divisor) {
    super(numerator, divisor);
  }

  @Override
  public String getOperatorName() {
    return Operator.MODULO.getOperatorName();
  }

}
