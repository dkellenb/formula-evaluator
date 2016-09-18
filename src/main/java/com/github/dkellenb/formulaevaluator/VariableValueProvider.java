package com.github.dkellenb.formulaevaluator;

import java.util.Set;

/**
 * Provides values for all variables passed at a later point in time.
 *
 * @param <T> type of the values stored
 */
public interface VariableValueProvider<T> {

  /**
   * Returns the value for a given variable.
   *
   * @param variable the variable name
   *
   * @return the value (may return null)
   */
  T getValue(String variable);

  /**
   * Returns a list with all variables.
   *
   * @return list with all variables
   */
  Set<String> getVariables();

}
