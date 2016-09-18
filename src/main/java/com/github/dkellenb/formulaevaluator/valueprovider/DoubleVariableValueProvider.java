package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.util.Set;

import com.github.dkellenb.formulaevaluator.VariableValueProvider;

/**
 * Variable value provider wrapping a BigDecimalVariableValueProvider.
 */
public class DoubleVariableValueProvider implements VariableValueProvider<Double> {

  private final BigDecimalVariableValueProvider bigDecimalProvider;

  /**
   * C'tor.
   * @param otherProvider another big decimal variable value provider.
   */
  public DoubleVariableValueProvider(BigDecimalVariableValueProvider otherProvider) {
    this.bigDecimalProvider = otherProvider;
  }

  @Override
  public Double getValue(String variable) {
    BigDecimal value = bigDecimalProvider.getValue(variable);
    return value == null ? null : value.doubleValue();
  }

  @Override
  public Set<String> getVariables() {
    return bigDecimalProvider.getVariables();
  }
}
