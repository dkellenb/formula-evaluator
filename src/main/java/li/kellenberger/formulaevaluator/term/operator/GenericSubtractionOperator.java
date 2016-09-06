package li.kellenberger.formulaevaluator.term.operator;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericSubtractionOperator<T>
    extends GenericStackableOperatorTerm<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param minuend the minuend
   * @param subtrahends all subtrahends
   */
  @SafeVarargs
  public GenericSubtractionOperator(Term<T> minuend, Term<T>... subtrahends) {
    super(minuend, subtrahends);
  }

  @Override
  public String getOperatorName() {
    return "-";
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param minuend Operand 1.
   * @param subtrahend Operand 2.
   * @return The result of the operation.
   */
  T calculate(FormulaEvaluatorConfiguration conf, T minuend, T subtrahend) {
    switch (conf.getPlusMinusNullHandling()) {
      case IDENTITY:
        return minuend == null && subtrahend == null
          ? null
          : super.calculate(conf, minuend == null ? zero() : minuend, subtrahend == null ? zero() : subtrahend);
      case INHERIT:
      default:
        return super.calculate(conf, minuend, subtrahend);
    }
  }

}
