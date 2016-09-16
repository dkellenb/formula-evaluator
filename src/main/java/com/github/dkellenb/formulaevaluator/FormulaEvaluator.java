package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.valueprovider.BigDecimalVariableValueProvider;

/**
 * Formula Evaluator is a handy expression evaluator for Java, that allows to evaluate simple mathematical and boolean
 * expressions.
 */
public class FormulaEvaluator {

  private static final FormulaEvaluatorConfiguration DEFAULT_CONFIGURATION = new FormulaEvaluatorConfiguration(false);

  private final String formula;
  private final BigDecimalVariableValueProvider valueProvider;

  /**
   * Configuration for the formula evaluation.
   */
  private FormulaEvaluatorConfiguration configuration = DEFAULT_CONFIGURATION;

  /**
   * Creates a new formula instance from an formula string.
   *
   * @param formula The formula. E.g. {@code 2.4*sin(3)/(2-4)} or {@code sin(y)&gt;0 & max(z, 3)&gt;3}
   */
  protected FormulaEvaluator(String formula) {
    this.formula = formula;
    this.valueProvider = BigDecimalVariableValueProvider.createValueProvider();
  }

  /**
   * Creates a new formula instance from an formula string.
   *
   * @param formula The formula. E.g. {@code 2.4*sin(3)/(2-4)} or {@code sin(y)&gt;0 & max(z, 3)&gt;3}
   * @return formula evaluator
   */
  public static FormulaEvaluator create(String formula) {
    return new FormulaEvaluator(formula);
  }

  /**
   * Evaluates the expression.
   *
   * @return The result of the expression.
   */
  public BigDecimal evalRounded() {
    BigDecimal preciseValue = evalPrecise();
    return preciseValue == null
      ? null
      : preciseValue.round(this.configuration.getResultMathContext()).stripTrailingZeros();
  }

  /**
   * Evaluates the expression without rounding.
   *
   * @return the result of the expression.
   */
  public BigDecimal evalPrecise() {
    Set<String> variables = valueProvider.getVariables();
    Term<BigDecimal> term = BigDecimalCachedFormulaCompiler.getTerm(formula, variables);
    BigDecimal result = term.evaluate(valueProvider, this.configuration);
    return result == null
      ? null
      : result.stripTrailingZeros();
  }

  /**
   * Sets a variable value.
   *
   * @param variable the variable name.
   * @param value the variable value.
   * @return The expression, allows to chain methods.
   */
  public FormulaEvaluator with(String variable, Integer value) {
    BigDecimal bigDecimalValue = value != null ? BigDecimal.valueOf(value) : null;
    valueProvider.with(variable, bigDecimalValue);
    return this;
  }

  /**
   * Sets a variable value.
   *
   * @param variable the variable to set.
   * @param value the variable value.
   * @return The expression, allows to chain methods.
   */
  public FormulaEvaluator with(String variable, BigDecimal value) {
    valueProvider.with(variable, value);
    return this;
  }

  /**
   * Sets variables from a map.
   *
   * @param variables the variables to set.
   * @return The expression, allows to chain methods.
   */
  public FormulaEvaluator with(Map<String, BigDecimal> variables) {
    for (Map.Entry<String, BigDecimal> entry : variables.entrySet()) {
      this.with(entry.getKey(), entry.getValue());
    }
    return this;
  }

  /**
   * Sets a variable value.
   *
   * @param variable
   * The variable to set.
   * @param value
   * The variable value.
   * @return the expression, allows to chain methods.
   */
  public FormulaEvaluator and(String variable, BigDecimal value) {
    return with(variable, value);
  }

  /**
   * Sets a variable value.
   *
   * @param variable
   * The variable to set.
   * @param value
   * The variable value.
   * @return the expression, allows to chain methods.
   */
  public FormulaEvaluator and(String variable, Integer value) {
    return with(variable, value);
  }

  /**
   * Returns the current formula of this formula evaluator.
   *
   * @return the formula.
   */
  public String getFormula() {
    return formula;
  }

  /**
   * Returns the formula evaluator configuration (we cannot guarantee that it will not be modified, therefore
   * we return a new instance).
   *
   * @return the formula evaluator configuration
   */
  public FormulaEvaluatorConfiguration getConfiguration() {
    modifiableConfiguration();
    return configuration;
  }

  /**
   * Creates a cloned configuration and make it therefore modifiable.
   */
  private void modifiableConfiguration() {
    if (!this.configuration.isModifiable()) {
      this.configuration = new FormulaEvaluatorConfiguration(this.configuration);
    }
  }


  /**
   * Sets a new formula evaluator configuration.
   *
   * @param configuration the new configuration to be used
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setConfiguration(FormulaEvaluatorConfiguration configuration) {
    checkNotNull(configuration, "Formula evaluator configuration should never be null");
    if (!configuration.isModifiable()) {
      this.configuration = new FormulaEvaluatorConfiguration(configuration);
    }
    this.configuration = configuration;
    return this;
  }

  /**
   * Sets the precision during the formula evaluation. Sets also the precision of the result.
   *
   * @param precision the new precision.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setPrecision(int precision) {
    modifiableConfiguration();
    this.configuration.setPrecision(precision);
    return this;
  }

  /**
   * Sets the precision during the formula evaluation.
   *
   * @param precision the new precision.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setCalculationPrecision(int precision) {
    modifiableConfiguration();
    this.configuration.setCalculationPrecision(precision);
    return this;
  }

  /**
   * Sets the precision for the result of the formula evaluation. Not that it will be overwritten by
   * setPrecision().
   *
   * @param precision the new precision.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setResultPrecision(int precision) {
    modifiableConfiguration();
    this.configuration.setResultPrecision(precision);
    return this;
  }

  /**
   * Sets the rounding mode for formula evaluation.
   *
   * @param roundingMode the new rounding mode.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setRoundingMode(RoundingMode roundingMode) {
    checkNotNull(roundingMode, "RoundingMode cannot be null");
    modifiableConfiguration();
    configuration.setRoundingMode(roundingMode);
    return this;
  }

  /**
   * Sets the default null handling.
   *
   * @param nullHandling the new null handling.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling nullHandling) {
    checkNotNull(nullHandling, "Handling is not allowed to be undefined");
    modifiableConfiguration();
    configuration.setDefaultNullHandling(nullHandling);
    return this;
  }

  /**
   * Sets additional handling rules for plus / minus if default null handling is not set.
   *
   * @param nullHandling the new null handling.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setPlusMinusNullHandling(
      FormulaEvaluatorConfiguration.BasicOperationsNullHandling nullHandling) {
    checkNotNull(nullHandling, "Handling is not allowed to be undefined");
    modifiableConfiguration();
    configuration.setPlusMinusNullHandling(nullHandling);
    return this;
  }

  /**
   * Sets additional handling rules for multiplication if default null handling is not set.
   *
   * @param nullHandling the new null handling.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setMultiplicationNullHandling(
      FormulaEvaluatorConfiguration.BasicOperationsNullHandling nullHandling) {
    checkNotNull(nullHandling, "Handling is not allowed to be undefined");
    modifiableConfiguration();
   configuration.setMultiplicationNullHandling(nullHandling);
    return this;
  }

  /**
   * Sets additional handling rules for division if default null handling is not set.
   *
   * @param nullHandling the new null handling.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setDivisionNullHandling(
      FormulaEvaluatorConfiguration.BasicOperationsNullHandling nullHandling) {
    checkNotNull(nullHandling, "Handling is not allowed to be undefined");
    modifiableConfiguration();
    configuration.setDivisionNullHandling(nullHandling);
    return this;
  }

  /**
   * Sets additional handling rules for division if default division by zero handling is not set.
   *
   * @param divisionByZeroHandling the new division by zero handling.
   * @return this formula evaluator instance in order to allow to chaining methods.
   */
  public FormulaEvaluator setDivisionByZeroHandling(
      FormulaEvaluatorConfiguration.DivisionByZeroHandling divisionByZeroHandling) {
    checkNotNull(divisionByZeroHandling, "Handling is not allowed to be undefined");
    modifiableConfiguration();
    configuration.setDivisionByZeroHandling(divisionByZeroHandling);
    return this;
  }

  private static void checkNotNull(Object value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }
  }

}
