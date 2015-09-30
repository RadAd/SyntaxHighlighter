package radsoft.syntaxhighlighter.app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Range;
import radsoft.syntaxhighlighter.brush.Brush;
import radsoft.syntaxhighlighter.SyntaxHighlighter;

import org.mozilla.universalchardet.UniversalDetector;

class SyntaxHighLighterTest
{
    public static void main(String[] s)
        throws Exception
    {
        showFile(s[0]);
    }
    
    static void showFile(String filename)
        throws java.io.IOException
    {
        final String sb = loadFile(filename);
        int p = filename.lastIndexOf('.');
        String ext = filename.substring(p + 1);
        final Brush scheme = SyntaxHighlighter.getBrush(ext);

        //System.out.println("Scheme: " + (scheme != null ? scheme.name : "Unknown"));
        
        if (scheme != null)
        {
            SyntaxHighlighter sh = new SyntaxHighlighter(scheme);
            Map<Range<Integer>, String> rs = sh.parse(sb);
            
            for (Map.Entry<Range<Integer>, String> r : rs.entrySet())
            {
                int start = r.getKey().lowerEndpoint();
                int end = r.getKey().upperEndpoint();

                System.out.println(r.getValue() + " " + start + ":" + end + " " + sb.substring(start, end));
            }
        }
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
