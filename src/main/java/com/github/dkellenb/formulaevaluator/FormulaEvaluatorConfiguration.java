package com.github.dkellenb.formulaevaluator;

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

  /**
   * Sets the default handling for null values.
   *
   * @param handling the handling option
   * @return self
   */
  public FormulaEvaluatorConfiguration setDefaultNullHandling(DefaultNullHandling handling) {
    this.defaultNullHandling = handling;
    return this;
  }

  /**
   * Sets the precision for expression evaluation.
   *
   * @param precision the new precision.
   */
  public void setPrecision(int precision) {
    calculationMathContext = new MathContext(precision, calculationMathContext.getRoundingMode());
    resultMathContext = calculationMathContext;
  }

  /**
   * Sets the calculation precision for expression evaluation.
   *
   * @param precision the new result precision
   */
  public void setCalculationPrecision(int precision) {
    resultMathContext = new MathContext(precision, resultMathContext.getRoundingMode());
  }

  /**
   * Sets the result and calculation precision for expression evaluation.
   *
   * @param precision the new result precision
   */
  public void setResultPrecision(int precision) {
    resultMathContext = new MathContext(precision, resultMathContext.getRoundingMode());
  }

  /**
   * Sets the rounding mode for expression evaluation.
   *
   * @param roundingMode the new rounding mode.
   */
  public void setRoundingMode(RoundingMode roundingMode) {
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
    this.divisionByZeroHandling = handling;
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
