package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Performance comparison for BigDecimal and Double.
 */
@Ignore
public class PerformanceComparison {

  private static final String[] FORMULAS = {
    "a + b + c",
    "a * b + c",
    "a / b * c",
    "a ^ (b % 10) / c",
    "a / b / 12",
    "SQRT(a ^ 2 + b ^ 2)"
  };

  private static final int TEST_RUNS = 100000;

  @Test
  public void testWithBigDecimalConfiguration() {
    // given
    FormulaEvaluatorConfiguration configuration = new FormulaEvaluatorConfiguration();
    configuration.setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling.NULL);
    configuration.setUnmodifiable();

    run(configuration);
  }

  @Test
  public void testWithDoubleConfiguration() {
    // given
    FormulaEvaluatorConfiguration configuration = new FormulaEvaluatorConfiguration();
    configuration.setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling.NULL);
    configuration.setBaseCalculationType(Double.class);
    configuration.setUnmodifiable();

    run(configuration);
  }

  private void run(FormulaEvaluatorConfiguration configuration) {
    for (int i = 0; i < TEST_RUNS; i++) {
      for (String formula : FORMULAS) {
        FormulaEvaluator formulaEvaluator = new FormulaEvaluator(formula);
        formulaEvaluator.with("a", BigDecimal.valueOf(i)).with("b", BigDecimal.valueOf(i + 2)).with("c", BigDecimal.valueOf(i + 5));
        formulaEvaluator.setConfiguration(configuration);
        formulaEvaluator.evalPrecise();
      }
    }
  }

}
