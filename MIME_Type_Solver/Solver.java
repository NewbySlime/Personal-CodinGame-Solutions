package CodinGame_files.MIME_Type_Solver;
import java.util.*;
import java.io.*;
import java.math.*;
import java.net.FileNameMap;

class StringSplitting{
    public StringSplitting(){}

    public void Split(String s, char CharToSplit){
        ResStrings = new ArrayList<String>();
        ManyCharsSet = 0;
        int sLength = s.length();

        int indexBefore = 0;
        for(int i = 0; i < sLength; i++){
            if(s.charAt(i) == CharToSplit || i == sLength - 1){
                ManyCharsSet++;
                String res = "";
                for(int o = indexBefore; o <= i; o++){
                    char CharAt = s.charAt(o);
                    if(CharAt != '.'){
                        res += CharAt;
                    }
                }

                ResStrings.add(res);
                indexBefore = i + 1;
            }
        }
    }

    public int ManyChars(){return ManyCharsSet;}
    public List<String> ListStrings(){return ResStrings;}

    private int ManyCharsSet = 0;
    private List<String> ResStrings = new ArrayList<String>();
}

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static String[] EXTs = {
        "wav",
"mp3",
"pdf"
    };

    static String[] MTs = {
        "audio/x-wav",
 "audio/mpeg",
 "application/pdf"
    };

    static String[] FileNames = {
        "a",
    "a.wav",
    "b.wav.tmp",
    "test.vmp3",
    "pdf",
    ".pdf",
    "mp3",
    "report..pdf",
    "defaultwav",
    ".mp3.",
    "final."
    };


    public static void main(String args[]) {
        Dictionary ExtFiles = new Hashtable<String, String>();

        /*Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.*/

        int N = EXTs.length;
        int Q = FileNames.length;
        for (int i = 0; i < N; i++) {
            String EXT = EXTs[i].toLowerCase(); // file extension
            String MT = MTs[i]; // MIME type.

            ExtFiles.put(EXT, MT);
        }

        //in.nextLine();
        
        StringSplitting StringSplit = new StringSplitting();
        for (int i = 0; i < Q; i++) {
            String FNAME = FileNames[i]; // One file name per line.
            System.out.println(FNAME);
            
            String Answer = "UNKNOWN";
            if(FNAME.charAt(FNAME.length() - 1) != '.'){
                StringSplit.Split(FNAME, '.');
                List<String> ListOfStrings = StringSplit.ListStrings();
                int ListStringSize = ListOfStrings.size();
                //String MIMEType = ListOfStrings.get(ListOfStrings.size());
                //System.out.println(MIMEType);

                System.out.println(ListOfStrings.size());
                System.out.println(StringSplit.ManyChars());


                if(ListStringSize > 0 && StringSplit.ManyChars() > 1){
                    String ExtFilesType = (String)ExtFiles.get(ListOfStrings.get(ListStringSize - 1).toLowerCase());
                    if(ExtFilesType != null){
                        Answer = ExtFilesType;
                    }
                }

            }
            System.out.println(Answer);
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.
        //System.out.println("UNKNOWN");
    }
}