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

// TODO Embed the type in the pattern ie for java script search for <script type="text/javascript">

/**
 * Regular expression for HTML script. This will be used to determine if the 
 * language was implanted into the HTML using {@code left} and {@code right}. 
 * e.g. left is "&lt;script>" and right is "&lt;/script>", if there is any 
 * content start with "&lt;script>" and "&lt;/script>", the content in between 
 * these two will be parsed by using this brush.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public final class HTMLScriptRegExp {
  public static final HTMLScriptRegExp phpScriptTags = new HTMLScriptRegExp("(?:&lt;|<)\\?(?:=|php)", "\\?(?:&gt;|>)");
  public static final HTMLScriptRegExp aspScriptTags = new HTMLScriptRegExp("(?:&lt;|<)%=?", "%(?:&gt;|>)");
  
  static HTMLScriptRegExp createScriptRegExp(String tag, String type) {
    return new HTMLScriptRegExp("(?:&lt;|<)\\s*" + tag + ".*?type=\"" + type + "\".*?(?:&gt;|>)", "(?:&lt;|<)\\/\\s*" + tag + "\\s*(?:&gt;|>)");
  }
  
  private final Pattern pattern;

  public HTMLScriptRegExp(String left, String right) {
    if (left == null) throw new NullPointerException("argument 'left' cannot be null");
    if (right == null) throw new NullPointerException("argument 'right' cannot be null");
    
    pattern = Pattern.compile("(" + left + ")(.*?)(" + right + ")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
  }

  public Pattern getpattern() {
    return pattern;
  }
}
