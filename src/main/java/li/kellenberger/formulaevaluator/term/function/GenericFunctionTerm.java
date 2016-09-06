package li.kellenberger.formulaevaluator.term.function;

import java.util.List;
import java.util.stream.Stream;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.VariableValueProvider;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;
import li.kellenberger.formulaevaluator.term.Term;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Generic implementation of a function term.
 *
 * @param <T> type of the value
 */
public abstract class GenericFunctionTerm<T> implements FunctionTerm<T> {

  private final List<Term<T>> parameters;

  /**
   * Default c'tor.
   *
   * @param parameters all parameters for this function.
   */
  @SafeVarargs
  protected GenericFunctionTerm(Term<T>... parameters) {
    if (parameters.length != getFunction().getNumParams()) {
      throw new IllegalArgumentException("Wrong number of parameters got for function '" + getName() + "'."
        + " Got " + parameters.length + ", but expected " + getFunction().getNumParams());
    }
    this.parameters = asList(parameters);
  }

  @Override
  public T evaluate(VariableValueProvider input, FormulaEvaluatorConfiguration conf) {
    // Simple case: No element
    if (parameters.size() == 0) {
      return calculate(conf, emptyList());
    }

    final List<T> evaluatedParameters = parameters.stream().map(t -> t.evaluate(input, conf)).collect(toList());
    return calculate(conf, evaluatedParameters);
  }

  /**
   * Implementation for this operator.
   *
   * @param conf the configuration.
   * @param parameters all parameters
   * @return The result of the operation.
   */
  protected T calculate(FormulaEvaluatorConfiguration conf, List<T> parameters) {
    Stream<T> parameterStream = parameters.stream();
    switch (conf.getDefaultNullHandling()) {
      case NULL:
        return parameterStream.anyMatch(v -> v == null) ? null : calculateDefault(conf, parameters);
      case ZERO:
        return calculateDefault(conf, parameterStream.map(v -> v == null ? zero() : v).collect(toList()));
      default:
        if (parameterStream.anyMatch(v -> v == null)) {
          throw new FormulaEvaluatorNullArgumentException(getName(), parameters);
        }
        return calculateDefault(conf, parameters);
    }
  }

  /**
   * Returns the function.
   * @return function
   */
  protected abstract Function getFunction();

  @Override
  public String getName() {
    return getFunction().getName();
  }

  @Override
  public String printFormula() {
    return getName() + "(" +  parameters.stream().map(Term::printFormula).collect(joining(",")) + ")";
  }

}
