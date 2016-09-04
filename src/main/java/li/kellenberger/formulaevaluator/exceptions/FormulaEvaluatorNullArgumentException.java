package li.kellenberger.formulaevaluator.exceptions;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * The calculator and expression evaluators exception class.
 */
public class FormulaEvaluatorNullArgumentException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * The expression exception.
   * @param name operation / function name
   * @param arguments arguments
   */
  public FormulaEvaluatorNullArgumentException(String name, List<?> arguments) {
    super("'null' parameter found for operation/function '" + name
      + "' and parameters '"
      + arguments.stream().map(v -> v == null ? null : v.toString()).collect(joining("', '")) + "'");
  }
}
