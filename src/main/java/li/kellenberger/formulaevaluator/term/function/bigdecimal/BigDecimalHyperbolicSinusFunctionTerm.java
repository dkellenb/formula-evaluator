package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal SINH Function.
 */
public class BigDecimalHyperbolicSinusFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  @SafeVarargs
  public BigDecimalHyperbolicSinusFunctionTerm(Term<BigDecimal>... parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.SINH;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    double d = Math.sinh(Math.toRadians(parameters.get(0).doubleValue()));
    return new BigDecimal(d, conf.getMathContext());
  }

}
