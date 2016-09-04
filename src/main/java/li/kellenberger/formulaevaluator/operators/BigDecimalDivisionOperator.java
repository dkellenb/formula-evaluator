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
public class BigDecimalDivisionOperator<I extends VariableValueProvider>
    extends GenericDivisionOperator<I, BigDecimal>
    implements BigDecimalOperator<I> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param numerator the numerator term
   * @param denominators one or many denominators
   */
  public BigDecimalDivisionOperator(Term<I, BigDecimal> numerator, Term<I, BigDecimal>... denominators) {
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
