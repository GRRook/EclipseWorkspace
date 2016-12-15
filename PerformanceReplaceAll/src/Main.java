import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "&lt;!DOCTYPE HTML PUBLIC &quot;-//W3C//DTD HTML 4.01 Frameset//EN&quot; &quot;http://www.w3.org/TR/html4/frameset.dtd&quot;&gt; &lt;!-- NewPage --&gt; &lt;html lang=&quot;de&quot;&gt; &lt;head&gt; &lt;meta http-equiv=&quot;Content-Type&quot; content=&quot;text/html&quot; charset=&quot;iso-8859-1&quot;&gt; &lt;title&gt;Apache Commons Lang 3.4 API&lt;/title&gt; &lt;script type=&quot;text/javascript&quot;&gt; targetPage = &quot;&quot; + window.location.search; if (targetPage != &quot;&quot; &amp;&amp; targetPage != &quot;undefined&quot;) targetPage = targetPage.substring(1); if (targetPage.indexOf(&quot;:&quot;) != -1 || (targetPage != &quot;&quot; &amp;&amp; !validURL(targetPage))) targetPage = &quot;undefined&quot;; function validURL(url) { try { url = decodeURIComponent(url); } catch (error) { return false; } var pos = url.indexOf(&quot;.html&quot;); if (pos == -1 || pos != url.length - 5) return false; var allowNumber = false; var allowSep = false; var seenDot = false; for (var i = 0; i &lt; url.length - 5; i++) { var ch = url.charAt(i); if ('a' &lt;= ch &amp;&amp; ch &lt;= 'z' || 'A' &lt;= ch &amp;&amp; ch &lt;= 'Z' || ch == '$' || ch == '_' || ch.charCodeAt(0) &gt; 127) { allowNumber = true; allowSep = true; } else if ('0' &lt;= ch &amp;&amp; ch &lt;= '9' || ch == '-') { if (!allowNumber) return false; } else if (ch == '/' || ch == '.') { if (!allowSep) return false; allowNumber = false; allowSep = false; if (ch == '.') seenDot = true; if (ch == '/' &amp;&amp; seenDot) return false; } else { return false; } } return true; } function loadFrames() { if (targetPage != &quot;&quot; &amp;&amp; targetPage != &quot;undefined&quot;) top.classFrame.location = top.targetPage; } &lt;/script&gt; &lt;/head&gt; &lt;frameset cols=&quot;20%,80%&quot; title=&quot;Documentation frame&quot; onload=&quot;top.loadFrames()&quot;&gt; &lt;frameset rows=&quot;30%,70%&quot; title=&quot;Left frames&quot; onload=&quot;top.loadFrames()&quot;&gt; &lt;frame src=&quot;overview-frame.html&quot; name=&quot;packageListFrame&quot; title=&quot;All Packages&quot;&gt; &lt;frame src=&quot;allclasses-frame.html&quot; name=&quot;packageFrame&quot; title=&quot;All classes and interfaces (except non-static nested types)&quot;&gt; &lt;/frameset&gt; &lt;frame src=&quot;overview-summary.html&quot; name=&quot;classFrame&quot; title=&quot;Package, class and interface descriptions&quot; scrolling=&quot;yes&quot;&gt; &lt;noframes&gt; &lt;noscript&gt; &lt;div&gt;JavaScript is disabled on your browser.&lt;/div&gt; &lt;/noscript&gt; &lt;h2&gt;Frame Alert&lt;/h2&gt; &lt;p&gt;This document is designed to be viewed using the frames feature. If you see this message, you are using a non-frame-capable web client. Link to &lt;a href=&quot;overview-summary.html&quot;&gt;Non-frame version&lt;/a&gt;.&lt;/p&gt; &lt;/noframes&gt; &lt;/frameset&gt; &lt;/html&gt;";
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(sdf.format(new Date()));
		String res = test.replaceAll("a", "A");
		String res1 = test.replaceAll("e", "A");
		String res2 = test.replaceAll("s", "A");
		String res3 = test.replaceAll("e", "A")
		;
		String res4 = test.replaceAll("s", "A");
		StringUtils.replace(test, "a", "A");
		StringUtils.replace(test, "e", "A");
		StringUtils.replace(test, "s", "A");
		StringUtils.replace(test, "k", "A");
		StringUtils.replace(test, "i", "A");
		System.out.println(sdf.format(new Date()));*/
		
		 
		String res  = replace(test, "&lt;", "<");
		String res1 = replace(res, "&gt;", ">");
		String res2 = replace(res1, "&quot;", "\"");
		String res3 = replace(res2, " ", "");
		System.out.println(res3);
		
		
			System.out.println(timeM1(20000));
			System.out.println(timeM2(20000));
			System.out.println(sdf.format(new Date()));
			System.out.println(timeM3(20000));
			System.out.println(sdf.format(new Date()));
		
	}
	static String s = "111222111222";	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
    public static int timeM1(int n) {
    	System.out.println(sdf.format(new Date()));
        int res = 0;
        for (int x = 0; x < n; x++) {
            res += s.replaceAll("111", "333").length();
        }
        System.out.println(sdf.format(new Date()));
        return res;
    }

    public static int timeM2(int n) {
    	System.out.println(sdf.format(new Date()));
        int res = 0;
        for (int x = 0; x < n; x++) {
            res += StringUtils.replace(s, "111", "333", -1).length();
        }
        System.out.println(sdf.format(new Date()));
        return res;
    }
    
    public static int timeM3(int n) {
    	//System.out.println(sdf.format(new Date()));
        int res = 0;
        for (int x = 0; x < n; x++) {
            res += replace(s, "111", "333").length();
        }
        //System.out.println(sdf.format(new Date()));
        return res;
    }
    
    public static String replace(String source, String os, String ns) {
        if (source == null) {
            return null;
        }
        int i = 0;
        if ((i = source.indexOf(os, i)) >= 0) {
            char[] sourceArray = source.toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder (sourceArray.length);
            buf.append (sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // Replace all remaining instances of oldString with newString.
            while ((i = source.indexOf(os, i)) > 0) {
                buf.append (sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append (sourceArray, j, sourceArray.length - j);
            source = buf.toString();
            buf.setLength (0);
        }
        return source;
    }

}
