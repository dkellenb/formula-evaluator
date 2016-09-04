package li.kellenberger.formulaevaluator.exceptions;

/**
 * The calculator and expression evaluators exception class.
 */
public class FormulaEvaluatorException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * The expression exception.
   * @param message the message
   */
  public FormulaEvaluatorException(String message) {
    super(message);
  }

}
