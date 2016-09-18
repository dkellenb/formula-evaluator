package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.dkellenb.formulaevaluator.VariableValueProvider;

import static java.util.Collections.unmodifiableSet;

/**
 * Generic variable value provider.
 *
 * @param <T> type of the values.
 * @param <I> self instance type
 */
public abstract class GenericVariableValueProvider<T, I extends VariableValueProvider<T, I>>
    implements VariableValueProvider<T, I> {

  private final Map<String, T> map;

  /**
   * C'tor with initial map.
   *
   * @param initialMap initial map
   */
  public GenericVariableValueProvider(Map<String, T> initialMap) {
    this.map = initialMap;
  }

  /**
   * C'tor with default initialized map.
   */
  public GenericVariableValueProvider() {
    this.map = new HashMap<>(8);
  }

  @Override
  @SuppressWarnings("unchecked")
  public I with(String variable) {
    map.put(variable, null);
    return (I) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public I with(String variable, BigDecimal value) {
    map.put(variable, value == null ? null : convert(value));
    return (I) this;
  }

  /**
   * Converts the value.
   *
   * @param value value
   * @return desired output
   */
  protected abstract T convert(BigDecimal value);

  /**
   * Converts the value.
   *
   * @param value value
   * @return desired output
   */
  protected abstract T convert(Double value);

  /**
   * Converts the value.
   *
   * @param value value
   * @return desired output
   */
  protected abstract T convert(Long value);

  /**
   * Converts the value.
   *
   * @param value value
   * @return desired output
   */
  protected abstract T convert(Integer value);

  @Override
  @SuppressWarnings("unchecked")
  public I with(String variable, Double value) {
    map.put(variable, value == null ? null : convert(value));
    return (I) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public I with(String variable, Long value) {
    map.put(variable, value == null ? null : convert(value));
    return (I) this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public I with(String variable, Integer value) {
    map.put(variable, value == null ? null : convert(value));
    return (I) this;
  }

  @Override
  public T getValue(String variable) {
    return map.get(variable);
  }

  @Override
  public Set<String> getVariables() {
    return unmodifiableSet(map.keySet());
  }

  @Override
  public String toString() {
    return map.entrySet().stream().map((e) -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(";"));
  }

}
