package com.github.dkellenb.formulaevaluator.term.value;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;
import lombok.AllArgsConstructor;

/**
 * BigDecimal variable.
 */
@AllArgsConstructor
public class BigDecimalVariable implements Variable<BigDecimal> {

  private final String variableName;

  @Override
  public BigDecimal evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration configuration) {
    return input.getValue(variableName);
  }

  @Override
  public String printFormula() {
    return variableName;
  }

}
