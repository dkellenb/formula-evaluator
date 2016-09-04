package li.kellenberger.formulaevaluator.operators;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsEqual;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.Term;
import li.kellenberger.formulaevaluator.valueprovider.BigDecimalVariableValueProvider;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Helper class to test terms.
 */
public final class TermTester<T> {

  private final Term<T> term;
  private final Map<String, BigDecimal> values = new HashMap<>();
  private FormulaEvaluatorConfiguration configuration = new FormulaEvaluatorConfiguration();

  /**
   * Creation.
   *
   * @param term term to be tested
   * @param <T> type
   * @return new instance
   */
  public static <T> TermTester<T> testThat(Term<T> term) {
    return new TermTester<>(term);
  }

  private TermTester(Term<T> term) {
    this.term = term;
  }

  /**
   * Variable values.
   *
   * @param variable variable
   * @param value value
   * @return this
   */
  public TermTester<T> with(String variable, BigDecimal value) {
    if (value != null) {
      values.put(variable, value);
    }
    return this;
  }

  /**
   * Configuration.
   *
   * @param configuration config
   * @return this
   */
  public TermTester<T> with(FormulaEvaluatorConfiguration configuration) {
    this.configuration = configuration;
    return this;
  }

  /**
   * Equal check.
   *
   * @param expectedValue expected value
   */
  public void equalTo(T expectedValue) {
    BigDecimalVariableValueProvider valueProvider = new BigDecimalVariableValueProvider(values);
    T value = term.evaluate(valueProvider, configuration);
    assertThat("For formula '" + term.printFormula() + "' with variables: " + valueProvider + " expected result of '"
      + expectedValue + "' does not match.", value, IsEqual.equalTo(expectedValue));
  }

  /**
   * Exception check.
   *
   * @param expectedException the expected exeption
   */
  public void isThrowing(Class<? extends Exception> expectedException) {
    BigDecimalVariableValueProvider valueProvider = new BigDecimalVariableValueProvider(values);
    try {
      T value = term.evaluate(valueProvider, configuration);
      fail("For formula '" + term.printFormula() + "' with variables : " + valueProvider
        + " expected exception would be '" + expectedException + "', but returned '" + value + "'");
    } catch (Exception e) {
      if (!e.getClass().equals(expectedException)) {
        fail("For formula '" + term.printFormula() + "' with variables : " + valueProvider
          + " expected exception would be '" + expectedException + "', but returned '" + e.getMessage() + "'");
      }
    }
  }
}
