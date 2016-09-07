package com.github.dkellenb.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.dkellenb.formulaevaluator.term.operator.OperatorTest;
import com.github.dkellenb.formulaevaluator.term.operator.TermTester;
import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.math.BigDecimal.ONE;
import static com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.NULL;
import static com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests BigDecimalExponentiationOperator.
 */
public class BigDecimalExponentiationOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = createOp(v("a"), v("b"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a^b)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    // good cases
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", TWO).with("b", TWO).equalTo(FOUR);
    TermTester.testThat(createOp(v("a"), c(THREE))).with("a", TWO).equalTo(EIGHT);

    // null cases
    TermTester.testThat(createOp(v("a"), v("b"))).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", TWO).isThrowing(FormulaEvaluatorNullArgumentException.class);
    TermTester.testThat(createOp(v("a"), v("b"))).with("b", TWO).isThrowing(FormulaEvaluatorNullArgumentException.class);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(NULL);

    // positive test cases
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", THREE).with("b", TWO).with(conf).equalTo(NINE);

    // null cases
    TermTester.testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(null);
    TermTester.testThat(createOp(v("a"), v("b"))).with("b", FOUR).with(conf).equalTo(null);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(ZERO);

    // positive test cases
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", THREE).with("b", TWO).with(conf).equalTo(NINE);

    // null cases
    TermTester.testThat(createOp(v("a"), v("b"))).with(conf).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(ONE);
    TermTester.testThat(createOp(v("a"), v("b"))).with("b", FOUR).with(conf).equalTo(BigDecimal.ZERO);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> numerator, Term<BigDecimal> denominator) {
    return new BigDecimalExponentiationOperator(numerator, denominator);
  }

}
