package com.github.dkellenb.formulaevaluator.mapping;

import java.util.Map;

import com.github.dkellenb.formulaevaluator.definition.Constant;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.doubletype.AllDoubleFunctions;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleAdditionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleDivisionOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleExponentiationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleGreaterEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleGreaterOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleLogicalAndOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleLogicalOrOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleModuloOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleMultiplicationOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleNotEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleSmallerEqualOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleSmallerOperator;
import com.github.dkellenb.formulaevaluator.term.operator.doubletype.DoubleSubtractionOperator;
import com.github.dkellenb.formulaevaluator.term.value.ConstantTerm;

/**
 * Factory for creating Double Terms.
 */
public final class DoubleTermFactory extends GenericTermFactory<Double> {

  private static DoubleTermFactory instance;

  private DoubleTermFactory() {
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
  public static DoubleTermFactory getInstance() {
    if (instance == null) {
      instance = new DoubleTermFactory();
    }
    return instance;
  }

  @Override
  public Term<Double> createFixedValueTerm(String value) {
    return new ConstantTerm<>(new Double(value));
  }

  private void registerDefaultOperators() {
    registerOperation(Operator.PLUS, DoubleAdditionOperator::new);
    registerOperation(Operator.MINUS, DoubleSubtractionOperator::new);
    registerOperation(Operator.MULTIPLY, DoubleMultiplicationOperator::new);
    registerOperation(Operator.DIVISION, DoubleDivisionOperator::new);
    registerOperation(Operator.MODULO, DoubleModuloOperator::new);
    registerOperation(Operator.FRACTION, DoubleExponentiationOperator::new);
    registerOperation(Operator.LOGICAL_AND, DoubleLogicalAndOperator::new);
    registerOperation(Operator.LOGICAL_OR, DoubleLogicalOrOperator::new);
    registerOperation(Operator.GREATER, DoubleGreaterOperator::new);
    registerOperation(Operator.GREATER_EQUAL, DoubleGreaterEqualOperator::new);
    registerOperation(Operator.SMALLER, DoubleSmallerOperator::new);
    registerOperation(Operator.SMALLER_EQUAL, DoubleSmallerEqualOperator::new);
    registerOperation(Operator.EQUAL, DoubleEqualOperator::new);
    registerOperation(Operator.EQUAL2, DoubleEqualOperator::new);
    registerOperation(Operator.NOT_EQUAL, DoubleNotEqualOperator::new);
    registerOperation(Operator.NOT_EQUAL2, DoubleNotEqualOperator::new);
  }

  private void registerDefaultFunctions() {
    for (Map.Entry<Function, FunctionTermSupplier<Double>> entry : AllDoubleFunctions.all().entrySet()) {
      registerFunction(entry.getKey(), entry.getValue());
    }
  }

  private void registerDefaultConstants() {
    ConstantTerm<Double> piTerm = new ConstantTerm<>(new Double(
      "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679"));
    registerConstant(Constant.PI, () -> piTerm);
    ConstantTerm<Double> trueTerm = new ConstantTerm<>(1d);
    registerConstant(Constant.TRUE, () -> trueTerm);
    ConstantTerm<Double> falseTerm = new ConstantTerm<>(0d);
    registerConstant(Constant.FALSE, () -> falseTerm);
  }

}
