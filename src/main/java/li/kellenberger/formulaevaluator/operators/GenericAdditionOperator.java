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
public abstract class GenericAdditionOperator<I extends VariableValueProvider, R>
    extends GenericStackableOperatorTerm<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param summand the base summand
   * @param summands further summands
   */
  @SafeVarargs
  public GenericAdditionOperator(Term<I, R> summand, Term<I, R>... summands) {
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
  R calculate(FormulaEvaluatorConfiguration conf, R summand1, R summand2) {
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
