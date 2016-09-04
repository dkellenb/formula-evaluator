package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * Tagging interface for all BigDecimal operators.
 *
 * @param <I> the input object needed for the evaluation (configuration, where to retrieve the value, ...)
 */
interface BigDecimalOperator<I extends VariableValueProvider> extends OperatorTerm<I, BigDecimal> {

  /**
   * Zero.
   * @return zero
   */
  default BigDecimal zero() {
    return BigDecimal.ZERO;
  }

  /**
   * One.
   * @return one
   */
  default BigDecimal one() {
    return BigDecimal.ONE;
  }

  /**
   * Returns the result class.
   *
   * @return the result class.
   */
  default Class<BigDecimal> getResultClass() {
    return BigDecimal.class;
  }

}
