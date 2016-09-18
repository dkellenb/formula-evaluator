package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;

import com.github.dkellenb.formulaevaluator.VariableValueProvider;

/**
 * Provides values for all variables passed at a later point in time.
 *
 * @param <T> type of the values stored
 * @param <I> self instance type
 */
public interface ModifiableVariableValueProvider<T, I extends ModifiableVariableValueProvider<T, I>>
  extends VariableValueProvider<T> {

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
