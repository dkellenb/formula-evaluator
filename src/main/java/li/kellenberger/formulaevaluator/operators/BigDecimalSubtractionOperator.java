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
public class BigDecimalSubtractionOperator<I extends VariableValueProvider>
    extends GenericSubtractionOperator<I, BigDecimal>
    implements BigDecimalOperator<I> {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param minuend the minuend term
   * @param subtrahends one or many subtrahends
   */
  public BigDecimalSubtractionOperator(Term<I, BigDecimal> minuend, Term<I, BigDecimal>... subtrahends) {
    super(minuend, subtrahends);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    return v1.subtract(v2, conf.getMathContext());
  }

}
