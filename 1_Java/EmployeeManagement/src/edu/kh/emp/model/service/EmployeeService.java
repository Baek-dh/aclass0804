package edu.kh.emp.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.emp.model.vo.Employee;

public interface EmployeeService {
	
	/** 새로운 사원 정보 추가 서비스 
	 * <p>emp에 사번을 세팅한 후 empList에 추가</p>
	 * @param emp
	 * @return empList 추가 결과
	 * 
	 */
	public abstract boolean addEmployee(Employee emp);
	
	/** 사원 리스트 반환 서비스
	 * @return empList
	 */
	public abstract List<Employee> selectAll();
	
	
	/** 사번이 일치하는 차원 정보 조회 서비스
	 * @param empId
	 * @return emp
	 */
	public abstract Employee selectEmpNo(int empId);
	
	/** 사번이 일치하는 사원 정보 수정 서비스
	 * @param emp
	 */
	public abstract void updateEmployee(Employee emp);

	/** 사번이 일치하는 사원 정보 삭제 서비스
	 * @param empId
	 * @return deleteEmp
	 */
	public abstract Employee deleteEmployee(int empId);

	
	/** 입력 받은 부서와 일치 모든 사원 정보 조회 서비스
	 * @param departmentTitle
	 * @return searchList
	 */ 
	public abstract List<Employee> selectDepartment(String departmentTitle);

	/** 입력 받은 급여 이상을 받는 모든 사원 정보 조회 서비스
	 * @param salary 
	 * @return searchList
	 */
	public abstract List<Employee> selectSalary(int salary);
	
	/** 부서별 급여 합 전체 조회 서비스
	 * @return map
	 */
	public abstract Map<String, Integer> departmentalSalay();
	
}
