package edu.kh.oop.method.run;

import edu.kh.oop.method.view.UserView;

public class UserRun {
	public static void main(String[] args) {
		UserView view = new UserView(); // UserView 객체 생성
		
		view.displayMenu();
	}
}
