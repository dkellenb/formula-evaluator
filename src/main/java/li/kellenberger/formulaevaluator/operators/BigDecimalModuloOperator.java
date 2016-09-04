package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * BigDecimal specific variable value provider.
 *
 * @param <I> value provider as input
 */
public class BigDecimalModuloOperator<I extends VariableValueProvider>
    extends GenericModuloOperator<I, BigDecimal>
    implements BigDecimalOperator<I> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominator the denominator
   */
  public BigDecimalModuloOperator(Term<I, BigDecimal> numerator, Term<I, BigDecimal> denominator) {
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
