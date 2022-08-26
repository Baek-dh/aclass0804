package edu.kh.io.model.run;

import edu.kh.io.model.service.IOService;

public class Run {
	public static void main(String[] args) {
		
		IOService service = new IOService();
		
//		service.output();
//		service.input();
		service.fileCopy();
		
	}
}
