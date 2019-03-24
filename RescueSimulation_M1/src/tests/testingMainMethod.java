package tests;

import java.util.ArrayList;
import java.util.Random;

public class testingMainMethod {
public static void main(String[] args) {
	
	
	ArrayList r = new ArrayList<>();
	int i = 1;
	r.add(5);
	r.add(4);
	r.add(2);
	int y = (int) r.get(i);
	System.out.println(r);
	for(int k = 0 ;k<r.size() ; k++) {
		System.out.println(r.get(k) + " ");
	
	}
	
}
}
