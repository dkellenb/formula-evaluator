package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal IF Function.
 */
public class BigDecimalIfFunctionTerm extends GenericFunctionTerm<BigDecimal> implements BigDecimalFunction {

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  @SafeVarargs
  public BigDecimalIfFunctionTerm(Term<BigDecimal>... parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.IF;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    boolean isTrue = !zero().equals(parameters.get(0));
    return isTrue ? parameters.get(1) : parameters.get(2);
  }

}
