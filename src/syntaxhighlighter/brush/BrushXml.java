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

    public final static String[] exts = { "xml", "htm", "html", "xhtml", "xslt" };

  public BrushXml() {
    add(new RegExpRule("(\\&lt;|<)\\!\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\](\\&gt;|>)", Pattern.MULTILINE, "color2")); // <![ ... [ ... ]]>
    add(new RegExpRule(RegExpRule.xmlComments, "comments")); // <!-- ... -->

    // regular expression for highlighting the tag
    RegExpRule tagRegExpRule = new RegExpRule("(?:&lt;|<)[\\s\\/\\?]*([:\\w-\\.]+)", Pattern.COMMENTS, "");
    // highlight the tag only, not including the symbols at the start, 1 means the group 1 of the matched results
    tagRegExpRule.setGroupOperation(1, "keyword");

    // regular expression for highlighting the variable assignment
    RegExpRule valueRegExpRule = new RegExpRule("([\\w:\\-\\.]+)"
            + "\\s*=\\s*"
            + "(\".*?\"|'.*?'|\\w+)", Pattern.COMMENTS, "");
    // highlight the variable name, 1 means the group 1 of the matched results
    valueRegExpRule.setGroupOperation(1, "color1");
    // highlight the value, 2 means the group 2 of the matched results
    valueRegExpRule.setGroupOperation(2, "string");

    RegExpRule _regExpRule = new RegExpRule("((?:&lt;|<)[\\s\\/\\?]*(?:\\w+))(.*?)[\\s\\/\\?]*(?:&gt;|>)", Pattern.DOTALL, "");
    // perform futher operation on the group 1 of the matched results
    _regExpRule.setGroupOperation(1, tagRegExpRule);
    // perform futher operation on the group 2 of the matched results
    _regExpRule.setGroupOperation(2, valueRegExpRule);
    add(_regExpRule);
  }
}
