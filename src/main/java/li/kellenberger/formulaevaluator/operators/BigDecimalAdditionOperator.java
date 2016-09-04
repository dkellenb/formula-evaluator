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
public class BigDecimalAdditionOperator<I extends VariableValueProvider>
    extends GenericAdditionOperator<I, BigDecimal>
    implements BigDecimalOperator<I> {

  /**
   * Initializes the calculator based on the summands.
   *
   * @param summand passend summand
   * @param summands passed summands
   */
  public BigDecimalAdditionOperator(Term<I, BigDecimal> summand, Term<I, BigDecimal>... summands) {
    super(summand, summands);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal summand1, BigDecimal summand2) {
    return summand1.add(summand2, conf.getMathContext());
  }

}
