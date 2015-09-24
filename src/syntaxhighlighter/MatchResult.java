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
package syntaxhighlighter;

/**
 * Matched result, it will be generated when parsing the content.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public final class MatchResult {
  private final int start;
  private final int end;
  private final String styleKey;

  protected MatchResult(int start, int end, String styleKey) {
    if (styleKey == null) {
      throw new NullPointerException("argument 'styleKey' cannot be null");
    }
    this.start = start;
    this.end = end;
    this.styleKey = styleKey;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public String getStyleKey() {
    return styleKey;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[");
    sb.append(start);
    sb.append(", ");
    sb.append(end);
    sb.append(", ");
    sb.append(styleKey);
    sb.append("]");

    return sb.toString();
  }
}
