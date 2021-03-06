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

// TODO
// Incomplete strings
// Load brushes from a file

/**
 * Brush for syntax highlighter.
 * 
 * In syntax highlighter, every supported programming language has its own 
 * brush. Brush contain a set of rules, the highlighter/parser will use these 
 * rules to determine the structure of the code and apply different color to 
 * different group of component.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 * @author Adam Gates <Adam.Gates84+github@gmail.com>
 */
public class Brush {
    
    public final static String PREPROCESSOR = "preprocessor";
    public final static String COMMENTS = "comments";
    public final static String KEYWORD = "keyword";
    public final static String FUNCTIONS = "functions";
    public final static String STRING = "string";
    public final static String VALUE = "value";
    public final static String VARIABLE = "variable";
    public final static String CONSTANTS = "constants";
    public final static String COLOR1 = "color1";
    public final static String COLOR2 = "color2";
    public final static String COLOR3 = "color3";

  private final String name;
  private final List<RegExpRule> regExpRuleList = new java.util.ArrayList<RegExpRule>();
  private Pattern htmlScriptPattern = null;

  protected Brush(String name) {
    if (name == null)  throw new NullPointerException("argument 'name' cannot be null");
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public List<RegExpRule> getRegExpRuleList() {
    return java.util.Collections.unmodifiableList(regExpRuleList);
  }

  protected void add(RegExpRule rule) {
    if (rule == null)  throw new NullPointerException("argument 'rule' cannot be null");
    this.regExpRuleList.add(rule);
  }

  public Pattern getHTMLScriptPattern() {
    return htmlScriptPattern;
  }

  protected void setHTMLScriptPattern(Pattern htmlScriptPattern) {
    this.htmlScriptPattern = htmlScriptPattern;
  }

  protected void setHTMLScriptPattern(String regExp, int regFlags) {
    this.htmlScriptPattern = Pattern.compile(regExp, regFlags);
  }

  /**
   * Similar function in JavaScript SyntaxHighlighter for making string of 
   * keywords separated by space into regular expression.
   * @param str the keywords separated by space
   * @return the treated regexp string
   */
  protected static String getKeywords(String str) {
    if (str == null)  throw new NullPointerException("argument 'str' cannot be null");
    return "\\b(?:" + str.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", "|") + ")\\b";
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(getClass().getName());
    sb.append("\n");
    sb.append("rule count: ");
    sb.append(regExpRuleList.size());
    for (int i = 0, iEnd = regExpRuleList.size(); i < iEnd; i++) {
      RegExpRule rule = regExpRuleList.get(i);
      sb.append("\n");
      sb.append(i);
      sb.append(": ");
      sb.append(rule.toString());
    }
    sb.append("\n");
    sb.append("HTML Script Pattern: ");
    sb.append(htmlScriptPattern);

    return sb.toString();
  }
}
