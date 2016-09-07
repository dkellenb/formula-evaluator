package com.github.dkellenb.formulaevaluator.mapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalDivisionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalOrOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalModuloOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalMultiplicationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalNotEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerOperator;
import com.github.dkellenb.formulaevaluator.term.value.BigDecimalVariable;
import com.github.dkellenb.formulaevaluator.term.value.ConstantBigDecimalTerm;
import com.github.dkellenb.formulaevaluator.definition.Constant;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.bigdecimal.AllBigDecimalFunctions;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalAdditionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalExponentiationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalAndOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSubtractionOperator;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

/**
 * Factory for creating BigDecimal Terms.
 */
public final class BigDecimalTermFactory implements TermFactory<BigDecimal> {

  private static BigDecimalTermFactory instance;

  private final Map<Operator, OperatorTermSupplier<BigDecimal>> operatorSuppliers;
  private final Map<String, Operator> supportedOperators;
  private final Map<Function, FunctionTermSupplier<BigDecimal>> functionSuppliers;
  private final Map<String, Function> supportedFunctions;
  private final Map<String, Supplier<Term<BigDecimal>>> variableSuppliers;
  private final Map<String, Supplier<Term<BigDecimal>>> constantSuppliers;
  private final Set<String> supportedConstants;

  private BigDecimalTermFactory() {
    // hidden c'tor.
    operatorSuppliers = new HashMap<>(Operator.ALL_OPERATORS.size());
    supportedOperators = new HashMap<>(Operator.ALL_OPERATORS.size());
    functionSuppliers = new HashMap<>(Function.ALL_FUNCTIONS.size());
    supportedFunctions = new HashMap<>(Function.ALL_FUNCTIONS.size());
    variableSuppliers = new HashMap<>(128);
    constantSuppliers = new HashMap<>(Constant.ALL_CONSTANTS_SET.size());
    supportedConstants = new HashSet<>(Constant.ALL_CONSTANTS_SET.size());

    registerDefaultOperators();
    registerDefaultFunctions();
    registerDefaultConstants();
  }

  /**
   * Gets in instance of this factory.
   *
   * @return the factory
   */
  public static BigDecimalTermFactory getInstance() {
    if (instance == null) {
      instance = new BigDecimalTermFactory();
    }
    return instance;
  }

  @Override
  public Term<BigDecimal> getOperatorTerm(Operator operator, Term<BigDecimal> t1, Term<BigDecimal> t2) {
    OperatorTermSupplier<BigDecimal> supplier = operatorSuppliers.get(operator);
    if (supplier != null) {
      return supplier.create(t1, t2);
    }
    throw new UnsupportedOperationException("Operation " + operator.getOperatorName() + " not supported.");
  }

  @Override
  public Term<BigDecimal> getFunctionTerm(Function function, List<Term<BigDecimal>> parameters) {
    FunctionTermSupplier<BigDecimal> supplier = functionSuppliers.get(function);
    if (supplier != null) {
      return supplier.create(parameters);
    }
    throw new UnsupportedOperationException("Operation " + function.getName() + " not supported.");
  }

  @Override
  public Term<BigDecimal> getVariableTerm(String variable) {
    Supplier<Term<BigDecimal>> variableSupplier = variableSuppliers.get(variable);
    if (variableSupplier != null) {
      return variableSupplier.get();
    }
    BigDecimalVariable bigDecimalVariable = new BigDecimalVariable(variable);
    registerVariable(variable, () -> bigDecimalVariable);
    return bigDecimalVariable;
  }

  @Override
  public Term<BigDecimal> getConstantTerm(String constantName) {
    Supplier<Term<BigDecimal>> supplier = constantSuppliers.get(constantName);
    if (supplier != null) {
      return supplier.get();
    }
    throw new UnsupportedOperationException("Unsupported constant " + constantName + " not supported.");
  }

  @Override
  public Term<BigDecimal> createFixedValueTerm(String value) {
    return new ConstantBigDecimalTerm(new BigDecimal(value));
  }

  @Override
  public void registerOperation(Operator operator, OperatorTermSupplier<BigDecimal> operatorTermSupplier) {
    operatorSuppliers.put(operator, operatorTermSupplier);
    supportedOperators.put(operator.getOperatorName(), operator);
  }

  @Override
  public void registerFunction(Function function, FunctionTermSupplier<BigDecimal> functionTermSupplier) {
    functionSuppliers.put(function, functionTermSupplier);
    supportedFunctions.put(function.getName(), function);
  }

  @Override
  public void registerVariable(String variable, Supplier<Term<BigDecimal>> variableTermCreator) {
    variableSuppliers.put(variable, variableTermCreator);
  }

  @Override
  public void registerConstant(String constant, Supplier<Term<BigDecimal>> constantTermCreator) {
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

  private void registerDefaultOperators() {
    registerOperation(Operator.PLUS, BigDecimalAdditionOperator::new);
    registerOperation(Operator.MINUS, BigDecimalSubtractionOperator::new);
    registerOperation(Operator.MULTIPLY, BigDecimalMultiplicationOperator::new);
    registerOperation(Operator.DIVISION, BigDecimalDivisionOperator::new);
    registerOperation(Operator.MODULO, BigDecimalModuloOperator::new);
    registerOperation(Operator.FRACTION, BigDecimalExponentiationOperator::new);
    registerOperation(Operator.LOGICAL_AND, BigDecimalLogicalAndOperator::new);
    registerOperation(Operator.LOGICAL_OR, BigDecimalLogicalOrOperator::new);
    registerOperation(Operator.GREATER, BigDecimalGreaterOperator::new);
    registerOperation(Operator.GREATER_EQUAL, BigDecimalGreaterEqualOperator::new);
    registerOperation(Operator.SMALLER, BigDecimalSmallerOperator::new);
    registerOperation(Operator.SMALLER_EQUAL, BigDecimalSmallerEqualOperator::new);
    registerOperation(Operator.EQUAL, BigDecimalEqualOperator::new);
    registerOperation(Operator.EQUAL2, BigDecimalEqualOperator::new);
    registerOperation(Operator.NOT_EQUAL, BigDecimalNotEqualOperator::new);
    registerOperation(Operator.NOT_EQUAL2, BigDecimalNotEqualOperator::new);
  }

  private void registerDefaultFunctions() {
    for (Map.Entry<Function, FunctionTermSupplier<BigDecimal>> entry : AllBigDecimalFunctions.all().entrySet()) {
      registerFunction(entry.getKey(), entry.getValue());
    }
  }

  private void registerDefaultConstants() {
    ConstantBigDecimalTerm piTerm = new ConstantBigDecimalTerm(new BigDecimal(
      "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679"));
    registerConstant(Constant.PI, () -> piTerm);
    ConstantBigDecimalTerm trueTerm = new ConstantBigDecimalTerm(BigDecimal.ONE);
    registerConstant(Constant.TRUE, () -> trueTerm);
    ConstantBigDecimalTerm falseTerm = new ConstantBigDecimalTerm(BigDecimal.ONE);
    registerConstant(Constant.FALSE, () -> falseTerm);
  }

}
