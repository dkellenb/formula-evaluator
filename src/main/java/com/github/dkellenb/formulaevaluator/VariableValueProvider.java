package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Provides values for all variables passed at a later point in time.
 */
public interface VariableValueProvider {

  /**
   * Returns the value for a given variable.
   *
   * @param variable the variable name
   *
   * @return the value (may return null)
   */
  BigDecimal getValue(String variable);

  /**
   * Returns a list with all variables.
   *
   * @return list with all variables
   */
  Set<String> getVariables();

}
