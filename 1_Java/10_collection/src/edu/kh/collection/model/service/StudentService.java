package edu.kh.collection.model.service;

import java.util.ArrayList;
import java.util.List;

import edu.kh.collection.model.vo.Student;

public class StudentService {

	// 부모 타입 참조 변수 List
	private List<Student> stdList = new ArrayList<Student>();
							// Student 타입으로 제한된 ArrayList객체 생성

	public StudentService() {
		stdList.add( new Student("홍길동", 18, "서울시 강남구", 'M', 80) );
		stdList.add( new Student("김길순", 17, "서울시 중구", 'F', 90) );
		stdList.add( new Student("가나다", 19, "경기도 부천시", 'F', 100) );
	}
	
	
	
	/** stdList에 학생 정보 추가 메서드
	 * @param name
	 * @param age
	 * @param address
	 * @param gender
	 * @param score
	 * @return 
	 * true : 학생 정보 추가 성공 시
	 * false : 학생 정보 추가 실패 시
	 */
	public boolean addStudent(String name, int age, String address, char gender, int score) {
		
		// 제네릭 확인 테스트
//		List test1 = new ArrayList();
//		List<String> test2 = new ArrayList<String>();
//		List<Integer> test3 = new ArrayList<Integer>();
		
		// List.add(E e)  ->  제네릭 <E>에 작성되는 타입으로 모든 E가 변화함
		
		// test1.add(Object e) -> 타입 제한 X, 모든 참조 변수가 Object 타입
		// test2.add(String e) -> 타입 제한 O, 모든 참조 변수가 String 타입
		// test3.add(Integer e) -> 타입 제한 O, 모든 참조 변수가 Integer 타입
		
		
		Student std = new Student(name, age, address, gender, score);
		
		// boolean add(Student e) : add() 결과로 boolean 반환
		return stdList.add(std);
	}
	
	
	/** 학생 전체 조회 시 stdList 반환 메서드
	 * @return stdList
	 */
	public List<Student> getStdList() {
		return stdList;
	}



	/** 학생 정보 수정 메서드
	 * @param idx
	 * @param address
	 * @param score
	 * @return 
	 * true : 인덱스가 일치하는 학생 정보 수정 성공 시
	 * false : 인덱스가 일치하는 학생이 없을 경우
	 */
	public boolean updateStudent(int idx, String address, int score) {
		
		// 입력 받은 idx가 stdList에 실제 존재하는 요소의 인덱스 범위를 넘어선 경우 
		if( idx >= stdList.size() || idx < 0 ) {
			return false;
	
		} else {
			stdList.get(idx).setAddress(address);
			
			stdList.get(idx).setScore(score);
			
			return true;
		}
		
	}



	/** 학생 정보 제거 메서드
	 * @param idx
	 */
	public Student removeStudent(int idx) {
		
		// 일치하는 인덱스가 있으면
		// 해당 학생 정보를 stdList에서 제거하고
		// 제거된 학생 정보를 반환
		
		// 일치하는 인덱스가 없다면 null 반환
		
		if( idx < 0 || idx >= stdList.size() ) {
			return null;
			
		}else {
			
			// 리스트에서 제거된 요소를 반환
			return stdList.remove(idx);
		}
		
		
		
	}



	/** 일치하는 이름 검색
	 * @param name
	 * @return resultList
	 */
	public List<Student> selectStudent1(String name) {
		List<Student> resultList = new ArrayList<Student>();
		
		// 향상된 for문 
		for( Student s : stdList ) {
			
			// 검색한 이름과 학생 이름이 같은 경우
			if( s.getName().equals(name)  ) { 
				resultList.add(s);
			}
		}
		return resultList;
	}
	
	
	/** 포함하는 이름 검색
	 * @param name
	 * @return resultList
	 */
	public List<Student> selectStudent2(String name) {
		List<Student> resultList = new ArrayList<Student>();
		
		// 향상된 for문 
		for( Student s : stdList ) {
			
			// 검색한 이름이 학생 이름에 포함되어 있는 경우
			if( s.getName().contains(name)  ) { 
				// String.contains(값) 
				// - 문자열에 값이 포함되어있으면 true
				
				resultList.add(s);
			}
		}
		return resultList;
	}
	
	
	
	
	
	
	
	
	
	
}
