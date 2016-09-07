package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal LOG10 Function.
 */
public class BigDecimal10LogarithmFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage.
   *
   * @param argument default terms
   */
  public BigDecimal10LogarithmFunctionTerm(Term<BigDecimal> argument) {
    super(argument);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimal10LogarithmFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.LOG10;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    double d = Math.log10(parameters.get(0).doubleValue());
    return new BigDecimal(d, conf.getMathContext());
  }

}
