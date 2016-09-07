package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal RAD Function.
 */
public class BigDecimalRadiansFunctionTerm extends GenericFunctionTerm<BigDecimal> implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage
   *
   * @param term parameter term
   */
  public BigDecimalRadiansFunctionTerm(Term<BigDecimal> term) {
    super(term);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalRadiansFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.RAD;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    double d = Math.toRadians(parameters.get(0).doubleValue());
    return new BigDecimal(d, conf.getMathContext());
  }

}
