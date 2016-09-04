package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;

/**
 * BigDecimal specific variable value provider.
 */
public class BigDecimalAdditionOperator
    extends GenericAdditionOperator<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the summands.
   *
   * @param summand passend summand
   * @param summands passed summands
   */
  @SafeVarargs
  public BigDecimalAdditionOperator(Term<BigDecimal> summand, Term<BigDecimal>... summands) {
    super(summand, summands);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal summand1, BigDecimal summand2) {
    return summand1.add(summand2, conf.getMathContext());
  }

}
