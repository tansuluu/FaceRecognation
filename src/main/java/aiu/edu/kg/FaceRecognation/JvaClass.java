package aiu.edu.kg.FaceRecognation;

import java.io.*;

public class JvaClass {
    public static void main(String[] args) {
        System.out.println(1);
//        try {
//            String prg = "import sys";
//            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\tanya\\Desktop\\FaceRecognation\\src\\main\\java\\aiu\\edu\\kg\\FaceRecognation\\fileto.py"));
//            out.write(prg);
//            out.close();
//            Process p = Runtime.getRuntime().exec("python C:\\Users\\tanya\\Desktop\\FaceRecognation\\src\\main\\java\\aiu\\edu\\kg\\FaceRecognation\\fileto.py");
//            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String ret = in.readLine();
//            System.out.println("value is : "+ret);
            ScriptPython scriptPython = new ScriptPython();
            scriptPython.runScript();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
    }


}
