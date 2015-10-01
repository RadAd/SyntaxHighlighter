package radsoft.syntaxhighlighter.app;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
        if (s.length == 0)
        {
            test("test.cpp");
        }
        else
            showFile(s[0], System.out);
    }
    
    static void test(String filename)
        throws java.io.IOException
    {
        System.out.print("Testing: " + filename + " ");
        
        final String text = new String(loadResourceFile("/test/" + filename));
        final Brush scheme = getBrush(filename);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        showFile(text, scheme, ps);
        
        final String parsed = new String(baos.toByteArray());
        final String expected = new String(loadResourceFile("/expected/" + filename));
        
        System.out.println(parsed.equals(expected) ? "PASSED" : "FAILED");
    }
    
    static void showFile(String filename, PrintStream os)
        throws java.io.IOException
    {
        final String text = loadFile(filename);
        final Brush scheme = getBrush(filename);

        //os.println("Scheme: " + (scheme != null ? scheme.name : "Unknown"));
        showFile(text, scheme, os);
    }
        
    static void showFile(String text, Brush scheme, PrintStream os)
    {
        if (scheme != null)
        {
            SyntaxHighlighter sh = new SyntaxHighlighter(scheme);
            Map<Range<Integer>, String> rs = sh.parse(text);
            
            for (Map.Entry<Range<Integer>, String> r : rs.entrySet())
            {
                int start = r.getKey().lowerEndpoint();
                int end = r.getKey().upperEndpoint();

                os.println(r.getValue() + " " + start + ":" + end + " " + text.substring(start, end));
            }
        }
    }
    
    static Brush getBrush(String filename)
    {
        int p = filename.lastIndexOf('.');
        String ext = filename.substring(p + 1);
        return SyntaxHighlighter.getBrush(ext);
    }
    
    static String loadFile(String filename)
        throws java.io.IOException
    {
        String mFileEncoding = detectEncoding(filename);
        
        if (mFileEncoding == null)
            mFileEncoding = "UTF-8";
            
        return loadFile(filename, mFileEncoding);
    }
    
    static byte[] loadResourceFile(String filename)
        throws java.io.IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copy(SyntaxHighLighterTest.class.getResourceAsStream(filename), baos);
        return baos.toByteArray();
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
    
    public static void copy(InputStream in, OutputStream out)
        throws java.io.IOException
    {
        try {
            int byteRead = 0;
            byte[] b = new byte[8096];

            while ((byteRead = in.read(b)) != -1) {
                out.write(b, 0, byteRead);
            }
        } finally {
            in.close();
        }
    }
}
