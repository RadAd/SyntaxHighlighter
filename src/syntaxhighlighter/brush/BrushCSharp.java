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
package radsoft.syntaxhighlighter.brush;

import java.util.regex.Pattern;

/**
 * C# brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushCSharp extends Brush {

    public final static String[] exts = { "cs" };

  public BrushCSharp() {
    super("C#");
     
    String keywords = "abstract as base bool break byte case catch char checked class const "
            + "continue decimal default delegate do double else enum event explicit "
            + "extern false finally fixed float for foreach get goto if implicit in int "
            + "interface internal is lock long namespace new null object operator out "
            + "override params private protected public readonly ref return sbyte sealed set "
            + "short sizeof stackalloc static string struct switch this throw true try "
            + "typeof uint ulong unchecked unsafe ushort using virtual void while var";

    add(new RegExpRule("\\/\\/\\/.*$", Pattern.MULTILINE, COLOR1)); // documents
    add(new RegExpRule(RegExpRule.singleLineCComments, COMMENTS));
    add(new RegExpRule(RegExpRule.multiLineCComments, COMMENTS));
    add(new RegExpRule("@\"(?:[^\"]|\"\")*\"", STRING)); // @-quoted strings
    add(new RegExpRule(RegExpRule.doubleQuotedString, STRING));
    add(new RegExpRule(RegExpRule.singleQuotedString, STRING));
    add(new RegExpRule("^\\s*#.*", Pattern.MULTILINE, PREPROCESSOR)); // preprocessor tags like #region and #endregion
    add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, KEYWORD));
    add(new RegExpRule("\\bpartial(?=\\s+(?:class|interface|struct)\\b)", KEYWORD)); // contextual keyword: 'partial'
    add(new RegExpRule("\\byield(?=\\s+(?:return|break)\\b)", KEYWORD)); // contextual keyword: 'yield'

    setHTMLScriptPattern(RegExpRule.aspScriptTags);
  }
}
