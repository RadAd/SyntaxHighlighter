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
 * General XML (include HTML) brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class BrushXml extends Brush {

    public final static String[] exts = { "xml", "xslt" };
    public final static String[] extshtml = { "htm", "html", "xhtml" };

  public BrushXml(boolean html) {
    super(html ? "HTML" : "XML");
    
    setIsHtml(html);
    
    add(new RegExpRule("(\\&lt;|<)\\!\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\](\\&gt;|>)", Pattern.MULTILINE, COLOR2)); // <![ ... [ ... ]]>
    add(new RegExpRule(RegExpRule.xmlComments, COMMENTS)); // <!-- ... -->

    // regular expression for highlighting the tag
    RegExpRule tagRegExpRule = new RegExpRule("(?:&lt;|<)[\\s\\/\\?]*([:\\w-\\.]+)", Pattern.COMMENTS, null);
    tagRegExpRule.addGroupOperation(KEYWORD);

    // regular expression for highlighting the variable assignment
    RegExpRule valueRegExpRule = new RegExpRule("([\\w:\\-\\.]+)" + "\\s*=\\s*" + "(\".*?\"|'.*?'|\\w+)", Pattern.COMMENTS, null);
    valueRegExpRule.addGroupOperation(COLOR1);
    valueRegExpRule.addGroupOperation(STRING);

    RegExpRule _regExpRule = new RegExpRule("((?:&lt;|<)[\\s\\/\\?]*(?:\\w+))(.*?)[\\s\\/\\?]*(?:&gt;|>)", Pattern.DOTALL, null);
    _regExpRule.addGroupOperation(tagRegExpRule);
    _regExpRule.addGroupOperation(valueRegExpRule);
    add(_regExpRule);
  }
}
