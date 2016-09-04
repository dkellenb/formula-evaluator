package li.kellenberger.formulaevaluator.operators;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.VariableValueProvider;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.util.Arrays.asList;

/**
 * Tagging interface for all value calculators.
 *
 * @param <I> the input object needed for the evaluation
 * @param <R> the result object after the evaluation
 */
public abstract class GenericOperatorTerm<I extends VariableValueProvider, R> implements OperatorTerm<I, R> {

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param v1 Operand 1.
   * @param v2 Operand 2.
   * @return The result of the operation.
   */
  R calculate(FormulaEvaluatorConfiguration conf, R v1, R v2) {
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
