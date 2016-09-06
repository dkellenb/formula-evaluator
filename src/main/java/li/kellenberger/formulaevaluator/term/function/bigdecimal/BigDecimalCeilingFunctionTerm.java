package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal CEILING Function.
 */
public class BigDecimalCeilingFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  @SafeVarargs
  public BigDecimalCeilingFunctionTerm(Term<BigDecimal>... parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.CEILING;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal toRound = parameters.get(0);
    return toRound.setScale(0, RoundingMode.CEILING);
  }

}
