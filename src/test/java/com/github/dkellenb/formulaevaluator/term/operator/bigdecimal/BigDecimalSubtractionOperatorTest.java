package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.operator.OperatorTest;
import com.github.dkellenb.formulaevaluator.term.operator.TermTester;
import com.github.dkellenb.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.math.BigDecimal.ONE;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests BigDecimalSubtractionOperator.
 */
public class BigDecimalSubtractionOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = createOp(v("a"), v("b"), v("c"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a-b-c)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    // good cases
    TermTester.testThat(createOp(v("a"))).with("a", ONE).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).equalTo(ONE);

    // null cases
    TermTester.testThat(createOp(v("a"))).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", THREE).isThrowing(FormulaEvaluatorNullArgumentException.class);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling.NULL);

    // positive test cases
    TermTester.testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    TermTester.testThat(createOp(v("a"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(null);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO);

    // positive test cases
    TermTester.testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    TermTester.testThat(createOp(v("a"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with(conf).equalTo(BigDecimal.ZERO);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(FOUR);
  }

  @Test
  public void shouldCalculateWithIdentityFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setPlusMinusNullHandling(FormulaEvaluatorConfiguration.BasicOperationsNullHandling.IDENTITY);

    // positive test cases
    TermTester.testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    TermTester.testThat(createOp(v("a"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(FOUR);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> base) {
    return new BigDecimalSubtractionOperator(base);
  }

  @SafeVarargs
  private static Term<BigDecimal> createOp(Term<BigDecimal> base, Term<BigDecimal>... terms) {
    return new BigDecimalSubtractionOperator(base, terms);
  }

}
