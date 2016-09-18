package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double MIN Function.
 */
public class DoubleMinFunctionTerm extends GenericFunctionTerm<Double> implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term1 the first argument
   * @param term2 the second argument
   */
  public DoubleMinFunctionTerm(Term<Double> term1, Term<Double> term2) {
    super(term1, term2);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleMinFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.MIN;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    Double v1 = parameters.get(0);
    Double v2 = parameters.get(1);
    return v1.compareTo(v2) < 0 ? v1 : v2;
  }

}
