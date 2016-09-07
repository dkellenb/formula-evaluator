package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.dkellenb.formulaevaluator.term.operator.OperatorTest;
import com.github.dkellenb.formulaevaluator.term.operator.TermTester;
import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;
import com.github.dkellenb.formulaevaluator.term.Term;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.NULL;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for BigDecimalLogicalAndOperator.
 */
public class BigDecimaLogicalAndOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = createOp(v("a"), v("b"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a&&b)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    TermTester.testThat(createOp(v("a"), v("b"))).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("b", TWO).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ONE).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ONE).equalTo(ONE);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration nullConfiguration = new FormulaEvaluatorConfiguration();
    nullConfiguration.setDefaultNullHandling(NULL);

    TermTester.testThat(createOp(v("a"), v("b"))).with(nullConfiguration).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with(nullConfiguration).with("a", ONE).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with(nullConfiguration).with("b", ONE).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ONE).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ONE).equalTo(ONE);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration zeroConfiguration = new FormulaEvaluatorConfiguration();
    zeroConfiguration.setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO);

    TermTester.testThat(createOp(v("a"), v("b"))).with(zeroConfiguration).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with(zeroConfiguration).with("a", ONE).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with(zeroConfiguration).with("b", ONE).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ZERO).with("b", ONE).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ZERO).equalTo(ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", ONE).equalTo(ONE);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> base, Term<BigDecimal> applicant) {
    return new BigDecimalLogicalAndOperator(base, applicant);
  }

}
