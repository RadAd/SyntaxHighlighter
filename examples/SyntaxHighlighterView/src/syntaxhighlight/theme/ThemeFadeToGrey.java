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
package syntaxhighlight.theme;

import java.awt.Color;
import java.awt.Font;

import syntaxhighlighter.brush.Brush;

/**
 * Fade to Grey theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeFadeToGrey extends Theme {

  public ThemeFadeToGrey() {
    // Fade to Grey SyntaxHighlighter theme based on theme by Brasten Sager
    // :http//www.ibrasten.com/

    setBackground(new Color(0x121212));

    setHighlightedBackground(new Color(0x2C2C29));

    setGutterText(new Color(0xafafaf));
    setGutterBorderColor(new Color(0x3185b9));

    setPlain(new Style().setColor(Color.white));

    addStyle(Brush.COMMENTS,     new Style().setColor(new Color(0x696854)));
    addStyle(Brush.STRING,       new Style().setColor(new Color(0xe3e658)));
    addStyle(Brush.KEYWORD,      new Style().setColor(new Color(0xd01d33)));
    addStyle(Brush.PREPROCESSOR, new Style().setColor(new Color(0x435a5f)));
    addStyle(Brush.VARIABLE,     new Style().setColor(new Color(0x898989)));
    addStyle(Brush.VALUE,        new Style().setColor(new Color(0x009900)));
    addStyle(Brush.FUNCTIONS,    new Style().setColor(new Color(0xaaaaaa)));
    addStyle(Brush.CONSTANTS,    new Style().setColor(new Color(0x96daff)));
    addStyle(Brush.SCRIPT,       new Style().setColor(new Color(0xd01d33)).setBold(true));
    //addStyle(Brush.SCRIPTBACKGROUND, new Style()));
    addStyle(Brush.COLOR1,       new Style().setColor(new Color(0xffc074)));
    addStyle(Brush.COLOR2,       new Style().setColor(new Color(0x4a8cdb)));
    addStyle(Brush.COLOR3,       new Style().setColor(new Color(0x96daff)));
  }
}
