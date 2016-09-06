package li.kellenberger.formulaevaluator.term.operator;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalModuloOperator
    extends GenericModuloOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominator the denominator
   */
  public BigDecimalModuloOperator(Term<BigDecimal> numerator, Term<BigDecimal> denominator) {
    super(numerator, denominator);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal numerator, BigDecimal denominator) {
    return numerator.remainder(denominator, conf.getMathContext());
  }

  @Override
  protected boolean isZero(BigDecimal value) {
    return value != null && BigDecimal.ZERO.compareTo(value) == 0;
  }

}
