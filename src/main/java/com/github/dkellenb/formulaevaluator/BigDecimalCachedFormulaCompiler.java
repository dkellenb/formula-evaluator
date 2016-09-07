package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.util.Set;

import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * Instance of a CachedFormulaCompiler for BigDecimal based calculations.
 */
public final class BigDecimalCachedFormulaCompiler extends CachedFormulaCompiler<BigDecimal> {

  private static BigDecimalCachedFormulaCompiler instance = null;

  private BigDecimalCachedFormulaCompiler() {
    // hidden c'tor.
    super(FormulaCompiler::compile);
  }

  /**
   * Gets the compiled term for the given formula.
   *
   * @param formula the formula
   * @param variables variables
   * @return the term
   */
  public static Term<BigDecimal> getTerm(String formula, Set<String> variables) {
    if (instance == null) {
      instance = new BigDecimalCachedFormulaCompiler();
    }
    return instance.getCompiledTerm(formula, variables);
  }

}
