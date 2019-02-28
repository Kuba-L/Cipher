package ie.gmit.dip;







public class Runner {
	
	public static void main(String[] args) {
		try{ //try catch block for NullPointerException
		Menu m = new Menu();
		m.start();
	}catch (NullPointerException e){
		System.out.println("Caught Exception" + e);
		e.printStackTrace();
		return;
		}
	}
}