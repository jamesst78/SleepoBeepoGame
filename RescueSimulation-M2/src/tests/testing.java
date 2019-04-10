package tests;

public abstract class testing {

	public static void main(String[] args) {
		System.out.println(reverse(bola2(bola("menrit" , 'i'),'i')));

	}
	
	public static String bola(String s1 , char c1) {
		if(s1.length()==0)
			return "";
		else {
			if(s1.charAt(0) ==c1)
				return  c1+bola(s1.substring(1) , c1) ;
			else
				return bola(s1.substring(1) , c1)+s1.charAt(0);
		}
	}
	public static String bola2(String s1 , char c1) {
		if(s1.length()==0)
			return "";
		
		else {
			if(s1.charAt(0) ==c1)
				return  bola2(s1.substring(1) , c1) +c1;
			else
				return s1.charAt(0)+bola2(s1.substring(1) , c1);
		}
	}
	public static String reverse(String k) {
		String p = "";
		for(int i = 0 ; i<k.length();i++) {
			p = k.charAt(i) + p;
		}
		return p;
	}

}
