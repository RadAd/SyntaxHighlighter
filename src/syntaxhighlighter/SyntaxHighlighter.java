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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.brush.RegExpRule;
import syntaxhighlighter.brush.*;

/**
 * The parser of the syntax highlighter.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public final class SyntaxHighlighter {

    private static final Brush plainBrush = new BrushPlain();
    private static final Map<String, Brush> brushes = new HashMap<String, Brush>();
    private static final List<Brush> htmlBrushes = new ArrayList<Brush>();
    
    private static void register(String[] exts, Brush brush) {
        for (String ext : exts) {
            brushes.put(ext, brush);
        }
        if (brush.getHTMLScriptRegExp() != null) {
            htmlBrushes.add(brush);
        }
    }
    
    public static Brush getBrush(String ext) {
        Brush brush = brushes.get(ext);
        if (brush == null) {
            brush = plainBrush;
        }
        return brush;
    }
    
    public static Map<String, Brush> getBrushes() {
        return java.util.Collections.unmodifiableMap(brushes);
    }
    
    static {
        register(BrushAppleScript.exts, new BrushAppleScript());
        register(BrushAS3.exts, new BrushAS3());
        register(BrushBash.exts, new BrushBash());
        register(BrushColdFusion.exts, new BrushColdFusion());
        register(BrushCpp.exts, new BrushCpp());
        register(BrushCSharp.exts, new BrushCSharp());
        register(BrushCss.exts, new BrushCss());
        register(BrushDelphi.exts, new BrushDelphi());
        register(BrushDiff.exts, new BrushDiff());
        register(BrushErlang.exts, new BrushErlang());
        register(BrushGroovy.exts, new BrushGroovy());
        register(BrushJava.exts, new BrushJava());
        register(BrushJavaFX.exts, new BrushJavaFX());
        register(BrushJScript.exts, new BrushJScript());
        register(BrushPerl.exts, new BrushPerl());
        register(BrushPhp.exts, new BrushPhp());
        register(BrushPowerShell.exts, new BrushPowerShell());
        register(BrushPython.exts, new BrushPython());
        register(BrushRuby.exts, new BrushRuby());
        register(BrushSass.exts, new BrushSass());
        register(BrushScala.exts, new BrushScala());
        register(BrushSql.exts, new BrushSql());
        register(BrushVb.exts, new BrushVb());
        register(BrushXml.exts, new BrushXml(false));
        register(BrushXml.extshtml, new BrushXml(true));
    }

  private Brush brush;
  private List<Brush> htmlScriptBrushList;

  public SyntaxHighlighter() {
    this(plainBrush);
  }

  public SyntaxHighlighter(Brush brush) {
    if (brush == null) throw new NullPointerException("argument 'brush' cannot be null");
    this.brush = brush;
    this.htmlScriptBrushList = htmlBrushes;
  }

  public void setHTMLScriptBrushList(List<Brush> htmlScriptBrushList) {
    this.htmlScriptBrushList = htmlScriptBrushList;
  }
  
  public Brush getBrush() {
    return brush;
  }

  public void setBrush(Brush brush) {
    if (brush == null) throw new NullPointerException("argument 'brush' cannot be null");
    this.brush = brush;
  }
  
  private static void addMatch(RangeMap<Integer, String> matches, int start, int end, String styleKey, boolean override) {
    if (styleKey == null) throw new NullPointerException("argument 'styleKey' cannot be null");
    Range<Integer> r = Range.closedOpen(start, end);
    // TODO Dont put if there is already something there
    Map.Entry<Range<Integer>, String> e = matches.getEntry(start);
    //if (override || matches.subRangeMap(r).asMapOfRanges().isEmpty())
    if (override || e == null)
    {
        if (e != null)
            matches.remove(e.getKey());
        e = matches.getEntry(end);
        if (e != null)
            matches.remove(e.getKey());
        matches.put(r, styleKey);
    }
  }

  private static void removeMatches(RangeMap<Integer, String> matches, int start, int end) {
    Range<Integer> r = Range.closedOpen(start, end);
    matches.remove(r);
  }
  
  public Map<Range<Integer>, String> parse(CharSequence content) {
    return parse(content, 0, content.length());
  }
  
  public Map<Range<Integer>, String> parse(CharSequence content, int allstart, int allend) {
    if (content == null) throw new NullPointerException("argument 'content' cannot be null");
    
    RangeMap<Integer, String> matches = TreeRangeMap.create();
    
    parse1(matches, brush, content, allstart, allend);
    
    // parse the HTML-Script brushes later
    if (brush.isHtml() && htmlScriptBrushList != null) {
        for (Brush htmlScriptBrush : htmlScriptBrushList) {
          Pattern _pattern = htmlScriptBrush.getHTMLScriptRegExp().getpattern();

          Matcher matcher = _pattern.matcher(content.subSequence(allstart, allend));
          while (matcher.find()) {
            // the content of HTML-Script, parse it using the HTML-Script brush
            int start = matcher.start(1) + allstart;
            int end = matcher.end(1) + allstart;
            removeMatches(matches, start, end);
            parse1(matches, htmlScriptBrush, content, start, end);
          }
        }
    }
    
    return matches.asMapOfRanges();
  }

  private static void parse1(RangeMap<Integer, String> matches, Brush brush, CharSequence content, int allstart, int allend) {
    // parse the RegExpRule in the brush first
    List<RegExpRule> regExpRuleList = brush.getRegExpRuleList();
    for (RegExpRule regExpRule : regExpRuleList) {
      parse2(matches, regExpRule, content, allstart, allend);
    }
  }

  private static void parse2(RangeMap<Integer, String> matches, RegExpRule regExpRule, CharSequence content, int allstart, int allend) {
    List<Object> groupOperations = regExpRule.getGroupOperations();

    Pattern regExpPattern = regExpRule.getPattern();
    Matcher matcher = regExpPattern.matcher(content.subSequence(allstart, allend));
    while (matcher.find()) {
      // deal with the matched result
      for (int groupId = 0; groupId < groupOperations.size(); ++groupId) {
        Object operation = groupOperations.get(groupId);

        // the start and end position of the match
        int start = matcher.start(groupId);
        int end = matcher.end(groupId);
        if (start == -1 || end == -1) {
          continue;
        }
        start += allstart;
        end += allstart;

        if (operation instanceof String) {
          // add the style to the match
          addMatch(matches, start, end, (String) operation, regExpRule.getOverride());
        } else if (operation instanceof RegExpRule) {
          // parse the result using the <code>operation</code> RegExpRule
          parse2(matches, (RegExpRule) operation, content, start, end);
        }
      }
    }
  }
}
