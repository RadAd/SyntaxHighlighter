// Copyright (c) 2011 Chan Wai Shing
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

import radsoft.syntaxhighlighter.brush.Brush;

/**
 * Emacs theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeEmacs extends Theme {

  public ThemeEmacs() {
    // Emacs SyntaxHighlighter theme based on theme by Joshua Emmons
    // http://www.skia.net/

    setBackground(Color.black);

    setHighlightedBackground(new Color(0x2A3133));

    setGutterText(new Color(0xd3d3d3));
    setGutterBorderColor(new Color(0x990000));

    setPlain(new Style().setColor(new Color(0xd3d3d3)));

    addStyle(Brush.COMMENTS,     new Style().setColor(new Color(0xff7d27)));
    addStyle(Brush.STRING,       new Style().setColor(new Color(0xff9e7b)));
    addStyle(Brush.KEYWORD,      new Style().setColor(new Color(0x00ffff)));
    addStyle(Brush.PREPROCESSOR, new Style().setColor(new Color(0xaec4de)));
    addStyle(Brush.VARIABLE,     new Style().setColor(new Color(0xffaa3e)));
    addStyle(Brush.VALUE,        new Style().setColor(new Color(0x009900)));
    addStyle(Brush.FUNCTIONS,    new Style().setColor(new Color(0x81cef9)));
    addStyle(Brush.CONSTANTS,    new Style().setColor(new Color(0xff9e7b)));
    //addStyle(Brush.SCRIPT,       new Style().setColor(new Color(0x00ffff)).setBold(true));
    //addStyle(Brush.SCRIPTBACKGROUND, new Style()));
    addStyle(Brush.COLOR1,       new Style().setColor(new Color(0xebdb8d)));
    addStyle(Brush.COLOR2,       new Style().setColor(new Color(0xff7d27)));
    addStyle(Brush.COLOR3,       new Style().setColor(new Color(0xaec4de)));
  }
}
