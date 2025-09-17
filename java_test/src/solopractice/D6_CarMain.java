package solopractice;

public class D6_CarMain {
	public static void main(String[] args) {
		D6_Car car1 = new D6_Car();
		System.out.println(car1.company);
		
		D6_Car car2 = new D6_Car("마티즈");
		System.out.println(car2.company+car2.model);
		
		D6_Car car3 = new D6_Car("제네시스","검정");
		System.out.println(car3.company+car3.model+car3.color);
		
		D6_Car car4 = new D6_Car("롤스로이스","빨강",200);
		System.out.println(car4.company+car4.model+car4.color+car4.maxSpeed);
	}
}
