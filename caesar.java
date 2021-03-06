public class caesar{
//encryption
public static String encrypt(String plaintext, int k)
{
	StringBuilder result= new StringBuilder("");
	for( int i=0;i<plaintext.length(); i++)
	{
		if (Character.isUpperCase(plaintext.charAt(i)))
		{
			char ch = (char) (((int) plaintext.charAt(i) + k - 65) % 26 + 65);
			result.append(ch);
		}
		else 
		{
			char ch = (char) (((int) plaintext.charAt(i) + k - 97) % 26 + 97);
                        result.append(ch);
		}
	}
	String op = result.toString();
	return op;
}

//decryption

public static String decrypt(String cipher, int k)
{
        StringBuilder result= new StringBuilder("");
        for( int i=0;i<cipher.length(); i++)
        {
                if (Character.isUpperCase(cipher.charAt(i)))
                {
                        char ch = (char) (((int) cipher.charAt(i) +(26-k) - 65) % 26 + 65);
                        result.append(ch);
                }
                else
                {
                        char ch = (char) (((int) cipher.charAt(i) + (26-k) - 97) % 26 + 97);
                        result.append(ch);
                }
        }
	String op = result.toString();
        return op;
}


	public static void main (String[] args)
	{
		//Read plaintext 
		String plaintext = null;
		int shiftkey ;
		System.out.println("Enter Plain Text:");
		plaintext = System.console().readLine();
		System.out.println("Enter shift key:");
		shiftkey= Integer.parseInt(System.console().readLine());
		String output=encrypt(plaintext,shiftkey);
		System.out.println("PlainText encypted:"+ output);
		System.out.println("Cipher Decrypted:"+decrypt(output,shiftkey));
	}
}
