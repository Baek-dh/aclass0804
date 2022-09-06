package edu.kh.jdbc1.model.vo;

// VO(Value Object) : 값 저장용 객체 (저장된 값을 읽는 용도로 사용)
// -> 비슷한 단어로 DTO(Data Transfer Object) : 데이터를 교환하는 용도의 객체
//												(값을 읽고, 쓰고하는 용도)

//  VO, DTO 모두 값을 저장하는용도
public class Emp { // DB에서 조회된 사원 한명(1행)의 정보를 저장하는 객체

	private String empName;
	private String deptTitle;
	private int salary;
	
	public Emp() { } // 기본 생성자
	
	// 매개변수 생성자
	public Emp(String empName, String deptTitle, int salary) {
		this.empName = empName;
		this.deptTitle = deptTitle;
		this.salary = salary;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "이름 : " + empName + " / 부서명 : " + deptTitle + " / 급여 : " + salary;
	}
	
	
	
	
	
	
}





