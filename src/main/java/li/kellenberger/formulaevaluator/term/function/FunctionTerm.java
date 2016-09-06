package li.kellenberger.formulaevaluator.term.function;

import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;

/** Function Term.
 *
 * @param <T> type
 */
public interface FunctionTerm<T> extends Term<T> {

  /**
   * Function name (pattern).
   *
   * @return Function name (pattern)
   */
  String getName();

  /**
   * Implementation for this function.
   *
   * @param conf the configuration.
   * @param parameters the parameters
   *
   * @return The result of the operation.
   */
  T calculateDefault(FormulaEvaluatorConfiguration conf, List<T> parameters);

  /**
   * Implementation of zero.
   *
   * @return zero
   */
  T zero();

}
