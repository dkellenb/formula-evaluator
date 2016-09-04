package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;

/**
 * Calculates the sum based on the terms passed in the constructor.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericAdditionOperator<T>
    extends GenericStackableOperatorTerm<T> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param summand the base summand
   * @param summands further summands
   */
  @SafeVarargs
  public GenericAdditionOperator(Term<T> summand, Term<T>... summands) {
    super(summand, summands);
  }

  @Override
  public String getOperatorName() {
    return "+";
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param summand1 Operand 1.
   * @param summand2 Operand 2.
   * @return The result of the operation.
   */
  T calculate(FormulaEvaluatorConfiguration conf, T summand1, T summand2) {
    switch (conf.getPlusMinusNullHandling()) {
      case IDENTITY:
        return summand1 == null && summand2 == null
          ? null
          : super.calculate(conf, summand1 == null ? zero() : summand1, summand2 == null ? zero() : summand2);
      case INHERIT:
      default:
        return super.calculate(conf, summand1, summand2);
    }
  }

}
