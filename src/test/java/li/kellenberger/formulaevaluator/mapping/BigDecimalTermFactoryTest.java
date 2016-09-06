package li.kellenberger.formulaevaluator.mapping;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.VariableValueProvider;
import li.kellenberger.formulaevaluator.definition.Constant;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.value.ConstantBigDecimalTerm;
import li.kellenberger.formulaevaluator.valueprovider.BigDecimalVariableValueProvider;

import static java.math.BigDecimal.ONE;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests the BigDecimalTermFactory.
 */
public class BigDecimalTermFactoryTest {

  @Test
  public void shouldProvideSimpleVariableTerm() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();
    FormulaEvaluatorConfiguration defaultConfiguration = new FormulaEvaluatorConfiguration();
    BigDecimalVariableValueProvider valueProvider = BigDecimalVariableValueProvider.createValueProvider().with("a", BigDecimal.TEN);

    // when
    Term<BigDecimal> variableTerm = factory.getVariableTerm("a");

    // then
    assertThat(variableTerm.evaluate(valueProvider, defaultConfiguration), equalTo(BigDecimal.TEN));
  }

  @Test
  public void shouldProvideConstant() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();
    FormulaEvaluatorConfiguration defaultConfiguration = new FormulaEvaluatorConfiguration();
    BigDecimalVariableValueProvider emptyValueProvider = BigDecimalVariableValueProvider.createValueProvider();

    // when
    Term<BigDecimal> constantTerm = factory.getConstantTerm(Constant.TRUE);

    // then
    assertThat(constantTerm.evaluate(emptyValueProvider, defaultConfiguration), equalTo(BigDecimal.ONE));
  }

  @Test
  public void shouldProvideSimpleOperation() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();
    FormulaEvaluatorConfiguration defaultConfiguration = new FormulaEvaluatorConfiguration();
    BigDecimalVariableValueProvider valueProvider = BigDecimalVariableValueProvider.createValueProvider()
      .with("a", BigDecimal.ONE)
      .with("b", BigDecimal.ZERO);

    // when
    Term<BigDecimal> plusOperatorTerm = factory.getOperatorTerm(Operator.PLUS, factory.getVariableTerm("a"), factory.getVariableTerm("b"));

    // then
    assertThat(plusOperatorTerm.evaluate(valueProvider, defaultConfiguration), equalTo(BigDecimal.ONE));
  }

  @Test
  public void shouldHaveAllOperationsRegistered() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();

    // for all operators, an operatorTerm should be returned
    List<Operator> unsupportedOperators = new LinkedList<>();
    for (Operator operator : Operator.ALL_OPERATORS) {
      try {
        factory.getOperatorTerm(operator, new ConstantBigDecimalTerm(ONE), new ConstantBigDecimalTerm(ONE));
      } catch (UnsupportedOperationException e) {
        unsupportedOperators.add(operator);
      }
    }

    // verify list is empty
    assertThat("Following operations are not yet supported: "
      + unsupportedOperators.stream().map(Operator::getOperatorName).collect(joining(" ")),
      unsupportedOperators.size(), equalTo(0));
  }

  @Test
  public void shouldHaveAllFunctionsRegistered() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();

    // for all operators, an operatorTerm should be returned
    List<Function> unsupportedFunctions = new LinkedList<>();
    for (Function function : Function.ALL_FUNCTIONS) {
      try {
        factory.getFunctionTerm(function, createConstArgs(function));
      } catch (UnsupportedOperationException e) {
        unsupportedFunctions.add(function);
      }
    }

    // verify list is empty
    assertThat("Following operations are not yet supported: "
        + unsupportedFunctions.stream().map(Function::getName).collect(joining(" ")),
      unsupportedFunctions.size(), equalTo(0));
  }

  private ConstantBigDecimalTerm[] createConstArgs(Function function) {
    Stream<ConstantBigDecimalTerm> argStream = IntStream.range(0, function.getNumParams()).mapToObj(v -> new ConstantBigDecimalTerm(ONE));
    return argStream.toArray(ConstantBigDecimalTerm[]::new);
  }

  @Test
  public void shouldBeAbleToRegisterOwnFunction() {
    // given
    BigDecimalTermFactory factory = BigDecimalTermFactory.getInstance();
    BigDecimalVariableValueProvider emptyValueProvider = BigDecimalVariableValueProvider.createValueProvider();
    FormulaEvaluatorConfiguration defaultConfiguration = new FormulaEvaluatorConfiguration();
    Function nullFunction = new Function("NULL", 0);
    Term<BigDecimal> nullTerm = new Term<BigDecimal>() {

      @Override
      public BigDecimal evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration configuration) {
        return null;
      }

      @Override
      public String printFormula() {
        return "(null)";
      }
    };

    // when
    factory.registerFunction(nullFunction, parameters -> nullTerm);
    Term<BigDecimal> nullFunctionTerm = factory.getFunctionTerm(nullFunction);

    // then
    assertThat(nullFunctionTerm.printFormula(), equalTo("(null)"));
    assertThat(nullFunctionTerm.evaluate(emptyValueProvider, defaultConfiguration), nullValue());
  }

}
