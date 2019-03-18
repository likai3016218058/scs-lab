package main;

public class Findmoney {
	public boolean findMoney (int x){
		boolean result=true;
		if(x>50) x-=50; 
		if(x>20) x-=20;
		if(x>10) x-=10;
		else if(x>5) x-=5;
		if(x>3||x<0) result=false;
		return result;
	}

}
