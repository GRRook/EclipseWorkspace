import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Program {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
            String str = br.readLine(); //This can return 'a = 5;','b = "Text";' or 'pckg.example.MyClass.run(5);'
            if(str == null)
                return;

            runCode(str); //How would I do this?
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
	  }
	
	
	public static void runCode(String s) throws Exception{
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
        File jf = new File("test.java"); //create file in current working directory
        PrintWriter pw = new PrintWriter(jf);
        pw.println("public class test {public static void main(){"+s+"}}");
        pw.close();
        Iterable fO = sjfm.getJavaFileObjects(jf);
        if(!jc.getTask(null,sjfm,null,null,null,fO).call()) { //compile the code
            throw new Exception("compilation failed");
        }
        URL[] urls = new URL[]{new File("").toURI().toURL()}; //use current working directory
        URLClassLoader ucl = new URLClassLoader(urls);
        Object o= ucl.loadClass("test").newInstance();
        o.getClass().getMethod("main").invoke(o);

    }
}


