package com.github.dkellenb.formulaevaluator;

import java.util.Set;

import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * Instance of a CachedFormulaCompiler for Double based calculations.
 */
public final class DoubleCachedFormulaCompiler extends CachedFormulaCompiler<Double> {

  private static DoubleCachedFormulaCompiler instance = null;

  private DoubleCachedFormulaCompiler() {
    // hidden c'tor.
    super((formula, variables) -> FormulaCompiler.compile(Double.class, formula, variables));
  }

  /**
   * Gets the compiled term for the given formula.
   *
   * @param formula the formula
   * @param variables variables
   * @return the term
   */
  public static Term<Double> getTerm(String formula, Set<String> variables) {
    if (instance == null) {
      instance = new DoubleCachedFormulaCompiler();
    }
    return instance.getCompiledTerm(formula, variables);
  }

}
