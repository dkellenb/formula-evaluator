package li.kellenberger.formulaevaluator;

/**
 * Calculator Term.
 *
 * @param <T> the result object after the evaluation
 */
public interface Term<T> {

  /**
   * Evaluates input I and returns output T.
   *
   * @param input the input
   * @param configuration the formula evaluator configuration
   *
   * @return the evaluated output
   */
  T evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration configuration);

  /**
   * Prints the calculation formula.
   *
   * @return the formula
   */
  String printFormula();

}
