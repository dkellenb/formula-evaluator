package li.kellenberger.formulaevaluator.value;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * BigDecimal Term.
 *
 * @param <I> an arbitrary object type (has no effect)
 */
public class ConstantBigDecimalTerm<I extends VariableValueProvider> implements Term<I, BigDecimal> {

  private final BigDecimal value;

  /**
   * Creates a BigDecimal Term.
   * @param value the value
   */
  public ConstantBigDecimalTerm(BigDecimal value) {
    this.value = value;
  }

  @Override
  public BigDecimal evaluate(I input, FormulaEvaluatorConfiguration conf) {
    return value;
  }

  @Override
  public String printFormula() {
    return value == null ? null : value.toString();
  }

}
