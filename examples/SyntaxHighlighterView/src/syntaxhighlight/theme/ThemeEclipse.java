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
 * Eclipse theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ThemeEclipse extends Theme {

  public ThemeEclipse() {
    // (C) Code-House
    // :http//blog.code-house.org/2009/10/xml-i-adnotacje-kod-ogolnego-przeznaczenia-i-jpa/

    setBackground(new Color(0xffffff));

    setHighlightedBackground(new Color(0xc3defe));

    setGutterText(new Color(0x787878));
    setGutterBorderColor(new Color(0xd4d0c8));

    setPlain(new Style().setColor(new Color(0x000000)));

    addStyle(Brush.COMMENTS,     new Style().setColor(new Color(0x3f5fbf)));
    addStyle(Brush.STRING,       new Style().setColor(new Color(0x2a00ff)));
    addStyle(Brush.KEYWORD,      new Style().setColor(new Color(0x7f0055)));
    addStyle(Brush.PREPROCESSOR, new Style().setColor(new Color(0x646464)));
    addStyle(Brush.VARIABLE,     new Style().setColor(new Color(0xaa7700)));
    addStyle(Brush.VALUE,        new Style().setColor(new Color(0x009900)));
    addStyle(Brush.FUNCTIONS,    new Style().setColor(new Color(0xff1493)));
    addStyle(Brush.CONSTANTS,    new Style().setColor(new Color(0x0066cc)));
    //addStyle(Brush.SCRIPT,       new Style().setColor(new Color(0x7f0055)).setBold(true));
    //addStyle(Brush.SCRIPTBACKGROUND, new Style()));
    addStyle(Brush.COLOR1,       new Style().setColor(Color.gray));
    addStyle(Brush.COLOR2,       new Style().setColor(new Color(0xff1493)));
    addStyle(Brush.COLOR3,       new Style().setColor(Color.red));
  }
}
