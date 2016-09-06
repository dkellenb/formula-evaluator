package li.kellenberger.formulaevaluator.mapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import li.kellenberger.formulaevaluator.definition.Constant;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimal10LogarithmFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalAbsoluteFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalCeilingFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalCosinusFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalDegreesFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalFloorFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalHyperbolicSinusFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalHyperbolicTangentFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalIfFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalMaxFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalMinFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalNaturalLogarithmFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalNotFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalRadiansFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalRandomFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalRoundFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalSinusFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalSquareRootFunctionTerm;
import li.kellenberger.formulaevaluator.term.function.bigdecimal.BigDecimalTangentFunctionTerm;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalAdditionOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalDivisionOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalEqualOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalExponentiationOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterEqualOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalAndOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalOrOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalModuloOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalMultiplicationOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalNotEqualOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerEqualOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerOperator;
import li.kellenberger.formulaevaluator.term.operator.bigdecimal.BigDecimalSubtractionOperator;
import li.kellenberger.formulaevaluator.term.value.BigDecimalVariable;
import li.kellenberger.formulaevaluator.term.value.ConstantBigDecimalTerm;

/**
 * Factory for creating BigDecimal Terms.
 */
public final class BigDecimalTermFactory implements TermFactory<BigDecimal> {

  private static BigDecimalTermFactory instance;

  private final Map<Operator, OperatorTermSupplier<BigDecimal>> operatorSuppliers;
  private final Map<Function, FunctionTermSupplier<BigDecimal>> functionSuppliers;
  private final Map<String, Supplier<Term<BigDecimal>>> variableSuppliers;
  private final Map<String, Supplier<Term<BigDecimal>>> constantSuppliers;

  private BigDecimalTermFactory() {
    // hidden c'tor.
    operatorSuppliers = new HashMap<>(Operator.ALL_OPERATORS.size());
    functionSuppliers = new HashMap<>(Function.ALL_FUNCTIONS.size());
    variableSuppliers = new HashMap<>(128);
    constantSuppliers = new HashMap<>(Constant.ALL_CONSTANTS_SET.size());

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
  @SafeVarargs
  public final Term<BigDecimal> getFunctionTerm(Function function, Term<BigDecimal>... parameters) {
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
  public Term<BigDecimal> getConstantTerm(String constant) {
    Supplier<Term<BigDecimal>> supplier = constantSuppliers.get(constant);
    if (supplier != null) {
      return supplier.get();
    }
    throw new UnsupportedOperationException("Unsupported constant " + constant + " not supported.");
  }

  @Override
  public void registerOperation(Operator operator, OperatorTermSupplier<BigDecimal> operatorTermSupplier) {
    operatorSuppliers.put(operator, operatorTermSupplier);
  }

  @Override
  public void registerFunction(Function function, FunctionTermSupplier<BigDecimal> functionTermSupplier) {
    functionSuppliers.put(function, functionTermSupplier);
  }

  @Override
  public void registerVariable(String variable, Supplier<Term<BigDecimal>> variableTermCreator) {
    variableSuppliers.put(variable, variableTermCreator);
  }

  @Override
  public void registerConstant(String constant, Supplier<Term<BigDecimal>> constantTermCreator) {
    constantSuppliers.put(constant, constantTermCreator);
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
    registerFunction(Function.NOT, BigDecimalNotFunctionTerm::new);
    registerFunction(Function.IF, BigDecimalIfFunctionTerm::new);
    registerFunction(Function.RANDOM, BigDecimalRandomFunctionTerm::new);
    registerFunction(Function.SIN, BigDecimalSinusFunctionTerm::new);
    registerFunction(Function.COS, BigDecimalCosinusFunctionTerm::new);
    registerFunction(Function.TAN, BigDecimalTangentFunctionTerm::new);
    registerFunction(Function.SINH, BigDecimalHyperbolicSinusFunctionTerm::new);
    registerFunction(Function.COSH, BigDecimalHyperbolicTangentFunctionTerm::new);
    registerFunction(Function.TANH, BigDecimalHyperbolicTangentFunctionTerm::new);
    registerFunction(Function.RAD, BigDecimalRadiansFunctionTerm::new);
    registerFunction(Function.DEG, BigDecimalDegreesFunctionTerm::new);
    registerFunction(Function.MIN, BigDecimalMinFunctionTerm::new);
    registerFunction(Function.MAX, BigDecimalMaxFunctionTerm::new);
    registerFunction(Function.LOG, BigDecimalNaturalLogarithmFunctionTerm::new);
    registerFunction(Function.LOG10, BigDecimal10LogarithmFunctionTerm::new);
    registerFunction(Function.ROUND, BigDecimalRoundFunctionTerm::new);
    registerFunction(Function.FLOOR, BigDecimalFloorFunctionTerm::new);
    registerFunction(Function.CEILING, BigDecimalCeilingFunctionTerm::new);
    registerFunction(Function.ABS, BigDecimalAbsoluteFunctionTerm::new);
    registerFunction(Function.SQRT, BigDecimalSquareRootFunctionTerm::new);
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
