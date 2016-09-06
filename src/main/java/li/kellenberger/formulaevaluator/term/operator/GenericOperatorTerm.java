package li.kellenberger.formulaevaluator.term.operator;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.util.Arrays.asList;

/**
 * Tagging interface for all value calculators.
 *
 * @param <T> the result object after the evaluation
 */
public abstract class GenericOperatorTerm<T> implements OperatorTerm<T> {

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param v1 Operand 1.
   * @param v2 Operand 2.
   * @return The result of the operation.
   */
  T calculate(FormulaEvaluatorConfiguration conf, T v1, T v2) {
    switch (conf.getDefaultNullHandling()) {
      case NULL:
        return v1 == null || v2 == null ? null : calculateDefault(conf, v1, v2);
      case ZERO:
        return calculateDefault(conf, v1 == null ? zero() : v1, v2 == null ? zero() : v2);
      default:
        if (v1 == null || v2 == null) {
          throw new FormulaEvaluatorNullArgumentException(this.getOperatorName(), asList(v1, v2));
        }
        return calculateDefault(conf, v1, v2);
    }
  }

}
