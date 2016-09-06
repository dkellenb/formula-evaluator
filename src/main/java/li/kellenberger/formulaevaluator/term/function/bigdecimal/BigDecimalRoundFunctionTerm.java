package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal ROUND Function.
 */
public class BigDecimalRoundFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  @SafeVarargs
  public BigDecimalRoundFunctionTerm(Term<BigDecimal>... parameters) {
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
