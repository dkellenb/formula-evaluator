package li.kellenberger.formulaevaluator.term.operator;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalMultiplicationOperator
    extends GenericMultiplicationOperation<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param multiplier the multiplier term
   * @param multiplicands one or many multiplicands
   */
  @SafeVarargs
  public BigDecimalMultiplicationOperator(Term<BigDecimal> multiplier, Term<BigDecimal>... multiplicands) {
    super(multiplier, multiplicands);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal multiplier, BigDecimal multiplicant) {
    return multiplier.multiply(multiplicant, conf.getMathContext());
  }

}
