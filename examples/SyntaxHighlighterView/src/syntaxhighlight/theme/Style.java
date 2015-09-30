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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * The style used by {@link syntaxhiglight.Theme} as those of CSS styles.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public final class Style {
  private boolean changed;
  private SimpleAttributeSet attributeSet;
  private boolean bold;
  private Color color;
  private Color background;
  private boolean underline;
  private boolean italic;

  public Style() {
    changed = true;
    attributeSet = null;

    bold = false;
    color = Color.black;
    background = null;
    underline = false;
    italic = false;
  }

  public SimpleAttributeSet getAttributeSet() {
    if (changed) {
      attributeSet = new SimpleAttributeSet();
      StyleConstants.setBold(attributeSet, bold);
      StyleConstants.setForeground(attributeSet, color);
      if (background != null) {
        StyleConstants.setBackground(attributeSet, background);
      }
      StyleConstants.setUnderline(attributeSet, underline);
      StyleConstants.setItalic(attributeSet, italic);
      changed = false;
    }
    return attributeSet;
  }

  public Color getBackground() {
    return background;
  }

  public Style setBackground(Color background) {
    changed = true;
    this.background = background;
    return this;
  }

  public boolean isBold() {
    return bold;
  }

  public Style setBold(boolean bold) {
    changed = true;
    this.bold = bold;
    return this;
  }

  public Color getColor() {
    return color;
  }

  public Style setColor(Color color) {
    if (color == null) throw new NullPointerException("argument 'color' cannot be null");
    changed = true;
    this.color = color;
    return this;
  }

  public boolean isItalic() {
    return italic;
  }

  public Style setItalic(boolean italic) {
    changed = true;
    this.italic = italic;
    return this;
  }

  public boolean isUnderline() {
    return underline;
  }

  public Style setUnderline(boolean underline) {
    changed = true;
    this.underline = underline;
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (this.bold ? 1 : 0);
    hash = 97 * hash + (this.color != null ? this.color.hashCode() : 0);
    hash = 97 * hash + (this.background != null ? this.background.hashCode() : 0);
    hash = 97 * hash + (this.underline ? 1 : 0);
    hash = 97 * hash + (this.italic ? 1 : 0);
    return hash;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Style)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    Style _object = (Style) obj;
    return _object.bold == bold && _object.color.equals(color) && _object.background.equals(background)
            && _object.underline == underline && _object.italic == italic;
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
    sb.append("bold: ").append(bold);
    sb.append(", ");
    sb.append("color: ").append(color);
    sb.append(", ");
    sb.append("background: ").append(background);
    sb.append(", ");
    sb.append("underline: ").append(underline);
    sb.append(", ");
    sb.append("italic: ").append(italic);
    sb.append("]");

    return sb.toString();
  }
}
