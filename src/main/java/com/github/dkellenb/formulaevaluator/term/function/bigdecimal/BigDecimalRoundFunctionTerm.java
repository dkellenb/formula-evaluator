package com.github.dkellenb.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal ROUND Function.
 */
public class BigDecimalRoundFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param value the value
   * @param precision the precision
   */
  public BigDecimalRoundFunctionTerm(Term<BigDecimal> value, Term<BigDecimal> precision) {
    super(value, precision);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalRoundFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.ROUND;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal toRound = parameters.get(0);
    BigDecimal precisionAsDecimal = parameters.get(1);

    int precision = precisionAsDecimal.intValue();
    return toRound.setScale(precision, conf.getMathContext().getRoundingMode());
  }

}
