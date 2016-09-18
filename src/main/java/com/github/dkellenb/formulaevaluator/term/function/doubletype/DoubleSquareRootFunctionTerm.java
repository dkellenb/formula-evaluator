package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double SQRT Function.
 */
public class DoubleSquareRootFunctionTerm extends GenericFunctionTerm<Double>
    implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public DoubleSquareRootFunctionTerm(Term<Double> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleSquareRootFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.SQRT;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    Double x = parameters.get(0);
    if (x.equals(zero())) {
      return zero();
    }
    if (x < 0) {
      throw new ArithmeticException("Argument to SQRT() function must not be negative");
    }
    return Math.sqrt(x);
  }

}
