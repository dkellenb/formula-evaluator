package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double LOG10 Function.
 */
public class Double10LogarithmFunctionTerm extends GenericFunctionTerm<Double>
    implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage.
   *
   * @param argument default terms
   */
  public Double10LogarithmFunctionTerm(Term<Double> argument) {
    super(argument);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  Double10LogarithmFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.LOG10;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    return Math.log10(parameters.get(0));
  }

}
