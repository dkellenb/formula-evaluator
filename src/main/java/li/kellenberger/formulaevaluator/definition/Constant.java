package li.kellenberger.formulaevaluator.definition;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Constants.
 */
public final class Constant {

  private Constant() {
    // hidden c'tor.
  }

  /** Pi. */
  public static final String PI = "PI";

  /** True. */
  public static final String TRUE = "TRUE";

  /** False. */
  public static final String FALSE = "FALSE";

  /** All supported constants. */
  public static final Set<String> ALL_CONSTANTS_SET = new HashSet<>(asList(PI, TRUE, FALSE));

}
