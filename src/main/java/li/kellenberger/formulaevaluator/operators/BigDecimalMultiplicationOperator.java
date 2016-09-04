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
public class BigDecimalMultiplicationOperator<I extends VariableValueProvider>
    extends GenericMultiplicationOperation<I, BigDecimal>
    implements BigDecimalOperator<I> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param multiplier the multiplier term
   * @param multiplicands one or many multiplicands
   */
  public BigDecimalMultiplicationOperator(Term<I, BigDecimal> multiplier, Term<I, BigDecimal>... multiplicands) {
    super(multiplier, multiplicands);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal multiplier, BigDecimal multiplicant) {
    return multiplier.multiply(multiplicant, conf.getMathContext());
  }

}
