package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Formula Evaluator Configuration.
 */
public class FormulaEvaluatorConfiguration {

  private MathContext calculationMathContext = MathContext.DECIMAL128;

  private MathContext resultMathContext = MathContext.DECIMAL32;

  private DefaultNullHandling defaultNullHandling = DefaultNullHandling.EXCEPTION;

  private BasicOperationsNullHandling plusMinusNullHandling = BasicOperationsNullHandling.INHERIT;

  private BasicOperationsNullHandling multiplicationNullHandling = BasicOperationsNullHandling.INHERIT;

  private BasicOperationsNullHandling divisionNullHandling = BasicOperationsNullHandling.INHERIT;

  private DivisionByZeroHandling divisionByZeroHandling = DivisionByZeroHandling.INHERIT;

  private Class<? extends Number> baseType = BigDecimal.class;

  private boolean modifiable = true;

  /**
   * Default c'tor with default values.
   */
  public FormulaEvaluatorConfiguration() {

  }

  /**
   * Modifiable c'tor.
   *
   * @param modifiable if instance shall be modifiable
   */
  FormulaEvaluatorConfiguration(boolean modifiable) {
    this.modifiable = modifiable;
  }

  /**
   * Copy c'tor.
   *
   * @param configuration the configuration to be copied
   */
  public FormulaEvaluatorConfiguration(FormulaEvaluatorConfiguration configuration) {
    this.calculationMathContext = configuration.calculationMathContext;
    this.resultMathContext = configuration.resultMathContext;
    this.defaultNullHandling = configuration.defaultNullHandling;
    this.plusMinusNullHandling = configuration.plusMinusNullHandling;
    this.multiplicationNullHandling = configuration.multiplicationNullHandling;
    this.divisionNullHandling = configuration.divisionNullHandling;
    this.divisionByZeroHandling = configuration.divisionByZeroHandling;
    this.baseType = configuration.baseType;
  }

  /**
   * Sets the status of this configuration to be not modifiable (Based on the value at construction or accpetance time).
   */
  public void setUnmodifiable() {
    this.modifiable = false;
  }

  /**
   * Returns @code{true} if instance is modifiable.
   * @return @code{true} if modifiable
   */
  boolean isModifiable() {
    return this.modifiable;
  }

  /**
   * Sets the default handling for null values.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setDefaultNullHandling(DefaultNullHandling handling) {
    checkModifiable();
    this.defaultNullHandling = handling;
    return this;
  }

  /**
   * Sets the precision for expression evaluation.
   *
   * @param precision the new precision.
   */
  public void setPrecision(int precision) {
    checkModifiable();
    calculationMathContext = new MathContext(precision, calculationMathContext.getRoundingMode());
    resultMathContext = calculationMathContext;
  }

  /**
   * Sets the calculation precision for expression evaluation.
   *
   * @param precision the new result precision
   */
  public void setCalculationPrecision(int precision) {
    checkModifiable();
    resultMathContext = new MathContext(precision, resultMathContext.getRoundingMode());
  }

  /**
   * Sets the result and calculation precision for expression evaluation.
   *
   * @param precision the new result precision
   */
  public void setResultPrecision(int precision) {
    checkModifiable();
    resultMathContext = new MathContext(precision, resultMathContext.getRoundingMode());
  }

  /**
   * Sets the rounding mode for expression evaluation.
   *
   * @param roundingMode the new rounding mode.
   */
  public void setRoundingMode(RoundingMode roundingMode) {
    checkModifiable();
    calculationMathContext = new MathContext(calculationMathContext.getPrecision(), roundingMode);
    resultMathContext = new MathContext(resultMathContext.getPrecision(), roundingMode);
  }

  /**
   * Sets the default handling for + and - operations for null values.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setPlusMinusNullHandling(BasicOperationsNullHandling handling) {
    checkModifiable();
    this.plusMinusNullHandling = handling;
    return this;
  }

  /**
   * Sets the default handling for * and ^ operations for null values.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setMultiplicationNullHandling(BasicOperationsNullHandling handling) {
    checkModifiable();
    this.multiplicationNullHandling = handling;
    return this;
  }

  /**
   * Sets the default handling for / and % operations for null values.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setDivisionNullHandling(BasicOperationsNullHandling handling) {
    checkModifiable();
    this.divisionNullHandling = handling;
    return this;
  }

  /**
   * Sets the default handling for / and % operations for division by zero.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setDivisionByZeroHandling(DivisionByZeroHandling handling) {
    checkModifiable();
    this.divisionByZeroHandling = handling;
    return this;
  }

  /**
   * Sets based on which type the calculations shall be performed. At the moment only BigDecimal and Double are
   * supported.
   *
   * @param clazz base type
   * @return self
   */
  public FormulaEvaluatorConfiguration setBaseCalculationType(Class<? extends Number> clazz) {
    checkModifiable();
    if (!BigDecimal.class.equals(clazz) && !Double.class.equals(clazz)) {
      throw new IllegalArgumentException("Only BigDecimal or Double as base calculation types are supported");
    }
    this.baseType = clazz;
    return this;
  }

  /**
   * Gets the MathContext used for calculations.
   * @return not null
   */
  public MathContext getCalculationMathContext() {
    return calculationMathContext;
  }

  /**
   * Gets the MathContext used for rounding at the end.
   * @return not null
   */
  public MathContext getResultMathContext() {
    return resultMathContext;
  }

  /**
   * Gets the configuration for the default null handling.
   * @return not null
   */
  public DefaultNullHandling getDefaultNullHandling() {
    return defaultNullHandling;
  }

  /**
   * Gets the configuration for the default null handling in + and - operations.
   * @return not null
   */
  public BasicOperationsNullHandling getPlusMinusNullHandling() {
    return plusMinusNullHandling;
  }

  /**
   * Gets the configuration for the default null handling in * and ^ operations.
   * @return not null
   */
  public BasicOperationsNullHandling getMultiplicationNullHandling() {
    return multiplicationNullHandling;
  }

  /**
   * Gets the configuration for the default null handling in / and % operations.
   * @return not null
   */
  public BasicOperationsNullHandling getDivisionNullHandling() {
    return divisionNullHandling;
  }

  /**
   * Gets the configuration for the division by zero handling in / and % operations.
   * @return not null
   */
  public DivisionByZeroHandling getDivisionByZeroHandling() {
    return divisionByZeroHandling;
  }

  /**
   * Base type to be used for calculations.
   *
   * @return the base type
   */
  public Class<? extends Number> getBaseType() {
    return this.baseType;
  }

  private void checkModifiable() {
    if (!isModifiable()) {
      throw new IllegalStateException("Tried to modify an unmodifiable evaluator configuration");
    }
  }

  /**
   * Null handling variations.
   */
  public enum DefaultNullHandling {

    /** Throw an exception if one value is null. */
    EXCEPTION,

    /** Return null if one value is null. */
    NULL,

    /** Null is equal to 0. */
    ZERO
  }

  /**
   * Null handling for basic operations.
   */
  public enum BasicOperationsNullHandling {
    /** Inherit checks from default. */
    INHERIT,

    /** Null is equal to identity. (null +/- a = a, null * a = a, a / null = a). */
    IDENTITY
  }

  /**
   * Division by zero handling for division operation.
   */
  public enum DivisionByZeroHandling {
    /** Inherit checks from default. */
    INHERIT,

    /** Zero is treated as 1. (a / 0 = a). */
    ONE,

    /** When denominator is zero it returns null. */
    NULL
  }

}
