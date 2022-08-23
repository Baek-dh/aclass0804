package edu.kh.poly.ex1.model.vo;

public class Spark extends Car{
	
	private double discountOffer; // 할인 혜택
	
	public Spark() { }

	public Spark(int wheel, int seat, String fuel, double discountOffer) {
		super(wheel, seat, fuel);
		this.discountOffer = discountOffer;
	}

	public double getDiscountOffer() {
		return discountOffer;
	}

	public void setDiscountOffer(double discountOffer) {
		this.discountOffer = discountOffer;
	}
	
	
	@Override
	public String toString() {
		return super.toString() + " / 할인혜택 : " + discountOffer;
	}
	
	
	public void dc() {
		System.out.println( discountOffer * 100 + "% 할인됩니다." );
	}
	
	
	
}
