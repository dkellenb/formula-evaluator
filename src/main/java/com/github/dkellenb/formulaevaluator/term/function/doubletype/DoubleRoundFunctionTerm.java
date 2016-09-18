package com.github.dkellenb.formulaevaluator.term.function.doubletype;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * Double ROUND Function.
 */
public class DoubleRoundFunctionTerm extends GenericFunctionTerm<Double>
    implements DoubleFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param value the value
   * @param precision the precision
   */
  public DoubleRoundFunctionTerm(Term<Double> value, Term<Double> precision) {
    super(value, precision);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  DoubleRoundFunctionTerm(List<Term<Double>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.ROUND;
  }

  @Override
  public Double calculateDefault(FormulaEvaluatorConfiguration conf, List<Double> parameters) {
    Double toRound = parameters.get(0);
    int places = (int) Math.floor(parameters.get(1));
    if (places < 0) {
      throw new IllegalArgumentException("Places >= 0 expected");
    }

    BigDecimal bd = new BigDecimal(toRound);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

}
