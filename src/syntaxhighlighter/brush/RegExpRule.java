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

import java.util.List;
import java.util.regex.Pattern;

/**
 * The regular expression rule.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 * @author Adam Gates <Adam.Gates84+github@gmail.com>
 */
public final class RegExpRule {
  protected static final Pattern multiLineCComments = Pattern.compile("\\/\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE);
  protected static final Pattern singleLineCComments = Pattern.compile("\\/\\/.*$", Pattern.MULTILINE);
  protected static final Pattern singleLinePerlComments = Pattern.compile("#.*$", Pattern.MULTILINE);
  protected static final Pattern doubleQuotedString = Pattern.compile("\"([^\\\\\"\\n]|\\\\.)*\"");
  protected static final Pattern singleQuotedString = Pattern.compile("'([^\\\\'\\n]|\\\\.)*'");
  protected static final Pattern multiLineDoubleQuotedString = Pattern.compile("\"([^\\\\\"]|\\\\.)*\"", Pattern.DOTALL);
  protected static final Pattern multiLineSingleQuotedString = Pattern.compile("'([^\\\\']|\\\\.)*'", Pattern.DOTALL);
  protected static final Pattern phpScriptTags = Pattern.compile("(?:&lt;|<)\\?(?:=|php)(.*?)\\?(?:&gt;|>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
  protected static final Pattern aspScriptTags = Pattern.compile("(?:&lt;|<)%=?(.*?)%(?:&gt;|>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

  static Pattern createScriptPattern(String tag, String type) {
    return Pattern.compile("(?:&lt;|<)\\s*" + tag + ".*?type=\"" + type + "\".*?(?:&gt;|>)(.*?)(?:&lt;|<)\\/\\s*" + tag + "\\s*(?:&gt;|>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
  }

  private final Pattern pattern;
  private final List<Object> groupOperations;

  RegExpRule(String regExp, String styleKey) {
    this(regExp, 0, styleKey);
  }

  RegExpRule(String regExp, int regFlags, String styleKey) {
    this(Pattern.compile(regExp, regFlags), styleKey);
  }

  RegExpRule(Pattern pattern, String styleKey) {
    if (pattern == null) throw new NullPointerException("argument 'pattern' cannot be null");

    this.pattern = pattern;
    this.groupOperations = new java.util.ArrayList<Object>();
    addGroupOperation(styleKey);
  }

  public Pattern getPattern() {
    return pattern;
  }

  public List<Object> getGroupOperations() {
    return java.util.Collections.unmodifiableList(groupOperations);
  }

  void addGroupOperation(String styleKey) {
    this.groupOperations.add(styleKey);
  }
  void addGroupOperation(RegExpRule subRule) {
    this.groupOperations.add(subRule);
  }
  void addGroupOperation(Brush brush) {
    this.groupOperations.add(brush);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(getClass().getName());
    sb.append(": ");
    sb.append("regExp: ");
    sb.append(pattern.pattern());
    sb.append(", ");
    sb.append("regFlags: ");
    sb.append(pattern.flags());
    sb.append(", ");
    sb.append("getGroupOperations: ");
    sb.append(getGroupOperations());

    return sb.toString();
  }
}
