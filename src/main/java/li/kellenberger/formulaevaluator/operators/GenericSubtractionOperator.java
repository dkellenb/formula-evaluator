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
public abstract class GenericSubtractionOperator<I extends VariableValueProvider, R>
    extends GenericStackableOperatorTerm<I, R> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param minuend the minuend
   * @param subtrahends all subtrahends
   */
  @SafeVarargs
  public GenericSubtractionOperator(Term<I, R> minuend, Term<I, R>... subtrahends) {
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
  R calculate(FormulaEvaluatorConfiguration conf, R minuend, R subtrahend) {
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
