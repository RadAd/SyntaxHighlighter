package syntaxhighlighter;

import java.util.List;
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
  public SyntaxHighlighterParser() {
    syntaxHighlighter = new SyntaxHighlighter();
    this.brush = SyntaxHighlighter.getBrush(null);
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

  public List<MatchResult> parse(String content) {
    syntaxHighlighter.setBrush(brush);
    return syntaxHighlighter.parse(content, 0, content.length());
  }
}
