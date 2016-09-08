package com.github.dkellenb.formulaevaluator.term.value;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;

/**
 * BigDecimal variable.
 */
public class BigDecimalVariable implements Variable<BigDecimal> {

  private final String variableName;

  /**
   * C'tor.
   *
   * @param variableName name of the variable
   */
  public BigDecimalVariable(String variableName) {
    this.variableName = variableName;
  }

  @Override
  public BigDecimal evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration configuration) {
    return input.getValue(variableName);
  }

  @Override
  public String printFormula() {
    return variableName;
  }

}
