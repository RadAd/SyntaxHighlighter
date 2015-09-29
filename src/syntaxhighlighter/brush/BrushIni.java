// Copyright (c) 2015 Adam Gates
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
package syntaxhighlighter.brush;

import java.util.regex.Pattern;

/**
 * General INI brush.
 * @author Adam Gates <adam.gates84+github@gmail.com>
 */
public class BrushIni extends Brush {

    public final static String[] exts = { "ini", "cfg", "conf" };

  public BrushIni() {
    super("Conf");
    
    add(new RegExpRule("^\\s*\\[(\\s|\\w)*\\]", Pattern.MULTILINE, PREPROCESSOR));
    add(new RegExpRule("^\\s*(;|#).*", Pattern.MULTILINE, COMMENTS));
    
    RegExpRule valueRegExpRule = new RegExpRule("([\\w:\\-\\.]+)\\s*=\\s*(.*)", Pattern.COMMENTS, null);
    valueRegExpRule.addGroupOperation(VARIABLE);
    valueRegExpRule.addGroupOperation(VALUE);
    add(valueRegExpRule);
  }
}
