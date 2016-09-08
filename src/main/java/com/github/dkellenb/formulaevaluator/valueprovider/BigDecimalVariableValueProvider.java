package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.dkellenb.formulaevaluator.VariableValueProvider;

import static java.util.Collections.emptyMap;
import static java.util.Collections.unmodifiableSet;

/**
 * HashMap based value provider.
 */
public final class BigDecimalVariableValueProvider implements VariableValueProvider {

  private final Map<String, BigDecimal> map;

  /**
   * C'tor.
   * @param map default map
   */
  public BigDecimalVariableValueProvider(Map<String, BigDecimal> map) {
    this.map = new HashMap<>(map);
  }

  /**
   * Create.
   *
   * @return new instance
   */
  public static BigDecimalVariableValueProvider createValueProvider() {
    return new BigDecimalVariableValueProvider(emptyMap());
  }

  /**
   * Adds a value for a variable.
   *
   * @param variable the variable
   * @param value the value
   * @return itself
   */
  public BigDecimalVariableValueProvider with(String variable, BigDecimal value) {
    map.put(variable, value);
    return this;
  }

  @Override
  public BigDecimal getValue(String variable) {
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
