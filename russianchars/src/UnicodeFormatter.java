
public class UnicodeFormatter {
	
	static public String byteToHex(byte b) {
	    // Returns hex String representation of byte b
	    char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f' };
	    char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
	    return new String(array);
	  }

	  static public String charToHex(char c) {
	    // Returns hex String representation of char c
	    byte hi = (byte) (c >>> 8);
	    byte lo = (byte) (c & 0xff);
	    return byteToHex(hi) + byteToHex(lo);
	  }
	  
	  public static void main(String[] args) {
		
		  String ss = "äå";
		  ss = "асдцвбфршя";
		  
		  
		  byte[] bb = ss.getBytes();
		  for (int k = 0; k < bb.length; k++) {
		      System.out.println("[" + k + "] = " + "0x"
		          + UnicodeFormatter.byteToHex(bb[k]));
		    }
	}

}
