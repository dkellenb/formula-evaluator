package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <I> the input object needed for the evaluation
 * @param <R> the result object after the evaluation
 */
public abstract class GenericModuloOperator<I extends VariableValueProvider, R>
    extends GenericDivisionOperator<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator
   * @param divisor the divisor
   */
  public GenericModuloOperator(Term<I, R> numerator, Term<I, R> divisor) {
    super(numerator, divisor);
  }

  @Override
  public String getOperatorName() {
    return "%";
  }

}
