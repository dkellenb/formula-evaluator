package com.github.dkellenb.formulaevaluator.valueprovider;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

/**
 * HashMap based value provider.
 */
public final class BigDecimalVariableValueProvider
    extends GenericVariableValueProvider<BigDecimal, BigDecimalVariableValueProvider> {

  private final MathContext conversionMathContext;

  /**
   * C'tor.
   */
  public BigDecimalVariableValueProvider() {
    super();
    this.conversionMathContext = MathContext.DECIMAL128;
  }

  /**
   * C'tor.
   * @param map default map
   */
  public BigDecimalVariableValueProvider(Map<String, BigDecimal> map) {
    this(map, MathContext.DECIMAL128);
  }

  /**
   * C'tor.
   * @param map default map
   * @param conversionMathContext the math context used for conversion of values
   */
  public BigDecimalVariableValueProvider(Map<String, BigDecimal> map, MathContext conversionMathContext) {
    super(map);
    this.conversionMathContext = conversionMathContext;
  }


  /**
   * Create.
   *
   * @return new instance
   */
  public static BigDecimalVariableValueProvider createValueProvider() {
    return new BigDecimalVariableValueProvider();
  }

  @Override
  public BigDecimal convert(BigDecimal value) {
    return value;
  }

  @Override
  public BigDecimal convert(Double value) {
    return new BigDecimal(value, conversionMathContext);
  }

  @Override
  public BigDecimal convert(Long value) {
    return BigDecimal.valueOf(value);
  }

  @Override
  public BigDecimal convert(Integer value) {
    return BigDecimal.valueOf(value);
  }

}
