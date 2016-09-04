package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Fraction operator.
 *
 * @param <I> input value provider
 * @param <R> result type
 */
public abstract class GenericExponentiationOperatorTerm<I extends VariableValueProvider, R>
  extends GenericMultiplicationOperation<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base
   * @param exponent the exponent
   */
  public GenericExponentiationOperatorTerm(Term<I, R> base, Term<I, R> exponent) {
    super(base, exponent);
  }

  @Override
  public String getOperatorName() {
    return "^";
  }

}
