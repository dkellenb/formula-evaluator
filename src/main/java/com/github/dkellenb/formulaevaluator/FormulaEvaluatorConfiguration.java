package com.github.dkellenb.formulaevaluator;

import java.math.MathContext;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

/**
 * Formula Evaluator Configuration.
 */
public class FormulaEvaluatorConfiguration {

  @Getter
  private MathContext mathContext = MathContext.DECIMAL128;

  @Getter
  private MathContext resultMathContext = MathContext.DECIMAL32;

  @Getter
  @Setter
  private DefaultNullHandling defaultNullHandling = DefaultNullHandling.EXCEPTION;

  @Getter
  @Setter
  private BasicOperationsNullHandling plusMinusNullHandling = BasicOperationsNullHandling.INHERIT;

  @Getter
  @Setter
  private BasicOperationsNullHandling multiplicationNullHandling = BasicOperationsNullHandling.INHERIT;

  @Getter
  @Setter
  private BasicOperationsNullHandling divisionNullHandling = BasicOperationsNullHandling.INHERIT;

  @Getter
  @Setter
  private DivisionByZeroHandling divisionByZeroHandling = DivisionByZeroHandling.INHERIT;

  /**
   * Sets the precision for expression evaluation.
   *
   * @param precision the new precision.
   */
  public void setPrecision(int precision) {
    mathContext = new MathContext(precision, mathContext.getRoundingMode());
  }

  /**
   * Sets the result precision for expression evaluation.
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
    mathContext = new MathContext(mathContext.getPrecision(), roundingMode);
    resultMathContext = new MathContext(resultMathContext.getPrecision(), roundingMode);
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
