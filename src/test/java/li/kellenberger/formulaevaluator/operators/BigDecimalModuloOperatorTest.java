package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import org.junit.Test;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.math.BigDecimal.ONE;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.NULL;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO;
import static li.kellenberger.formulaevaluator.operators.TermTester.testThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests BigDecimalModuloOperator.
 */
public class BigDecimalModuloOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = new BigDecimalModuloOperator(v("a"), v("b"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a%b)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    // good cases
    testThat(createOp(v("a"), v("b"))).with("a", ONE).with("b", TWO).equalTo(ONE);
    testThat(createOp(v("a"), c(THREE))).with("a", FOUR).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"), v("b"))).isThrowing(FormulaEvaluatorNullArgumentException.class);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).isThrowing(FormulaEvaluatorNullArgumentException.class);
    testThat(createOp(v("a"), v("b"))).with("b", FOUR).isThrowing(FormulaEvaluatorNullArgumentException.class);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(NULL);

    // positive test cases
    testThat(createOp(v("a"), v("b"))).with("a", FIVE).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with("b", FOUR).with(conf).equalTo(null);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(ZERO);

    // positive test cases
    testThat(createOp(v("a"), v("b"))).with("a", FIVE).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"), v("b"))).with(conf).isThrowing(ArithmeticException.class);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).isThrowing(ArithmeticException.class);
    testThat(createOp(v("a"), v("b"))).with("b", FOUR).with(conf).equalTo(BigDecimal.ZERO);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> numerator, Term<BigDecimal> denominator) {
    return new BigDecimalModuloOperator(numerator, denominator);
  }

}
