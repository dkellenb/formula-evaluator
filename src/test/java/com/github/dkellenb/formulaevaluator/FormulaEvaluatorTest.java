package com.github.dkellenb.formulaevaluator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests formula evaluator
 */
public class FormulaEvaluatorTest {

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

}
