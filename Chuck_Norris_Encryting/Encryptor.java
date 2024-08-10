package CodinGame_files.Chuck_Norris_Encryting;

class nBit{
    public nBit(int TypeBit){
        boolean[] Bits = new boolean[TypeBit];
        int n = MathPower(2, TypeBit);
        System.err.println("d: nBit: n = " + n);
        BitsString = new String[n];

        for(int i = 0; i < n; i++){
            if(i != 0){
                boolean truen = false;
                for(int o = Bits.length - 1; o >= 0; o--){
                    if(Bits[o]){
                        truen = true;
                        continue;
                    }else{
                        if(truen){
                            for(int m = o; m < Bits.length; m++){
                                Bits[m] = false;
                            }
                        }
                        Bits[o] = true;
                        break;
                    }
                }
            }
                
            String s = "";
            for(int o = 0; o < Bits.length; o++){
                if(Bits[o]){
                    s += 1;
                }else{
                    s += 0;
                }
            }

            BitsString[i] = s;
        }
    }

    private int MathPower(int one, int two){
        int res = 1;
        for(int i = 0; i < two; i++){
            res *= one;
        }
        return res;
    }

    public int BitsStringLength(){return BitsString.length;}
    public String BitsString(int index){return BitsString[index];}

    private String[] BitsString;
}

public class Encryptor {
    public static void main(String args[]) {
        String Msg = "CC";
        nBit new7Bit = new nBit(7);
        //int newBitLength = new7Bit.BitsStringLength();
        //System.err.println(newBitLength);
        
        String s = "";
        String MsgInBits = "";
        for(int o = 0; o < Msg.length(); o++){
            MsgInBits += new7Bit.BitsString(Msg.codePointAt(o));
        }

        char[] MsgInBitsToChar = MsgInBits.toCharArray();
        char Bit = ' ';
        int n = 0;
        while(n < MsgInBitsToChar.length){
            for(int i = n; i < MsgInBitsToChar.length; i++){
                if(Bit != MsgInBitsToChar[i]){
                    if(i != 0){
                        s += " ";
                    }
                    System.err.println("Not same");
                    Bit = MsgInBitsToChar[i];
                    switch(Bit){
                        case '0':
                        s += "00 ";
                        break;
                        case '1':
                        s += "0 ";
                        break;
                    }
                    
                    n = i;
                    break;
                }else{
                    System.err.println("Same " + i + " " + n);
                    s += "0";
                    n++;
                }
            }
        }
            
        System.out.println(s);
    }
}
