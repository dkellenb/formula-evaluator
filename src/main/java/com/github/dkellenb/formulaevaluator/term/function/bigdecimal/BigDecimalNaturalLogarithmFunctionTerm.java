package com.github.dkellenb.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import com.github.dkellenb.formulaevaluator.FormulaEvaluatorConfiguration;
import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.term.Term;
import com.github.dkellenb.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal LOG Function.
 */
public class BigDecimalNaturalLogarithmFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public BigDecimalNaturalLogarithmFunctionTerm(Term<BigDecimal> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalNaturalLogarithmFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.LOG;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal v1 = parameters.get(0);
    double d = Math.log(v1.doubleValue());
    return new BigDecimal(d, conf.getCalculationMathContext());
  }

}
