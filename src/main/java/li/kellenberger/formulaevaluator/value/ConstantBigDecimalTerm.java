package li.kellenberger.formulaevaluator.value;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.VariableValueProvider;

/**
 * BigDecimal Term.
 */
public class ConstantBigDecimalTerm implements Term<BigDecimal> {

  private final BigDecimal value;

  /**
   * Creates a BigDecimal Term.
   * @param value the value
   */
  public ConstantBigDecimalTerm(BigDecimal value) {
    this.value = value;
  }

  @Override
  public BigDecimal evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration conf) {
    return value;
  }

  @Override
  public String printFormula() {
    return value == null ? null : value.toString();
  }

}
