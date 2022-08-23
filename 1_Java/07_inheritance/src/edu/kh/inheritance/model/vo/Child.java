package edu.kh.inheritance.model.vo;


public class Child extends Parent{
// The type Child cannot subclass the final class Parent
	
	@Override
	public void method1() {
	// Cannot override the final method from Parent
		System.out.println("자식의 메서드");
	}
}
