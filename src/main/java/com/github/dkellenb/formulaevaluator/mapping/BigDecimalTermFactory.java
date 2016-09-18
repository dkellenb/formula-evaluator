package com.github.dkellenb.formulaevaluator.mapping;

import java.math.BigDecimal;
import java.util.Map;

import com.github.dkellenb.formulaevaluator.definition.Constant;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.bigdecimal.AllBigDecimalFunctions;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalAdditionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalDivisionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalExponentiationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalGreaterOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalAndOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalLogicalOrOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalModuloOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalMultiplicationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalNotEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSmallerOperator;
import com.github.dkellenb.formulaevaluator.term.operator.bigdecimal.BigDecimalSubtractionOperator;
import com.github.dkellenb.formulaevaluator.term.value.ConstantTerm;

/**
 * Factory for creating BigDecimal Terms.
 */
public final class BigDecimalTermFactory extends GenericTermFactory<BigDecimal> {

  private static BigDecimalTermFactory instance;

  private BigDecimalTermFactory() {
    super();
    // hidden c'tor.

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
  public Term<BigDecimal> createFixedValueTerm(String value) {
    return new ConstantTerm<>(new BigDecimal(value));
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
    ConstantTerm<BigDecimal> piTerm = new ConstantTerm<>(new BigDecimal(
      "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679"));
    registerConstant(Constant.PI, () -> piTerm);
    ConstantTerm<BigDecimal> trueTerm = new ConstantTerm<>(BigDecimal.ONE);
    registerConstant(Constant.TRUE, () -> trueTerm);
    ConstantTerm<BigDecimal> falseTerm = new ConstantTerm<>(BigDecimal.ZERO);
    registerConstant(Constant.FALSE, () -> falseTerm);
  }

}
