// Copyright (c) 2015 Adam Gates
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
 * Batch brush.
 * @author Adam Gates <adam.gates84+github@gmail.com>
 */
public class BrushBatch extends Brush {

    public final static String[] exts = { "bat", "cmd" };

  public BrushBatch() {
    super("Batch");
    
    String keywords = "assoc break call cd chdir cls color copy date del dir " +
          "echo endlocal erase exit for ftype goto graftabl if md mkdir " +
          "mklink move path pause popd prompt pushd rd rem ren rename " +
          "rmdir set setlocal shift start time title type ver verify vol";

    add(new RegExpRule("^\\s*rem\\s.*$", Pattern.MULTILINE, PREPROCESSOR));
    add(new RegExpRule(RegExpRule.doubleQuotedString, STRING));
    add(new RegExpRule(RegExpRule.singleQuotedString, STRING));
    add(new RegExpRule(":[^\\s]*", COLOR1));  // labels
    add(new RegExpRule("%~\\w*\\d", VARIABLE));
    add(new RegExpRule("%%\\w", VARIABLE));
    add(new RegExpRule("%\\w*(:.*)?%", VARIABLE));
    add(new RegExpRule("!\\w*(:.*)?!", VARIABLE));
    add(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, KEYWORD));

    RegExpRule _regExpRule = new RegExpRule("(?:[^\\^])(&|>|<|2>&1|\\|)", Pattern.MULTILINE, null);
    _regExpRule.addGroupOperation(COLOR2);
    add(_regExpRule);
  }
}
