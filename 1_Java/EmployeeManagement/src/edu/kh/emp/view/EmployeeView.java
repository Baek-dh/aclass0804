package edu.kh.emp.view;

import java.util.List;

import edu.kh.emp.model.vo.Employee;

public interface EmployeeView {

	
	/** 메뉴 출력 메서드
	 */
	public abstract void displayMenu();
	
	/** 새로운 사원 정보 추가
	 * <ul>
	 * <li>사번은 입력 받지 않음.</li>
	 * <li>EmployeeServiceImpl에 작성되어있는 empIdCount를 사번으로 사용</li>
	 * <li>입력 받은 값을 이용해서 Employee 객체를 생성하여 service로 전달</li>
	 * <ul>
	 */
	public abstract void addEmployee();
	
	/** 전체 사원 조회
	 * service에서 얻어온 empList를 printAll() 메서드를 이용해서 출력
	 */
	public abstract void selectAll();
	
	/** 사번이 일치하는 사원 정보 조회
	 * <p>사번을 입력 받아 service에 전달 후 반환 받은 값을 printOne 메서드를 이용해서 출력</p>
	 */
	public abstract void selectEmpId();
	
	
	/**
	 * 사번이 일치하는 사원 정보 수정
	 */
	public abstract void updateEmployee();
	
	/**
	 * 사번이 일치하는 사원 정보 삭제
	 * 
	 */
	public abstract void deleteEmployee();
	
	/**
	 * 입력 받은 부서와 일치 모든 사원 정보 조회
	 */
	public abstract void selectDepartment();
	
	/**
	 * 입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 */
	public abstract void selectSalary();
	
	
	/** 
	 * 부서별 급여 합 전체 조회
	 */
	public abstract void departmentalSalay();
	
	
	//-------------------------------------------------------------
	// 공용 메서드
	
	/** 사원 정보 모두 출력
	 * 전달 받은 empList의 있는 내용을 향상된 for문을 이용해 모두 출력
	 * @param empList
	 */
	public abstract void printAll(List<Employee> empList);
	
	/** 사원 정보 1명 출력
	 * 전달 받은 emp 객체의 정보를 출력
	 * @Param emp
	 */
	public abstract void printOne(Employee emp);
	
	
	/** 사번을 입력 받아 반한하는 메서드
	 * @return empNo
	 */
	public abstract int inputEmpId();
	
	
}
