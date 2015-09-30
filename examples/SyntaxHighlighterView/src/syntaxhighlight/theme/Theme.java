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
package radsoft.syntaxhighlighter.view.theme;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.SimpleAttributeSet;

/**
 * Theme for the {@link SyntaxHighlighterPane} and 
 * {@link JTextComponentRowHeader}.
 * 
 * To make a new theme, either extending this class or initiate this class and 
 * set parameters using setters. For the default value, find the comment of the 
 * constructor.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Theme {

  protected Font font;
  protected Color background;
  protected Color highlightedBackground;
  protected Color gutterText;
  protected Color gutterBorderColor;
  protected int gutterBorderWidth;
  protected Font gutterTextFont;
  protected int gutterTextPaddingLeft;
  protected int gutterTextPaddingRight;
  protected Style plain;
  protected final Map<String, Style> styles;

  protected Theme() {
    font = new Font("Consolas", Font.PLAIN, 12);
    background = Color.white;

    highlightedBackground = Color.gray;

    gutterText = Color.black;
    gutterBorderColor = new Color(184, 184, 184);
    gutterBorderWidth = 3;
    gutterTextFont = new Font("Verdana", Font.PLAIN, 11);
    gutterTextPaddingLeft = 7;
    gutterTextPaddingRight = 7;

    plain = new Style();

    styles = new HashMap<String, Style>();
  }

  protected void setPlain(Style plain) {
    if (plain == null) throw new NullPointerException("argument 'plain' cannot be null");
    this.plain = plain;
  }

  public Style getPlain() {
    return plain;
  }

  protected Style addStyle(String styleKey, Style style) {
    return styles.put(styleKey, style);
  }

  public Style getStyle(String key) {
    Style returnStyle = styles.get(key);
    return returnStyle != null ? returnStyle : plain;
  }

  public Font getFont() {
    return font;
  }

  protected void setFont(Font font) {
    if (font == null) throw new NullPointerException("argument 'font' cannot be null");
    this.font = font;
  }

  public Color getBackground() {
    return background;
  }

  protected void setBackground(Color background) {
    if (background == null) throw new NullPointerException("argument 'background' cannot be null");
    this.background = background;
  }

  public Color getHighlightedBackground() {
    return highlightedBackground;
  }

  protected void setHighlightedBackground(Color highlightedBackground) {
    if (highlightedBackground == null) throw new NullPointerException("argument 'highlightedBackground' cannot be null");
    this.highlightedBackground = highlightedBackground;
  }

  public Color getGutterText() {
    return gutterText;
  }

  protected void setGutterText(Color gutterText) {
    if (gutterText == null) throw new NullPointerException("argument 'gutterText' cannot be null");
    this.gutterText = gutterText;
  }

  public Color getGutterBorderColor() {
    return gutterBorderColor;
  }

  protected void setGutterBorderColor(Color gutterBorderColor) {
    if (gutterBorderColor == null) throw new NullPointerException("argument 'gutterBorderColor' cannot be null");
    this.gutterBorderColor = gutterBorderColor;
  }

  public int getGutterBorderWidth() {
    return gutterBorderWidth;
  }

  protected void setGutterBorderWidth(int gutterBorderWidth) {
    this.gutterBorderWidth = gutterBorderWidth;
  }

  public Font getGutterTextFont() {
    return gutterTextFont;
  }

  protected void setGutterTextFont(Font gutterTextFont) {
    if (gutterTextFont == null) throw new NullPointerException("argument 'gutterTextFont' cannot be null");
    this.gutterTextFont = gutterTextFont;
  }

  public int getGutterTextPaddingLeft() {
    return gutterTextPaddingLeft;
  }

  protected void setGutterTextPaddingLeft(int gutterTextPaddingLeft) {
    this.gutterTextPaddingLeft = gutterTextPaddingLeft;
  }

  public int getGutterTextPaddingRight() {
    return gutterTextPaddingRight;
  }

  protected void setGutterTextPaddingRight(int gutterTextPaddingRight) {
    this.gutterTextPaddingRight = gutterTextPaddingRight;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    sb.append(getClass().getName());
    sb.append(": ");
    sb.append("font: ").append(getFont());
    sb.append("; ");
    sb.append("background: ").append(getBackground());
    sb.append("; ");
    sb.append("highlightedBackground: ").append(getHighlightedBackground());
    sb.append("; ");
    sb.append("gutterText: ").append(getGutterText());
    sb.append("; ");
    sb.append("gutterBorderColor: ").append(getGutterBorderColor());
    sb.append(", ");
    sb.append("gutterBorderWidth: ").append(getGutterBorderWidth());
    sb.append(", ");
    sb.append("gutterTextFont: ").append(getGutterTextFont());
    sb.append(", ");
    sb.append("gutterTextPaddingLeft: ").append(getGutterTextPaddingLeft());
    sb.append(", ");
    sb.append("gutterTextPaddingRight: ").append(getGutterTextPaddingRight());
    sb.append(", ");
    sb.append("styles: ");
    for (String _key : styles.keySet()) {
      sb.append(_key).append(":").append(styles.get(_key));
    }
    sb.append("]");

    return sb.toString();
  }
}
