import java.util.*;
//check if it is an uppercase alphabet
class AlphabetChecker{
  String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  boolean checker(char c)
 {
  for(int i=0;i < alphabets.length();i++)
  {
   if(alphabets.charAt(i)==c)
                            return true;       
  }
  return false;
 }
}

class PlayFairAlgo{
       AlphabetChecker b=new AlphabetChecker();
       char keyMatrix[][]=new char[5][5];  
       //check for repitition
       boolean repeat(char c)
       {
           if(!b.checker(c))
           {
               return true;
           }
                for(int i=0;i < keyMatrix.length;i++)
                {
                    for(int j=0;j < keyMatrix[i].length;j++)
                    { 
                        if(keyMatrix[i][j]==c || c=='J')
                            return true;
                    }
                }
                return false;
       }
       //insertion of key followed by non matched alphabets
       void insertKey(String key)
       {
            key=key.toUpperCase();
            key=key.replaceAll("J", "I");
            key=key.replaceAll(" ", "");
            int a=0,b=0;
            
            for(int k=0;k < key.length();k++)
            {
                    if(!repeat(key.charAt(k)))
                    {
                        keyMatrix[a][b++]=key.charAt(k);
                        if(b>4)
                        {
                            b=0;
                            a++;
                        }
                    }
            }
           
            char p='A';
            
            while(a < 5)
            {
                   while(b < 5)
                   {
                        if(!repeat(p)) 
                        {
                            keyMatrix[a][b++]=p;
                            
                        }
                      p++;
                   }
                   b=0;
                   a++;
            }
            // print matrix
            System.out.println("-Key Matrix-");
            for(int i=0;i < 5;i++)
            {
                System.out.println();
                for(int j=0;j < 5;j++)
                {
                    System.out.print("\t"+keyMatrix[i][j]);
                }
            }
            
       }
       // row index 
       int rowPos(char c)
       {
             for(int i=0;i < keyMatrix.length;i++)
                {
                    for(int j=0;j < keyMatrix[i].length;j++)
                    { 
                        if(keyMatrix[i][j]==c)
                            return i;
                    }
                }
             return -1;
       }
       // column index
       int columnPos(char c)
       {
             for(int i=0;i < keyMatrix.length;i++)
                {
                    for(int j=0;j < keyMatrix[i].length;j++)
                    { 
                        if(keyMatrix[i][j]==c)
                            return j;
                    }
                }
             return -1;
       }
       // encryption
       String encryption(String plain)
       {
           plain=plain.toUpperCase();
           char a=plain.charAt(0),b=plain.charAt(1);
           String cipherChar="";
           int r1,c1,r2,c2;
           r1=rowPos(a);
           c1=columnPos(a);
           r2=rowPos(b);
           c2=columnPos(b);
        
           if(c1==c2)
           {
                ++r1;
               ++r2;
               if(r1>4)
                   r1=0;
               
               if(r2>4)
                   r2=0;
               cipherChar+=keyMatrix[r1][c2];
               cipherChar+=keyMatrix[r2][c1];
           }
           else if(r1==r2)
           {    
               ++c1;
               ++c2;
               if(c1>4)
                   c1=0;
               
               if(c2>4)
                   c2=0;
               cipherChar+=keyMatrix[r1][c1];
               cipherChar+=keyMatrix[r2][c2];
               
           }
           else{
               cipherChar+=keyMatrix[r1][c2];
               cipherChar+=keyMatrix[r2][c1];
           }
           return cipherChar;
       }
       
       
       
       // feeding two at a time to encrypt
       String Encryptfeeder(String plainText,String key)
       {
           insertKey(key);
           String cipherText="";
           plainText=plainText.replaceAll("j", "i");
           plainText=plainText.replaceAll(" ", "");
           plainText=plainText.toUpperCase();
           int len=plainText.length();          
          
           
           for(int i=0;i < len-1;i=i+2)
           {
              cipherText+=encryption(plainText.substring(i,i+2)); 
           }
           return cipherText;
       }
       
       // decryption
       String decryption(String cipher)
       {
           cipher=cipher.toUpperCase();
           char a=cipher.charAt(0),b=cipher.charAt(1);
           String plainChar="";
           int r1,c1,r2,c2;
           r1=rowPos(a);
           c1=columnPos(a);
           r2=rowPos(b);
           c2=columnPos(b);
        
           if(c1==c2)
           {
                --r1;
               --r2;
               if(r1 < 0)
                   r1=4;
               
               if(r2 < 0)
                   r2=4;
               plainChar+=keyMatrix[r1][c2];
               plainChar+=keyMatrix[r2][c1];
           }
           else if(r1==r2)
           {    
               --c1;
               --c2;
               if(c1 < 0)
                   c1=4;
               
               if(c2 < 0)
                   c2=4;
               plainChar+=keyMatrix[r1][c1];
               plainChar+=keyMatrix[r2][c2];
               
           }
           else{
               plainChar+=keyMatrix[r1][c2];
               plainChar+=keyMatrix[r2][c1];
           }
           return plainChar;
       }
       
       
       
       // feed two at a time to decrypt
       String Decryptfeeder(String cipherText,String key)
       {
           String plainText="";
           cipherText=cipherText.replaceAll("j", "i");
           cipherText=cipherText.replaceAll(" ", "");
           cipherText=cipherText.toUpperCase();
           int len=cipherText.length();
           for(int i=0;i < len-1;i=i+2)
           {
              plainText+=decryption(cipherText.substring(i,i+2));      
           }
           return plainText;
       }
       
      
}

class playfair{
       public static void main(String args[])
       {
            PlayFairAlgo p=new PlayFairAlgo();
            Scanner scn=new Scanner(System.in);
            String key,cipherText,plainText;
	                
            System.out.println("Enter plaintext:");
            plainText=scn.nextLine();
            // check length of string and append with "Z" if odd
	    if(plainText.length() %2!=0)
		        plainText=plainText + "Z";
            
            System.out.println("Enter Key:");
            key=scn.nextLine();
            
            cipherText=p.Encryptfeeder(plainText,key);
            
           System.out.println("\nEncrypted text:");
           System.out.println("\n"+cipherText);
           String encryptedText=p.Decryptfeeder(cipherText, key);
           System.out.println("Decrypted text:" );
           System.out.println("\n"+encryptedText);
           
       }
}

