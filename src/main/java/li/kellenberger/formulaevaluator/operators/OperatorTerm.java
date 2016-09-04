package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;

/**
 * Operator Term.
 *
 * @param <T> type
 */
interface OperatorTerm<T> extends Term<T> {

  /**
   * Operators name (pattern).
   * @return Operators name (pattern)
   */
  String getOperatorName();

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param v1 Operand 1.
   * @param v2 Operand 2.
   * @return The result of the operation.
   */
  T calculateDefault(FormulaEvaluatorConfiguration conf, T v1, T v2);

  /**
   * Returns the result class.
   *
   * @return the result class.
   */
  Class<T> getResultClass();

  /**
   * Implementation of zero.
   *
   * @return zero
   */
  T zero();

  /**
   * Implementation of one.
   *
   * @return one
   */
  T one();

}
