package com.github.dkellenb.formulaevaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import com.github.dkellenb.formulaevaluator.term.Term;

/**
 * Cached FormulaCompiler.
 *
 * @param <T> data type for the calculations
 */
class CachedFormulaCompiler<T> {

  private final Map<String, Term<T>> map = new HashMap<>(1024);
  private final BiFunction<String, Set<String>, Term<T>> formulaCompiler;

  /**
   * Default c'tor.
   *
   * @param formulaCompiler the formula compiler to be used
   */
  CachedFormulaCompiler(BiFunction<String, Set<String>, Term<T>> formulaCompiler) {
    this.formulaCompiler = formulaCompiler;
  }

  /**
   * The compiled term.
   *
   * @param formula the formula
   * @param variables variables
   * @return the cached or new compiled term
   */
  public Term<T> getCompiledTerm(String formula, Set<String> variables) {
    Term<T> term = map.get(formula);
    if (term == null) {
      term = formulaCompiler.apply(formula, variables);
      map.put(formula, term);
    }
    return term;
  }

}
