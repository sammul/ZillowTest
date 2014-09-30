/**
 * In this class MyString, I implemented a method "stringToLong". It supports an optional sign at the first character.
 * Legal input: 10, +10, -55...
 * Limitations: (1) If the input is illegal (ex. null, empty string, 100s0), the method will throw an exception containing message "Illegal Input String".
 * (2) If the input is larger than Long.MAX_VALUE, the method will throw an exception containing message "MAX OVERFLOW".
 * (3) If the input is smaller than Long.MIN_VALUE, the method will throw an exception containing message "MIN_OVERFLOW".
 * 
* @author Xia Wu
* @Time 9/28/2014
*
*/
public class MyString{
	/**
	 * Convert the string to a long
	 * @param s string to convert
	 */
	public long stringToLong(String s) throws IllegalArgumentException{
		long res = 0, lmax=Long.MAX_VALUE%10, lmin=Math.abs(Long.MIN_VALUE%10), overflow = Long.MAX_VALUE/10;
		long prefix = 1;
		int i = 0;
		if(s==null||s.length()==0)
			throw new IllegalArgumentException("Illegal Input String");
		if(s.charAt(0)=='-'){
			prefix = -1;
			i=1;
		}
		else if(s.charAt(0)=='+')
			i=1;
		if(i==s.length())
			throw new IllegalArgumentException("Illegal Input String");
		for(; i<s.length(); i++){
		    int c = s.charAt(i)-'0';
		    if(c>9 || c<0)
		    	throw new IllegalArgumentException("Illegal Input String");
		    if(res >= overflow){
			    if(prefix==1){
				    if(res>overflow || c>lmax)
				    	throw new IllegalArgumentException("MAX OVERFLOW");
				}
			    else{
			    	if(res==overflow && c==lmin && i==s.length()-1)
			    		return Long.MIN_VALUE;
			    	else if(res>overflow || c>=lmin)
			    		throw new IllegalArgumentException("MIN OVERFLOW");
			    }
		    }
		    res = res*10+c;
		 }
		 return prefix*res;
	}
}
