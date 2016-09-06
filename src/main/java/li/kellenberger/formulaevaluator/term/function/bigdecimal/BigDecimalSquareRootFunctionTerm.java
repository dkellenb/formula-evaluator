package li.kellenberger.formulaevaluator.term.function.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import li.kellenberger.formulaevaluator.FormulaEvaluatorConfiguration;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;
import li.kellenberger.formulaevaluator.term.function.GenericFunctionTerm;

/**
 * BigDecimal SQRT Function.
 * From The Java Programmers Guide To numerical Computing (Ronald Mak, 2003)
 */
public class BigDecimalSquareRootFunctionTerm extends GenericFunctionTerm<BigDecimal>
    implements BigDecimalFunction {

  /**
   * C'tor.
   *
   * @param parameters parameter terms
   */
  @SafeVarargs
  public BigDecimalSquareRootFunctionTerm(Term<BigDecimal>... parameters) {
    super(parameters);
  }

  @Override
  protected Function getFunction() {
    return Function.SQRT;
  }

  @Override
  public BigDecimal calculateDefault(FormulaEvaluatorConfiguration conf, List<BigDecimal> parameters) {
    BigDecimal x = parameters.get(0);
    if (x.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    if (x.signum() < 0) {
      throw new ArithmeticException("Argument to SQRT() function must not be negative");
    }
    BigInteger n = x.movePointRight(conf.getMathContext().getPrecision() << 1).toBigInteger();
    int bits = (n.bitLength() + 1) >> 1;
    BigInteger ix = n.shiftRight(bits);
    BigInteger ixPrev;
    do {
      ixPrev = ix;
      ix = ix.add(n.divide(ix)).shiftRight(1);
      // Give other threads a chance to work;
      Thread.yield();
    } while (ix.compareTo(ixPrev) != 0);
    return new BigDecimal(ix, conf.getMathContext().getPrecision());
  }

}
