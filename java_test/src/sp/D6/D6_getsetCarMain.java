package sp.D6;

public class D6_getsetCarMain {
	public static void main(String[] args) {
		D6_getsetCar car = new D6_getsetCar()	;
		
		car.setSpeed(-100);
		System.out.println("속도"+car.getSpeed());
		
		car.setSpeed(50);
		System.out.println("속도"+car.getSpeed());
		
		if(!car.isStop()) {
			car.setStop(true);
		}
		System.out.println("속도"+car.getSpeed());
	}
	
	
}
