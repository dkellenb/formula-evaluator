package li.kellenberger.formulaevaluator.term.operator;

import java.math.BigDecimal;

import org.junit.Test;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.math.BigDecimal.ONE;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.BasicOperationsNullHandling.IDENTITY;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.NULL;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO;
import static li.kellenberger.formulaevaluator.term.operator.TermTester.testThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests BigDecimalDivisionOperator.
 */
public class BigDecimalDivisionOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = new BigDecimalDivisionOperator(v("a"), v("b"), v("c"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a/b/c)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    // good cases
    testThat(createOp(v("a"))).with("a", ONE).equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"))).equalTo(null);
    testThat(createOp(v("a"), v("b"))).isThrowing(FormulaEvaluatorNullArgumentException.class);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).isThrowing(FormulaEvaluatorNullArgumentException.class);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(NULL);

    // positive test cases
    testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(null);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(ZERO);

    // positive test cases
    testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with(conf).isThrowing(ArithmeticException.class);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).isThrowing(ArithmeticException.class);
  }

  @Test
  public void shouldCalculateWithIdentityFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDivisionNullHandling(IDENTITY);

    // positive test cases
    testThat(createOp(v("a"))).with("a", ONE).with(conf).equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE))).with("a", SIX).with("b", TWO).with(conf).equalTo(ONE);

    // null cases
    testThat(createOp(v("a"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with(conf).equalTo(null);
    testThat(createOp(v("a"), v("b"))).with("a", FOUR).with(conf).equalTo(FOUR);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> base) {
    return new BigDecimalDivisionOperator(base);
  }

  @SafeVarargs
  private static Term<BigDecimal> createOp(Term<BigDecimal> base, Term<BigDecimal>... terms) {
    return new BigDecimalDivisionOperator(base, terms);
  }

}
