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
package syntaxhighlighter.brush;

import java.util.regex.Pattern;

/**
 * Scala brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushScala extends Brush {

    public final static String[] exts = { "scl", "scala" };

  public BrushScala() {
    // Contributed by Yegor Jbanov and David Bernard.

    String keywords = "val sealed case def true trait implicit forSome import match object null finally super "
            + "override try lazy for var catch throw type extends class while with new final yield abstract "
            + "else do if return protected private this package false";
    String keyops = "[_:=><%#@]+";

    add(new RegExpRule(RegExpRule.singleLineCComments, COMMENTS));
    add(new RegExpRule(RegExpRule.multiLineCComments, COMMENTS));
    add(new RegExpRule(RegExpRule.multiLineSingleQuotedString, STRING));
    // problem: scala should start multiple line string with triple double-quote
    add(new RegExpRule(RegExpRule.multiLineDoubleQuotedString, STRING));
    add(new RegExpRule(RegExpRule.singleQuotedString, STRING));
    add(new RegExpRule("0x[a-f0-9]+|\\d+(\\.\\d+)?", Pattern.CASE_INSENSITIVE, VALUE));
    add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, KEYWORD));
    add(new RegExpRule(keyops, Pattern.MULTILINE, KEYWORD));
  }
}
