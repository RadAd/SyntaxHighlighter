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

import java.util.Map;
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
  protected static final Pattern xmlComments = Pattern.compile("\\w+:\\/\\/[\\w-.\\/?%&=:@;]*");

  private final Pattern pattern;
  private final Map<Integer, Object> groupOperations;

  public RegExpRule(String regExp, String styleKey) {
    this(regExp, 0, styleKey);
  }

  public RegExpRule(String regExp, int regFlags, String styleKey) {
    this(Pattern.compile(regExp, regFlags), styleKey);
  }

  public RegExpRule(Pattern pattern, String styleKey) {
    if (pattern == null) throw new NullPointerException("argument 'pattern' cannot be null");
    if (styleKey == null) throw new NullPointerException("argument 'styleKey' cannot be null");

    this.pattern = pattern;
    this.groupOperations = new java.util.HashMap<Integer, Object>();
    setGroupOperation(0, styleKey);
  }

  public Pattern getPattern() {
    return pattern;
  }

  public String getRegExp() {
    return pattern.pattern();
  }

  public int getRegExpFlags() {
    return pattern.flags();
  }

  public Map<Integer, Object> getGroupOperations() {
    return java.util.Collections.unmodifiableMap(groupOperations);
  }

  public void setGroupOperation(int i, String styleKey) {
    this.groupOperations.put(i, styleKey);
  }
  public void setGroupOperation(int i, RegExpRule subRule) {
    this.groupOperations.put(i, subRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(getClass().getName());
    sb.append(": ");
    sb.append("regExp: ");
    sb.append(getRegExp());
    sb.append(", ");
    sb.append("regFlags: ");
    sb.append(getRegExpFlags());
    sb.append(", ");
    sb.append("getGroupOperations: ");
    sb.append(getGroupOperations());

    return sb.toString();
  }
}
