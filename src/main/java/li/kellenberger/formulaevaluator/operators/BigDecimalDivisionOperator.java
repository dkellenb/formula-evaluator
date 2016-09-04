package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalDivisionOperator
    extends GenericDivisionOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominators one or many denominators
   */
  @SafeVarargs
  public BigDecimalDivisionOperator(Term<BigDecimal> numerator, Term<BigDecimal>... denominators) {
    super(numerator, denominators);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal numerator, BigDecimal denominator) {
    return numerator.divide(denominator, conf.getMathContext());
  }

  @Override
  protected boolean isZero(BigDecimal value) {
    return value != null && BigDecimal.ZERO.compareTo(value) == 0;
  }

}
