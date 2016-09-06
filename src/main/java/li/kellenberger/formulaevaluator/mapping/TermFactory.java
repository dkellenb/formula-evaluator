package li.kellenberger.formulaevaluator.mapping;

import java.util.function.Supplier;

import li.kellenberger.formulaevaluator.definition.Operator;
import li.kellenberger.formulaevaluator.definition.Function;
import li.kellenberger.formulaevaluator.term.Term;

/**
 * Factory for creating terms for a specific type.
 *
 * @param <T> type for the calculations.
 */
public interface TermFactory<T> {

  /**
   * Returns a term based on the given operator (if exists).
   *
   * @param operator the operator
   * @param t1 first argument of the operator
   * @param t2 second argument of the operator
   * @return the created term
   */
  Term<T> getOperatorTerm(Operator operator, Term<T> t1, Term<T> t2);

  /**
   * Returns a term based on the given function (if exists).
   *
   * @param function the operator
   * @param parameters parameter terms
   * @return the created term
   */
  Term<T> getFunctionTerm(Function function, Term<T>... parameters);

  /**
   * Returns a variable retrieval term.
   *
   * @param variable the variable name
   * @return the created term
   */
  Term<T> getVariableTerm(String variable);

  /**
   * Returns a constant term.
   *
   * @param constant the constant name
   * @return the created term
   */
  Term<T> getConstantTerm(String constant);

  /**
   * Registers an additional operation.
   *
   * @param operator the operator
   * @param operatorTermSupplier the creator
   */
  void registerOperation(Operator operator, OperatorTermSupplier<T> operatorTermSupplier);

  /**
   * Registers an additional function.
   *
   * @param function the operator
   * @param functionTermSupplier the creator
   */
  void registerFunction(Function function, FunctionTermSupplier<T> functionTermSupplier);

  /**
   * Registers an additional variable.
   *
   * @param variable the variable
   * @param variableTermCreator the creator
   */
  void registerVariable(String variable, Supplier<Term<T>> variableTermCreator);

  /**
   * Registers an additional constant.
   *
   * @param constant the constant
   * @param constantTermCreator the creator
   */
  void registerConstant(String constant, Supplier<Term<T>> constantTermCreator);

  /**
   * Operator term creator.
   *
   * @param <T> data type
   */
  @FunctionalInterface
  interface OperatorTermSupplier<T> {

    /**
     * Creates a term with the given terms as argument.
     *
     * @param t1 first argument of the operator
     * @param t2 second argument of the operator
     * @return new term
     */
    Term<T> create(Term<T> t1, Term<T> t2);
  }

  /**
   * Function term creator.
   *
   * @param <T> data type
   */
  @FunctionalInterface
  interface FunctionTermSupplier<T> {

    /**
     * Returns a term based on the given function (if exists).
     *
     * @param parameters parameter terms
     * @return the created term
     */
    Term<T> create(Term<T>... parameters);

  }
}
