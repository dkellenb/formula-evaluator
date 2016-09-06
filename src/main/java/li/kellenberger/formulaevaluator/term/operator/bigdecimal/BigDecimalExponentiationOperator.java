package li.kellenberger.formulaevaluator.term.operator.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.operator.GenericExponentiationOperatorTerm;

import static java.math.BigDecimal.ONE;

/**
 * FRACTIONAL operation.
 * see http://stackoverflow.com/questions/3579779/how-to-do-a-fractional-power-on-bigdecimal-in-java}
 */
public class BigDecimalExponentiationOperator
    extends GenericExponentiationOperatorTerm<BigDecimal>
    implements BigDecimalOperator {

  /**
   * Initializes the calculator based on the terms.
   *
   * @param base the base term
   * @param exponent one or many exponent
   */
  public BigDecimalExponentiationOperator(Term<BigDecimal> base, Term<BigDecimal> exponent) {
    super(base, exponent);
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf,
                                     BigDecimal base, BigDecimal exponent) {
    int signOf2 = exponent.signum();
    double dn1 = base.doubleValue();
    BigDecimal v2 = exponent.multiply(new BigDecimal(signOf2)); // n2 is now positive
    BigDecimal remainderOf2 = v2.remainder(ONE);
    BigDecimal n2IntPart = v2.subtract(remainderOf2);
    BigDecimal intPow = base.pow(n2IntPart.intValueExact(), conf.getMathContext());
    BigDecimal doublePow = new BigDecimal(Math.pow(dn1,
      remainderOf2.doubleValue()));
    BigDecimal power = intPow.multiply(doublePow, conf.getMathContext());
    if (signOf2 == -1) {
      power = ONE.divide(power, conf.getMathContext().getPrecision(), RoundingMode.HALF_UP);
    }
    return power;
  }

}
