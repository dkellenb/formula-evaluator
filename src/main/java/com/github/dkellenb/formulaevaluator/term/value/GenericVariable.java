package com.github.dkellenb.formulaevaluator.term.value;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;

/**
 * Generic variable.
 *
 * @param <T> data type
 */
public class GenericVariable<T> implements Variable<T> {

  private final String variableName;

  /**
   * C'tor.
   *
   * @param variableName name of the variable
   */
  public GenericVariable(String variableName) {
    this.variableName = variableName;
  }

  @Override
  public T evaluate(VariableValueProvider<T> input, FormulaEvaluatorConfiguration configuration) {
    return input.getValue(variableName);
  }

  @Override
  public String printFormula() {
    return variableName;
  }

}
