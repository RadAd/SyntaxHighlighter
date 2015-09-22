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
 * Python brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushPython extends Brush {

    public final static String[] exts = { "py" };

  public BrushPython() {
    // Contributed by Gheorghe Milas and Ahmad Sherif

    String keywords = "and assert break class continue def del elif else "
            + "except exec finally for from global if import in is "
            + "lambda not or pass print raise return try yield while";
    String functions = "__import__ abs all any apply basestring bin bool buffer callable "
            + "chr classmethod cmp coerce compile complex delattr dict dir "
            + "divmod enumerate eval execfile file filter float format frozenset "
            + "getattr globals hasattr hash help hex id input int intern "
            + "isinstance issubclass iter len list locals long map max min next "
            + "object oct open ord pow print property range raw_input reduce "
            + "reload repr reversed round set setattr slice sorted staticmethod "
            + "str sum super tuple type type unichr unicode vars xrange zip";
    String special = "None True False self cls class_";

    add(new RegExpRule(RegExpRule.singleLinePerlComments, COMMENTS));
    add(new RegExpRule("^\\s*@\\w+", Pattern.MULTILINE, COLOR2));
    add(new RegExpRule("(['\\\"]{3})([^['\\\"]{3}])*?['\\\"]{3}", Pattern.MULTILINE, COMMENTS));
    add(new RegExpRule("\"(?!\")(?:\\.|\\\\\\\"|[^\\\"\"\\n])*\"", Pattern.MULTILINE, STRING));
    add(new RegExpRule("'(?!')(?:\\.|(\\\\\\')|[^\\''\\n])*'", Pattern.MULTILINE, STRING));
    add(new RegExpRule("\\+|\\-|\\*|\\/|\\%|=|==", Pattern.MULTILINE, KEYWORD));
    add(new RegExpRule("\\b\\d+\\.?\\w*", "value"));
    add(new RegExpRule(getKeywords(functions), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, FUNCTIONS));
    add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, KEYWORD));
    add(new RegExpRule(getKeywords(special), Pattern.MULTILINE, COLOR1));

    setHTMLScriptRegExp(HTMLScriptRegExp.aspScriptTags);
  }
}
