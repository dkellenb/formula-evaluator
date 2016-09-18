package com.github.dkellenb.formulaevaluator.term.function;

import java.util.List;
import java.util.stream.Stream;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.VariableValueProvider;
import com.github.dkellenb.formulaevaluator.exceptions.FormulaEvaluatorNullArgumentException;
import com.github.dkellenb.formulaevaluator.term.Term;

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
    this(asList(parameters));
  }

  /**
   * Default c'tor.
   *
   * @param parameters all parameters for this function.
   */
  protected GenericFunctionTerm(List<Term<T>> parameters) {
    if (parameters.size() != getFunction().getNumParams()) {
      throw new IllegalArgumentException("Wrong number of parameters got for function '" + getName() + "'."
        + " Got " + parameters.size() + ", but expected " + getFunction().getNumParams());
    }
    this.parameters = parameters;
  }

  @Override
  public T evaluate(VariableValueProvider<T, ?> input, FormulaEvaluatorConfiguration conf) {
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
