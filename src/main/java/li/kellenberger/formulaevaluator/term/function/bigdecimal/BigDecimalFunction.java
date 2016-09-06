package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.term.function.FunctionTerm;

/**
 * Tagging interface for BigDecimal functions.
 */
public interface BigDecimalFunction extends FunctionTerm<BigDecimal> {

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

}
