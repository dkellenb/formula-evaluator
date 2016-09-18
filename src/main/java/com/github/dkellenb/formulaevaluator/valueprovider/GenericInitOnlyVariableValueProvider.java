package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Generic variable value provider.
 *
 * @param <T> type of the values.
 */
public class GenericInitOnlyVariableValueProvider<T>
    extends GenericVariableValueProvider<T, GenericInitOnlyVariableValueProvider<T>> {

  /**
   * C'tor.
   *
   * @param initialValues initial values
   */
  public GenericInitOnlyVariableValueProvider(Map<String, T> initialValues) {
    super(initialValues);
  }

  @Override
  public T convert(BigDecimal value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T convert(Double value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T convert(Integer value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T convert(Long value) {
    throw new UnsupportedOperationException();
  }

}
