package com.github.dkellenb.formulaevaluator.term.value;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;

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
