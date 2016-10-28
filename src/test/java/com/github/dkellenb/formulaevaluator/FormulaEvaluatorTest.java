package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests formula evaluator
 */
public class FormulaEvaluatorTest {

  @Test
  public void shouldCalcWithDoubleAndBigDecimalSameSimpleResult() {
    // given
    FormulaEvaluator bigDecimalFormulaEvaluator = new FormulaEvaluator("a + b");
    bigDecimalFormulaEvaluator.with("a", BigDecimal.ONE).with("b", BigDecimal.valueOf(2));
    FormulaEvaluator doubleFormulaEvaluator = new FormulaEvaluator("a + b");
    doubleFormulaEvaluator.with("a", BigDecimal.ONE).with("b", BigDecimal.valueOf(2));
    doubleFormulaEvaluator.setBaseCalculationType(Double.class);

    // when
    BigDecimal bigDecimalValue = bigDecimalFormulaEvaluator.evalPrecise();
    BigDecimal doubleValue = doubleFormulaEvaluator.evalPrecise();

    // then
    assertThat(bigDecimalValue, equalTo(doubleValue));
  }

  @Test
  public void shouldReturnModifiableConfiguration() {
    // given
    FormulaEvaluator formulaEvaluator = new FormulaEvaluator("a");

    // when
    FormulaEvaluatorConfiguration configuration = formulaEvaluator.getConfiguration();

    // then
    assertThat(configuration.isModifiable(), equalTo(true));
  }

  @Test
  public void shouldSetPassedConfigurationToBeNotModifiable() {
    // given
    FormulaEvaluator formulaEvaluator = new FormulaEvaluator("a");
    FormulaEvaluatorConfiguration formulaEvaluatorConfiguration = new FormulaEvaluatorConfiguration();
    formulaEvaluatorConfiguration.setUnmodifiable();

    // when
    formulaEvaluator.setConfiguration(formulaEvaluatorConfiguration);

    // then
    assertThat(formulaEvaluatorConfiguration.isModifiable(), equalTo(false));
  }

  @Test
  public void shouldBeAbleToChangeConfigurationOfInitiallyUnmodifiableConfiguration() {
    // given
    FormulaEvaluator formulaEvaluator = new FormulaEvaluator("a");
    FormulaEvaluatorConfiguration formulaEvaluatorConfiguration = new FormulaEvaluatorConfiguration();
    formulaEvaluatorConfiguration.setUnmodifiable();
    formulaEvaluator.setConfiguration(formulaEvaluatorConfiguration);

    // when
    formulaEvaluator.setPrecision(10);

    // then
    assertThat(formulaEvaluatorConfiguration.isModifiable(),
      equalTo(false));
    assertThat(formulaEvaluatorConfiguration.getCalculationMathContext().getPrecision(),
      equalTo(new FormulaEvaluatorConfiguration().getCalculationMathContext().getPrecision()));
    assertThat(formulaEvaluator.getConfiguration().getCalculationMathContext().getPrecision(),
      equalTo(10));
  }

  @Test
  public void shouldReturnSameFormula() {
    // given
    String formula = "a + b / c";

    // when
    FormulaEvaluator formulaEvaluator = new FormulaEvaluator(formula);

    // then
    assertThat(formulaEvaluator.getFormula(), equalTo(formula));
  }

  @Test
  public void shouldScale() {
    // given
    String formula = "1 / 3";

    // when
    FormulaEvaluator formulaEvaluator = new FormulaEvaluator(formula);
    formulaEvaluator.setResultScale(2);

    // then
    assertThat(formulaEvaluator.evalRoundedAndScaled(), equalTo(new BigDecimal("0.33")));
  }

}
