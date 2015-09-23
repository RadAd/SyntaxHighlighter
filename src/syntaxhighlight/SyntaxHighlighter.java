// Copyright (c) 2012 Chan Wai Shing
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
package syntaxhighlight;

import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import syntaxhighlight.theme.Theme;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.SyntaxHighlighterParser;

/**
 * The syntax highlighter.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class SyntaxHighlighter extends JScrollPane {

  private static final long serialVersionUID = 1L;
  
  private final SyntaxHighlighterPane highlighter;
  private final JTextComponentRowHeader highlighterRowHeader;
  private Theme theme;
  private final SyntaxHighlighterParser parser;

  public SyntaxHighlighter(Theme theme) {
    if (theme == null) throw new NullPointerException("argument 'theme' cannot be null");

    this.theme = theme;
    this.parser = new SyntaxHighlighterParser();

    setBorder(null);

    this.highlighter = new SyntaxHighlighterPane();
    this.highlighter.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    this.highlighter.setTheme(theme);
    setViewportView(highlighter);

    this.highlighterRowHeader = new JTextComponentRowHeader(this, highlighter);
    this.highlighterRowHeader.applyTheme(theme);
    setRowHeaderView(highlighterRowHeader);
  }

  /**
   * Re-render the script text pane. Invoke this when any change of setting that 
   * affect the rendering was made.
   * This will re-parse the content and set the style.
   */
  protected void render() {
      // stop the change listener on the row header to speed up rendering
      highlighterRowHeader.setListenToDocumentUpdate(false);
      highlighter.setStyle(parser.parse(highlighter.getContent()));
      // resume the change listener on the row header
      highlighterRowHeader.setListenToDocumentUpdate(true);
      // notify the row header to update its information related to the SyntaxHighlighterPane
      // need to call this because we have stopped the change listener of the row header in previous code
      highlighterRowHeader.checkPanelSize();
  }

  /**
   * Get the SyntaxHighlighterPane (the script text area).
   * <b>Note: Normally should not operate on the SyntaxHighlighterPane 
   * directly.</b>
   * @return the SyntaxHighlighterPane
   */
  public SyntaxHighlighterPane getHighlighter() {
    return highlighter;
  }

  /**
   * Get the JTextComponentRowHeader, the line number panel.
   * <b>Note: Normally should not operate on the JTextComponentRowHeader 
   * directly.</b>
   * @return the JTextComponentRowHeader
   */
  public JTextComponentRowHeader getHighlighterRowHeader() {
    return highlighterRowHeader;
  }

  public Theme getTheme() {
    return theme;
  }

  public void setTheme(Theme theme) {
    if (theme == null) throw new NullPointerException("argument 'theme' cannot be null");

    if (!this.theme.equals(theme)) {
      this.theme = theme;
      highlighter.setTheme(theme);
      highlighterRowHeader.applyTheme(theme);
    }
  }

  public void setFirstLine(int firstLine) {
    highlighterRowHeader.setLineNumberOffset(firstLine - 1);
    highlighter.setLineNumberOffset(firstLine - 1);
  }

  public List<Integer> getHighlightedLineList() {
    return highlighter.getHighlightedLineList();
  }

  public void setHighlightedLineList(List<Integer> highlightedLineList) {
    highlighterRowHeader.setHighlightedLineList(highlightedLineList);
    highlighter.setHighlightedLineList(highlightedLineList);
  }

  public void addHighlightedLine(int lineNumber) {
    highlighterRowHeader.addHighlightedLine(lineNumber);
    highlighter.addHighlightedLine(lineNumber);
  }

  public boolean isGutterVisible() {
    return getRowHeader() != null && getRowHeader().getView() != null;
  }

  public void setGutterVisible(boolean visible) {
    if (visible) {
      setRowHeaderView(highlighterRowHeader);
      highlighter.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    } else {
      setRowHeaderView(null);
      highlighter.setBorder(null);
    }
  }

  public boolean isHighlightOnMouseOver() {
    return highlighter.isHighlightOnMouseOver();
  }

  public void setHighlightOnMouseOver(boolean highlightWhenMouseOver) {
    highlighter.setHighlightOnMouseOver(highlightWhenMouseOver);
  }

  public void setContent(String content, Brush brush) {
    parser.setBrush(brush);
    highlighter.setContent(content);
    render();
  }
}
