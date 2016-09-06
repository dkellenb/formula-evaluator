package li.kellenberger.formulaevaluator.term.operator;

import java.math.BigDecimal;

/**
 * Tagging interface for all BigDecimal operators.
 */
interface BigDecimalOperator extends OperatorTerm<BigDecimal> {

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
