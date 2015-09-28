package test;

import java.io.FileInputStream;
import java.io.InputStream;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.SyntaxHighlighter;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Range;

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
        if (s.length == 0)
            showAbout();
        else
            showFile(s[0]);
    }
    
    static void showAbout() {
        System.out.println("CliSyntaxHighlighter");
        System.out.println();
        System.out.println("Output to stdout syntax highlighting using ANSI escape sequences.");
        System.out.println();
        System.out.println("Brushes supported:");
        
        Map<String, Set<String>> b = new java.util.TreeMap<String, Set<String>>();
        
        for (Map.Entry<String, Brush> brushEntry : SyntaxHighlighter.getBrushes().entrySet())
        {
            Set<String> s = b.get(brushEntry.getValue().getName());
            if (s == null)
            {
                s = new java.util.TreeSet<String>();
                b.put(brushEntry.getValue().getName(), s);
            }
            s.add(brushEntry.getKey());
            //System.out.println("\t" + brushEntry.getKey() +"\t" + brushEntry.getValue().getName());
        }
        
        for (Map.Entry<String, Set<String>> brushEntry : b.entrySet())
        {
            System.out.print("\t" + brushEntry.getKey() +"\t");
            boolean first = true;
            for (String s : brushEntry.getValue())
            {
                if (!first)
                    System.out.print(", ");
                System.out.print(s);
                first = false;
            }
            System.out.println();
        }
    }
    
    static void showFile(String filename)
        throws java.io.IOException
    {
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
            Map<Range<Integer>, String> rs = sh.parse(sb);
            if (true)
            {
                for (Map.Entry<Range<Integer>, String> r : rs.entrySet())
                {
                    int start = r.getKey().lowerEndpoint();
                    int end = r.getKey().upperEndpoint();

                    if (start > last)
                        System.out.print(sb.substring(last, start));
                    String Color = COLOR_NORMAL;
                    switch (r.getValue())
                    {
                    case Brush.PREPROCESSOR: Color = COLOR_YELLOW;    break;
                    case Brush.KEYWORD:      Color = COLOR_CYAN;      break;
                    case Brush.STRING:
                    case Brush.VALUE:        Color = COLOR_PURPLE;    break;
                    case Brush.COLOR1:       Color = COLOR_YELLOW;    break;
                    case Brush.COLOR2:       Color = COLOR_GRAY;      break;
                    case Brush.VARIABLE:     Color = COLOR_RED;       break;
                    case Brush.COMMENTS:     Color = COLOR_WHITE;     break;
                    }
                    System.out.print(Color + sb.substring(start, end) + COLOR_NORMAL);
                    last = end;
                }
            }
            else
            {
                for (Map.Entry<Range<Integer>, String> r : rs.entrySet())
                {
                    int start = r.getKey().lowerEndpoint();
                    int end = r.getKey().upperEndpoint();

                    System.out.println(r.getValue() + " " + start + ":" + end + " " + sb.substring(start, end));

                    last = end;
                }
                last = sb.length();
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
