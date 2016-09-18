package com.github.dkellenb.formulaevaluator.mapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import com.github.dkellenb.formulaevaluator.definition.Constant;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.value.GenericVariable;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Generic implementation of a term factory.
 *
 * @param <T> calculation base type
 */
abstract class GenericTermFactory<T> implements TermFactory<T> {

  private final Map<Operator, OperatorTermSupplier<T>> operatorSuppliers;
  private final Map<String, Operator> supportedOperators;
  private final Map<Function, FunctionTermSupplier<T>> functionSuppliers;
  private final Map<String, Function> supportedFunctions;
  private final Map<String, Supplier<Term<T>>> variableSuppliers;
  private final Map<String, Supplier<Term<T>>> constantSuppliers;
  private final Set<String> supportedConstants;

  /**
   * C'tor.
   */
  protected GenericTermFactory() {
    supportedConstants = new HashSet<>(Constant.ALL_CONSTANTS_SET.size());
    variableSuppliers = new HashMap<>(128);
    operatorSuppliers = new HashMap<>(Operator.ALL_OPERATORS.size());
    functionSuppliers = new HashMap<>(Function.ALL_FUNCTIONS.size());
    constantSuppliers = new HashMap<>(Constant.ALL_CONSTANTS_SET.size());
    supportedOperators = new HashMap<>(Operator.ALL_OPERATORS.size());
    supportedFunctions = new HashMap<>(Function.ALL_FUNCTIONS.size());
  }

  @Override
  public Term<T> getOperatorTerm(Operator operator, Term<T> t1, Term<T> t2) {
    OperatorTermSupplier<T> supplier = operatorSuppliers.get(operator);
    if (supplier != null) {
      return supplier.create(t1, t2);
    }
    throw new UnsupportedOperationException("Operation " + operator.getOperatorName() + " not supported.");
  }

  @Override
  public Term<T> getFunctionTerm(Function function, List<Term<T>> parameters) {
    FunctionTermSupplier<T> supplier = functionSuppliers.get(function);
    if (supplier != null) {
      return supplier.create(parameters);
    }
    throw new UnsupportedOperationException("Operation " + function.getName() + " not supported.");
  }

  @Override
  public Term<T> getVariableTerm(String variable) {
    Supplier<Term<T>> variableSupplier = variableSuppliers.get(variable);
    if (variableSupplier != null) {
      return variableSupplier.get();
    }
    GenericVariable<T> genericVariable = new GenericVariable<>(variable);
    registerVariable(variable, () -> genericVariable);
    return genericVariable;
  }

  @Override
  public Term<T> getConstantTerm(String constantName) {
    Supplier<Term<T>> supplier = constantSuppliers.get(constantName);
    if (supplier != null) {
      return supplier.get();
    }
    throw new UnsupportedOperationException("Unsupported constant " + constantName + " not supported.");
  }

  @Override
  public void registerOperation(Operator operator, OperatorTermSupplier<T> operatorTermSupplier) {
    operatorSuppliers.put(operator, operatorTermSupplier);
    supportedOperators.put(operator.getOperatorName(), operator);
  }

  @Override
  public void registerFunction(Function function, FunctionTermSupplier<T> functionTermSupplier) {
    functionSuppliers.put(function, functionTermSupplier);
    supportedFunctions.put(function.getName(), function);
  }

  @Override
  public void registerVariable(String variable, Supplier<Term<T>> variableTermCreator) {
    variableSuppliers.put(variable, variableTermCreator);
  }

  @Override
  public void registerConstant(String constant, Supplier<Term<T>> constantTermCreator) {
    constantSuppliers.put(constant, constantTermCreator);
    supportedConstants.add(constant);
  }

  @Override
  public Map<String, Operator> getSupportedOperators() {
    return unmodifiableMap(supportedOperators);
  }

  @Override
  public Map<String, Function> getSupportedFunctions() {
    return unmodifiableMap(supportedFunctions);
  }

  @Override
  public Set<String> getSupportedConstants() {
    return unmodifiableSet(supportedConstants);
  }

}
