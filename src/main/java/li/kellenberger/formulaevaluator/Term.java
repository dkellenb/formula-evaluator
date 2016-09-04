package li.kellenberger.formulaevaluator;

/**
 * Calculator Term.
 *
 * @param <I> the input object needed for the evaluation (configuration, where to retrieve the value, ...)
 * @param <R> the result object after the evaluation
 */
public interface Term<I extends VariableValueProvider, R> {

  /**
   * Evaluates input I and returns output R.
   *
   * @param input the input
   * @param configuration the formula evaluator configuration
   *
   * @return the evaluated output
   */
  R evaluate(I input, FormulaEvaluatorConfiguration configuration);

  /**
   * Prints the calculation formula.
   *
   * @return the formula
   */
  String printFormula();

}
