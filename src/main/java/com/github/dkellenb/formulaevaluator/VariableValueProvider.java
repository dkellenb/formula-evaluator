package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Provides values for all variables passed at a later point in time.
 *
 * @param <T> type of the values stored
 * @param <I> self instance type
 */
public interface VariableValueProvider<T, I extends VariableValueProvider<T, I>> {

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

  /**
   * Adds a {@code null} value for a variable.
   *
   * @param variable the variable
   * @return itself
   */
  I with(String variable);

  /**
   * Adds a value for a variable.
   *
   * @param variable the variable
   * @param value the value
   * @return itself
   */
  I with(String variable, BigDecimal value);

  /**
   * Adds a value for a variable.
   *
   * @param variable the variable
   * @param value the value
   * @return itself
   */
  I with(String variable, Double value);

  /**
   * Adds a value for a variable.
   *
   * @param variable the variable
   * @param value the value
   * @return itself
   */
  I with(String variable, Integer value);

  /**
   * Adds a value for a variable.
   *
   * @param variable the variable
   * @param value the value
   * @return itself
   */
  I with(String variable, Long value);

}
