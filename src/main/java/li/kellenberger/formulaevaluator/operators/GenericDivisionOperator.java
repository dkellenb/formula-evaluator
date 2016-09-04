package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <I> the input object needed for the evaluation
 * @param <R> the result object after the evaluation
 */
public abstract class GenericDivisionOperator<I extends VariableValueProvider, R>
    extends GenericStackableOperatorTerm<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator
   * @param denominators all denominators
   */
  @SafeVarargs
  public GenericDivisionOperator(Term<I, R> numerator, Term<I, R>... denominators) {
    super(numerator, denominators);
  }

  @Override
  public String getOperatorName() {
    return "/";
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param v1 Operand 1.
   * @param v2 Operand 2.
   * @return The result of the operation.
   */
  R calculate(FormulaEvaluatorConfiguration conf, R v1, R v2) {
    R denominator;
    switch (conf.getDivisionByZeroHandling()) {
      case ONE:
        denominator = isZero(v2) ? one() : v2;
        break;
      case NULL:
        if (isZero(v2)) {
          return null;
        }
        denominator = v2;
        break;
      case INHERIT:
      default:
        denominator = v2;
    }

    switch (conf.getDivisionNullHandling()) {
      case IDENTITY:
        return v1 == null
          ? null
          : super.calculate(conf, v1, denominator == null ? one() : denominator);
      case INHERIT:
      default:
        return super.calculate(conf, v1, denominator);
    }
  }

  /**
   * Is zero check.
   *
   * @param value the value
   * @return true if is zero
   */
  protected abstract boolean isZero(R value);

}
