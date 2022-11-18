package edu.kh.project.board.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardImage {
	private int imageNo;
	private String imagePath;
	private String imageReName;
	private String imageOriginal;
	private int imageOrder;
	private int boardNo;
}



