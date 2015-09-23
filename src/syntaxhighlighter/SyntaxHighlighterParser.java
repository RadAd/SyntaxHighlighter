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
   * Indicate whether the HTML-Script option is turned on or not.
   */
  private boolean htmlScript;

  /**
   * Constructor.
   * @param brush the brush to use
   */
  public SyntaxHighlighterParser(Brush brush) {
    if (brush == null) {
      throw new NullPointerException("argument 'brush' cannot be null");
    }
    syntaxHighlighter = new SyntaxHighlighter();
    this.brush = brush;
    htmlScript = false;
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

  /**
   * Get the setting of the HTML Script option.
   * See also {@link #setHTMLScriptBrush(java.util.List)}.
   * 
   * @return true if it is turned on, false if it is turned off
   */
  public boolean isHtmlScript() {
    return htmlScript;
  }

  /**
   * Allows you to highlight a mixture of HTML/XML code and a script which is 
   * very common in web development.
   * See also {@link #setHTMLScriptBrush(java.util.List)} to set the brushes.
   * Default is off.
   * The highlighter will re-render the script text pane every time this 
   * function is invoked (if there is any content).
   * 
   * @param htmlScript true to turn on, false to turn off
   */
  public void setHtmlScript(boolean htmlScript) {
    if (this.htmlScript != htmlScript) {
      this.htmlScript = htmlScript;
    }
  }

  public List<MatchResult> parse(String fileExtension, String content) {
    List<MatchResult> returnList = new ArrayList<MatchResult>();

    Map<Integer, List<MatchResult>> parsedResult = syntaxHighlighter.parse(brush, htmlScript, content.toCharArray(), 0, content.length());
    for (List<MatchResult> resultList : parsedResult.values()) {
      for (MatchResult result : resultList) {
        returnList.add(result);
      }
    }

    return returnList;
  }
}
