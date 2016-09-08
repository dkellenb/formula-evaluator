package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Test;

import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.valueprovider.BigDecimalVariableValueProvider;

import static com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO;
import static com.github.dkellenb.formulaevaluator.valueprovider.BigDecimalVariableValueProvider.createValueProvider;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.util.Collections.emptySet;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Integration test for the FormulaCompiler.
 */
public class FormulaCompilerBigDecimalTest {

  private static final NoValueProvider NO_VALUE_PROVIDER = new NoValueProvider();
  private static final FormulaEvaluatorConfiguration DEFAULT_CONFIGURATION = new FormulaEvaluatorConfiguration();

  @Test
  public void shouldCreateConstantTree() {
    // given
    String formula = "8";

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula);

    // then
    assertThat(compiledFormula.printFormula(), equalTo(formula));
    assertThat(compiledFormula.evaluate(NO_VALUE_PROVIDER, DEFAULT_CONFIGURATION), equalTo(new BigDecimal(formula)));
  }

  @Test
  public void shouldCreateVariableTree() {
    // given
    String formula = "a";
    BigDecimal eight = new BigDecimal(8);
    BigDecimalVariableValueProvider valueProvider = createValueProvider().with("a", eight);

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula, "a");

    // then
    assertThat(compiledFormula.printFormula(), equalTo(formula));
    assertThat(compiledFormula.evaluate(valueProvider, DEFAULT_CONFIGURATION), equalTo(eight));
  }

  @Test
  public void shouldCreateOperationTree() {
    // given
    String formula = "(2+4)/3";
    BigDecimal two = new BigDecimal(2);

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula);

    // then
    assertThat(compiledFormula.printFormula(), equalTo("((2+4)/3)"));
    assertThat(compiledFormula.evaluate(NO_VALUE_PROVIDER, DEFAULT_CONFIGURATION), equalTo(two));
  }

  @Test
  public void shouldCreateOperationTreeWithAutoBrackets() {
    // given
    String formula = "2+6/3";
    BigDecimal four = new BigDecimal(4);

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula);

    // then
    assertThat(compiledFormula.printFormula(), equalTo("(2+(6/3))"));
    assertThat(compiledFormula.evaluate(NO_VALUE_PROVIDER, DEFAULT_CONFIGURATION), equalTo(four));
  }

  @Test
  public void shouldCreateFunctionTree() {
    // given
    String formula = "MAX(2, 3 + 1)";
    BigDecimal four = new BigDecimal(4);

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula);

    // then
    assertThat(compiledFormula.printFormula(), equalTo("MAX(2,(3+1))"));
    assertThat(compiledFormula.evaluate(NO_VALUE_PROVIDER, DEFAULT_CONFIGURATION), equalTo(four));
  }

  @Test
  public void shouldCreateTreeWithVariables() {
    // given
    String formula = "a + b * c";
    BigDecimalVariableValueProvider valueProvider = createValueProvider().with("a", ONE).with("b", null).with("c", TEN);
    FormulaEvaluatorConfiguration config = new FormulaEvaluatorConfiguration().setDefaultNullHandling(ZERO);

    // when
    Term<BigDecimal> compiledFormula = FormulaCompiler.compile(formula, "a", "b", "c");

    // then
    assertThat(compiledFormula.printFormula(), equalTo("(a+(b*c))"));
    assertThat(compiledFormula.evaluate(valueProvider, config), equalTo(ONE));
  }

  private static final class NoValueProvider implements VariableValueProvider {
    @Override
    public BigDecimal getValue(String variable) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getVariables() {
      return emptySet();
    }
  }

}
