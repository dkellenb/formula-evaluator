package li.kellenberger.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.operator.GenericStackableOperatorTerm;

/**
 * BigDecimal || Operator.
 */
public class BigDecimalLogicalOrOperator extends GenericStackableOperatorTerm<BigDecimal>
  implements BigDecimalOperator {

  /**
   * C'tor.
   *
   * @param base base value
   * @param applicants further terms (not null)
   */
  @SafeVarargs
  public BigDecimalLogicalOrOperator(Term<BigDecimal> base, Term<BigDecimal>... applicants) {
    super(base, applicants);
  }

  @Override
  public String getOperatorName() {
    return Operator.LOGICAL_OR.getOperatorName();
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, BigDecimal v1, BigDecimal v2) {
    boolean b1 = !zero().equals(v1);
    boolean b2 = !zero().equals(v2);
    return b1 || b2 ? one() : zero();
  }

}
