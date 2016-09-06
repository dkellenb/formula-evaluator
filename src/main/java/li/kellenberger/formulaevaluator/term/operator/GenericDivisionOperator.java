package li.kellenberger.formulaevaluator.term.operator;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericDivisionOperator<T>
    extends GenericStackableOperatorTerm<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator
   * @param denominators all denominators
   */
  @SafeVarargs
  public GenericDivisionOperator(Term<T> numerator, Term<T>... denominators) {
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
  T calculate(FormulaEvaluatorConfiguration conf, T v1, T v2) {
    T denominator;
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
  protected abstract boolean isZero(T value);

}
