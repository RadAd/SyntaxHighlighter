/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package syntaxhighlight.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import syntaxhighlight.SyntaxHighlighter;
import syntaxhighlight.theme.ThemeRDark;
import syntaxhighlighter.brush.Brush;
import syntaxhighlighter.SyntaxHighlighterParser;

/**
 * Usage example. This will just cover some of the functions. To know other 
 * available functions, please read the JavaDoc.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class Example {

  /**
   * Read the resource file from the jar.
   * @param path the resource path
   * @return the content of the resource file in byte array
   * @throws IOException error occurred when reading the content from the file
   */
  public static byte[] readResourceFile(String path) throws IOException {
    if (path == null) {
      throw new NullPointerException("argument 'path' cannot be null");
    }

    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    InputStream in = null;
    try {
      in = Example.class.getResourceAsStream(path);
      if (in == null) {
        throw new IOException("Resources not found: " + path);
      }

      int byteRead = 0;
      byte[] b = new byte[8096];

      while ((byteRead = in.read(b)) != -1) {
        bout.write(b, 0, byteRead);
      }
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException ex) {
        }
      }
    }

    return bout.toByteArray();
  }

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    long start = System.currentTimeMillis();

    Brush brush = syntaxhighlighter.parser.SyntaxHighlighter.getBrush("html");
    SyntaxHighlighterParser parser = new SyntaxHighlighterParser(brush);

    SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeRDark());
    //highlighter.setFirstLine(10);
    //highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
    highlighter.setContent(new String(readResourceFile("/example.html")));

    long end = System.currentTimeMillis();
    System.out.println("time elapsed: " + (end - start) + "ms");

    // initiate a frame and display
    JFrame frame = new JFrame();
    frame.setTitle("Syntax Highlighter");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // SyntaxHighlighter is actually a JScrollPane
    frame.setContentPane(highlighter);
    frame.setLocationByPlatform(true);
    frame.pack();
    frame.setVisible(true);
  }
}
