package edu.kh.emp.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.emp.model.vo.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	// 사번을 지정하기 위한 값
	// 사원이 추가될 떄 마다 1씩 증가
	private int empIdCount = 1;
	
	// 사원 정보를 저장하기 위한 List
	private List<Employee> empList = new ArrayList<>();
	
	public EmployeeServiceImpl() {
		empList.add(new Employee(empIdCount++, "홍대표", "700509-1234567", "dp_hong@emp.com", "010-1111-1111", "대표", "대표", 10000000));
		empList.add(new Employee(empIdCount++, "김부장", "751103-2987654", "bj_kim@emp.com",  "010-3456-7890", "회계", "부장", 6000000));
		empList.add(new Employee(empIdCount++, "이과장", "800317-1122334", "kj_lee@emp.com",  "010-2684-9753", "총무", "과장", 5000000));
		empList.add(new Employee(empIdCount++, "최대리", "900931-2654321", "dr_choi@emp.com", "010-8888-6666", "회계", "대리", 4000000));
		empList.add(new Employee(empIdCount++, "박사원", "950224-1678423", "sw_park@emp.com", "010-2222-3333", "마케팅", "사원", 3000000));
	}
	
	// 새로운 사원 정보 추가 서비스 
	@Override
	public boolean addEmployee(Employee emp) {
		emp.setEmpId(empIdCount++);
		return empList.add(emp);
	}

	// 사원 리스트 반환 서비스
	@Override
	public List<Employee> selectAll() {
		return empList;
	}

	// 사번이 일치하는 차원 정보 조회 서비스
	@Override
	public Employee selectEmpNo(int empId) {
		Employee emp = null;
		for(Employee e : empList) {
			if(e.getEmpId() == empId) {
				emp = e;
				break;
			}
		}
		
		return emp;
	}

	// 사번이 일치하는 사원 정보 수정 서비스
	@Override
	public void updateEmployee(Employee updateEmp) {
		
		Employee emp = selectEmpNo(updateEmp.getEmpId());
		
		emp.setEmail(updateEmp.getEmail());
		emp.setPhone(updateEmp.getPhone());
		emp.setDepartmentTitle(updateEmp.getDepartmentTitle());
		emp.setJobName(updateEmp.getJobName());
		emp.setSalary(updateEmp.getSalary());
	}

	// 사번이 일치하는 사원 정보 삭제 서비스
	@Override
	public Employee deleteEmployee(int empId) {
		Employee deleteEmp = null;
		
		for(int i=0 ; i<empList.size() ; i++) {
			if(empList.get(i).getEmpId() == empId) {
				deleteEmp = empList.remove(i);
				break;
			}
		}
		return deleteEmp;
	}

	// 입력 받은 부서와 일치 모든 사원 정보 조회 서비스
	@Override
	public List<Employee> selectDepartment(String departmentTitle) {
		List<Employee> searchList = new ArrayList<>();
		
		for(Employee emp : empList) {
			if(emp.getDepartmentTitle().equals(departmentTitle)) {
				searchList.add(emp);
			}
		}
		return searchList;
	}
	
	// 입력 받은 부서와 일치 모든 사원 정보 조회 서비스
	@Override
	public List<Employee> selectSalary(int salary) {
		List<Employee> searchList = new ArrayList<>();
		
		for(Employee emp : empList) {
			if(emp.getSalary() >= salary) {
				searchList.add(emp);
			}
		}
		return searchList;
	}

	// 부서별 급여 합 전체 조회
	@Override
	public Map<String, Integer> departmentalSalay() {
		
		Map<String, Integer> map = new HashMap<>();
		
		for(Employee emp : empList) {
			String key = emp.getDepartmentTitle();
			int value =  map.get(key) == null ? 0 : map.get(key); // 삼항 연산자
						// map에서 일치하는 key가 없으면 0, 있으면 해당 value 반환
			map.put(key, value + emp.getSalary());
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
}
