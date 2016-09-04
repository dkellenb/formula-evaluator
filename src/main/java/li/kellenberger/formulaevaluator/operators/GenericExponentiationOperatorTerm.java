package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.Term;

/**
 * Fraction operator.
 *
 * @param <T> result type
 */
public abstract class GenericExponentiationOperatorTerm<T>
  extends GenericMultiplicationOperation<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base
   * @param exponent the exponent
   */
  public GenericExponentiationOperatorTerm(Term<T> base, Term<T> exponent) {
    super(base, exponent);
  }

  @Override
  public String getOperatorName() {
    return "^";
  }

}
