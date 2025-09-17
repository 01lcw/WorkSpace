package solopractice;

public class D6_Car {
	String company = "현대";
	String model;
	String color;
	int maxSpeed;
	
	D6_Car(){}
	
	D6_Car(String model){
		this.model=model;
	}
	D6_Car(String model, String color){
		this.model=model;
		this.color=color;
	}
	D6_Car(String model, String color,int maxSpeed){
		this.model=model;
		this.color=color;
		this.maxSpeed=maxSpeed;
	}
}
