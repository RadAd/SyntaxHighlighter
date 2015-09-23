package syntaxhighlighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.parser.MatchResult;
import syntaxhighlighter.parser.SyntaxHighlighter;

/**
 * The SyntaxHighlighter parser for syntax highlight.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public final class SyntaxHighlighterParser {

  private final SyntaxHighlighter syntaxHighlighter;
  /**
   * The brush to use for this syntax highlighter.
   */
  private Brush brush;

  /**
   * Constructor.
   * @param brush the brush to use
   */
  public SyntaxHighlighterParser(Brush brush) {
    if (brush == null) throw new NullPointerException("argument 'brush' cannot be null");
    syntaxHighlighter = new SyntaxHighlighter();
    this.brush = brush;
  }

  /**
   * Get the brush.
   * @return brush the brush
   */
  public Brush getBrush() {
    return brush;
  }

  /**
   * Set the brush to use.
   * @param brush the brush
   */
  public void setBrush(Brush brush) {
    this.brush = brush;
  }

  public List<MatchResult> parse(String fileExtension, String content) {
    List<MatchResult> returnList = new ArrayList<MatchResult>();

    Map<Integer, List<MatchResult>> parsedResult = syntaxHighlighter.parse(brush, content.toCharArray(), 0, content.length());
    for (List<MatchResult> resultList : parsedResult.values()) {
      for (MatchResult result : resultList) {
        returnList.add(result);
      }
    }

    return returnList;
  }
}
