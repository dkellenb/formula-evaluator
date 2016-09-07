package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal ABS Function.
 */
public class BigDecimalAbsoluteFunctionTerm extends GenericFunctionTerm<BigDecimal> implements BigDecimalFunction {

  /**
   * C'tor. This one should be used for external usage.
   *
   * @param argument default terms
   */
  public BigDecimalAbsoluteFunctionTerm(Term<BigDecimal> argument) {
    super(argument);
  }

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  BigDecimalAbsoluteFunctionTerm(List<Term<BigDecimal>> parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.ABS;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal v1 = parameters.get(0);
    return v1.abs(conf.getMathContext());
  }

}
