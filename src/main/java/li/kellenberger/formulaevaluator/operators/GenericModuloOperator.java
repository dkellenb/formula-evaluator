package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.Term;

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
    return "%";
  }

}
