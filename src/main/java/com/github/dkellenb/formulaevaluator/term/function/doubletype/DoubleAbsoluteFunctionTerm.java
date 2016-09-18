package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double ABS Function.
 */
public class DoubleAbsoluteFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage.
   *
   * @param argument default terms
   */
  public DoubleAbsoluteFunctionTerm(Term<Double> argument) {
    super(argument);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleAbsoluteFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.ABS;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    Double v1 = parameters.get(0);
    return Math.abs(v1);
  }

}
