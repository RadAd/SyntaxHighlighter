/**
 * This is part of the Java SyntaxHighlighter.
 * 
 * It is distributed under MIT license. See the file 'readme.txt' for
 * information on usage and redistribution of this file, and for a
 * DISCLAIMER OF ALL WARRANTIES.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
package radsoft.syntaxhighlighter.app;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.UIManager;

import radsoft.syntaxhighlighter.view.SyntaxHighlighterView;
import radsoft.syntaxhighlighter.view.theme.ThemeRDark;
import radsoft.syntaxhighlighter.brush.Brush;
import radsoft.syntaxhighlighter.SyntaxHighlighter;

/**
 * Usage example. This will just cover some of the functions. To know other 
 * available functions, please read the JavaDoc.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */
public class ViewExample {

  public static String readFile(InputStream in) throws IOException {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    
    try {
      int byteRead = 0;
      byte[] b = new byte[8096];

      while ((byteRead = in.read(b)) != -1) {
        bout.write(b, 0, byteRead);
      }
    } finally {
      in.close();
    }
    
    return new String(bout.toByteArray());
  }
  
  static InputStream open(String filename) throws FileNotFoundException
  {
    if (filename.startsWith("res:"))
        return ViewExample.class.getResourceAsStream(filename.substring(4));
    else
        return new FileInputStream(filename);
  }

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    long start = System.currentTimeMillis();

    String filename = args.length > 0 ? args[0] : "res:/example.html";
    
    int p = filename.lastIndexOf('.');
    String ext = filename.substring(p + 1);

    SyntaxHighlighterView highlighter = new SyntaxHighlighterView(new ThemeRDark());
    //highlighter.setFirstLine(10);
    //highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
    Brush brush = SyntaxHighlighter.getBrushByExtension(ext);
    highlighter.setContent(readFile(open(filename)), brush);

    long end = System.currentTimeMillis();
    System.out.println("time elapsed: " + (end - start) + "ms");

    // initiate a frame and display
    JFrame frame = new JFrame();
    frame.setTitle("Syntax Highlighter - " + brush.getName());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // SyntaxHighlighter is actually a JScrollPane
    frame.setContentPane(highlighter);
    frame.setLocationByPlatform(true);
    frame.pack();
    frame.setVisible(true);
  }
}
