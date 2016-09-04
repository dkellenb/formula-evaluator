package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;

import org.junit.Test;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;

import static java.math.BigDecimal.ONE;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.BasicOperationsNullHandling.IDENTITY;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.NULL;
import static li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration.DefaultNullHandling.ZERO;
import static li.kellenberger.formulaevaluator.operators.TermTester.testThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests BigDecimalAdditionOperator.
 */
public class BigDecimalAdditionOperatorTest extends OperatorTest {

  @Test
  public void shouldPrintFormula() {
    // given
    Term<BigDecimal> operator = new BigDecimalAdditionOperator(v("a"), v("b"), v("c"));

    // when
    String formula = operator.printFormula();

    // then
    assertThat(formula, equalTo("(a+b+c)"));
  }

  @Test
  public void shouldCalculateWithDefaults() {
    // good cases
    testThat(createOp(v("a")))
      .with("a", ONE)
      .equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE)))
      .with("a", ONE).with("b", TWO)
      .equalTo(SIX);

    // null cases
    testThat(createOp(v("a")))
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .isThrowing(FormulaEvaluatorNullArgumentException.class);
    testThat(createOp(v("a"), v("b")))
      .with("a", FOUR)
      .isThrowing(FormulaEvaluatorNullArgumentException.class);
  }

  @Test
  public void shouldCalculateWithNullFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(NULL);

    // positive test cases
    testThat(createOp(v("a")))
      .with("a", ONE)
      .with(conf)
      .equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE)))
      .with("a", ONE).with("b", TWO)
      .with(conf)
      .equalTo(SIX);

    // null cases
    testThat(createOp(v("a")))
      .with(conf)
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .with(conf)
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .with("a", FOUR)
      .with(conf)
      .equalTo(null);
  }

  @Test
  public void shouldCalculateWithZeroFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setDefaultNullHandling(ZERO);

    // positive test cases
    testThat(createOp(v("a")))
      .with("a", ONE)
      .with(conf)
      .equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE)))
      .with("a", ONE).with("b", TWO)
      .with(conf)
      .equalTo(SIX);

    // null cases
    testThat(createOp(v("a")))
      .with(conf)
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .with(conf)
      .equalTo(BigDecimal.ZERO);
    testThat(createOp(v("a"), v("b")))
      .with("a", FOUR)
      .with(conf)
      .equalTo(FOUR);
  }

  @Test
  public void shouldCalculateWithIdentityFocused() {
    FormulaEvaluatorConfiguration conf = new FormulaEvaluatorConfiguration();
    conf.setPlusMinusNullHandling(IDENTITY);

    // positive test cases
    testThat(createOp(v("a")))
      .with("a", ONE)
      .with(conf)
      .equalTo(ONE);
    testThat(createOp(v("a"), v("b"), c(THREE)))
      .with("a", ONE).with("b", TWO)
      .with(conf)
      .equalTo(SIX);

    // null cases
    testThat(createOp(v("a")))
      .with(conf)
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .with(conf)
      .equalTo(null);
    testThat(createOp(v("a"), v("b")))
      .with("a", FOUR)
      .with(conf)
      .equalTo(FOUR);
  }

  private static Term<BigDecimal> createOp(Term<BigDecimal> base) {
    return new BigDecimalAdditionOperator(base);
  }

  @SafeVarargs
  private static Term<BigDecimal> createOp(Term<BigDecimal> base, Term<BigDecimal>... terms) {
    return new BigDecimalAdditionOperator(base, terms);
  }

}
