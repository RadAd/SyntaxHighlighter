package test;

import java.io.*;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.SyntaxHighlighter;
import syntaxhighlighter.MatchResult;

import org.mozilla.universalchardet.UniversalDetector;

class Project
{
    static final char ESCAPE = (char) 27;
    static final String COLOR_NORMAL      = ESCAPE + "[0m";
    static final String COLOR_BLACK       = ESCAPE + "[30m";
    static final String COLOR_DARK_GRAY   = ESCAPE + "[1;30m";
    static final String COLOR_DARK_RED    = ESCAPE + "[31m";
    static final String COLOR_RED         = ESCAPE + "[1;31m";
    static final String COLOR_DARK_GREEN  = ESCAPE + "[32m";
    static final String COLOR_GREEN       = ESCAPE + "[1;32m";
    static final String COLOR_DARK_YELLOW = ESCAPE + "[33m";
    static final String COLOR_YELLOW      = ESCAPE + "[1;33m";
    static final String COLOR_DARK_BLUE   = ESCAPE + "[34m";
    static final String COLOR_BLUE        = ESCAPE + "[1;34m";
    static final String COLOR_DARK_PURPLE = ESCAPE + "[35m";
    static final String COLOR_PURPLE      = ESCAPE + "[1;35m";
    static final String COLOR_DARK_CYAN   = ESCAPE + "[36m";
    static final String COLOR_CYAN        = ESCAPE + "[1;36m";
    static final String COLOR_GRAY        = ESCAPE + "[37m";
    static final String COLOR_WHITE       = ESCAPE + "[1;37m";
    
    public static void main(String[] s)
        throws Exception
    {
        String filename = s[0];
        final String sb = loadFile(filename);
        int p = filename.lastIndexOf('.');
        String ext = filename.substring(p + 1);
        final Brush scheme = SyntaxHighlighter.getBrush(ext);

        //System.out.println("Scheme: " + (scheme != null ? scheme.name : "Unknown"));
        
        int last = 0;
        if (sb.charAt(0) == '\uFFFE' || sb.charAt(0) == '\uFEFF')   // BOM
            last = 1;
            
        if (scheme != null)
        {
            SyntaxHighlighter sh = new SyntaxHighlighter(scheme);
            java.util.List<MatchResult> rs = sh.parse(sb);
            for (MatchResult r : rs)
            {
                if (r.getStart() > last)
                {
                    if (r.getStart() > last)
                        System.out.print(sb.substring(last, r.getStart()));
                    //System.out.println(r.getStyleKey() + " " + r.getStart() + ":" + r.getEnd() + " " + sb.substring(r.getStart(), r.getEnd()));
                    String Color = COLOR_NORMAL;
                    switch (r.getStyleKey())
                    {
                    case Brush.PREPROCESSOR:
                        Color = COLOR_YELLOW;
                        break;
                    case Brush.KEYWORD:
                        Color = COLOR_CYAN;
                        break;
                    case Brush.STRING:
                    case Brush.VALUE:
                        Color = COLOR_PURPLE;
                        break;
                    case Brush.COLOR1:
                        Color = COLOR_YELLOW;
                        break;
                    case Brush.VARIABLE:
                        Color = COLOR_RED;
                        break;
                    case Brush.COMMENTS:
                        Color = COLOR_WHITE;
                        break;
                    }
                    System.out.print(Color + sb.substring(r.getStart(), r.getEnd()) + COLOR_NORMAL);
                    last = r.getEnd();
                }
            }
        }
        
        System.out.print(sb.substring(last));
    }
    
    static String loadFile(String filename)
        throws java.io.IOException
    {
        String mFileEncoding = detectEncoding(filename);
        
        if (mFileEncoding == null)
            mFileEncoding = "UTF-8";
            
        return loadFile(filename, mFileEncoding);
    }
    
    static String detectEncoding(String filename)
        throws java.io.IOException
    {
        try (InputStream is = new FileInputStream(filename))
        {
            UniversalDetector detector = new UniversalDetector(null);
            byte[] buf = new byte[4096];
            int nread;
            while ((nread = is.read(buf)) > 0 && !detector.isDone())
            {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            
            return detector.getDetectedCharset();
        }
        //detector.reset();
    }
    
    static String loadFile(String filename, String encoding)
        throws java.io.IOException
    {
        byte[] encoded = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filename));
        return new String(encoded, encoding);
    }
}
