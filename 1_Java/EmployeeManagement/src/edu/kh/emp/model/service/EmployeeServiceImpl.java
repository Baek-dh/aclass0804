package edu.kh.emp.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.emp.model.vo.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	// ����� �����ϱ� ���� ��
	// ����� �߰��� �� ���� 1�� ����
	private int empIdCount = 1;
	
	// ��� ������ �����ϱ� ���� List
	private List<Employee> empList = new ArrayList<>();
	
	public EmployeeServiceImpl() {
		empList.add(new Employee(empIdCount++, "ȫ��ǥ", "700509-1234567", "dp_hong@emp.com", "010-1111-1111", "��ǥ", "��ǥ", 10000000));
		empList.add(new Employee(empIdCount++, "�����", "751103-2987654", "bj_kim@emp.com",  "010-3456-7890", "ȸ��", "����", 6000000));
		empList.add(new Employee(empIdCount++, "�̰���", "800317-1122334", "kj_lee@emp.com",  "010-2684-9753", "�ѹ�", "����", 5000000));
		empList.add(new Employee(empIdCount++, "�ִ븮", "900931-2654321", "dr_choi@emp.com", "010-8888-6666", "ȸ��", "�븮", 4000000));
		empList.add(new Employee(empIdCount++, "�ڻ��", "950224-1678423", "sw_park@emp.com", "010-2222-3333", "������", "���", 3000000));
	}
	
	// ���ο� ��� ���� �߰� ���� 
	@Override
	public boolean addEmployee(Employee emp) {
		emp.setEmpId(empIdCount++);
		return empList.add(emp);
	}

	// ��� ����Ʈ ��ȯ ����
	@Override
	public List<Employee> selectAll() {
		return empList;
	}

	// ����� ��ġ�ϴ� ���� ���� ��ȸ ����
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

	// ����� ��ġ�ϴ� ��� ���� ���� ����
	@Override
	public void updateEmployee(Employee updateEmp) {
		
		Employee emp = selectEmpNo(updateEmp.getEmpId());
		
		emp.setEmail(updateEmp.getEmail());
		emp.setPhone(updateEmp.getPhone());
		emp.setDepartmentTitle(updateEmp.getDepartmentTitle());
		emp.setJobName(updateEmp.getJobName());
		emp.setSalary(updateEmp.getSalary());
	}

	// ����� ��ġ�ϴ� ��� ���� ���� ����
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

	// �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ ����
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
	
	// �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ ����
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

	// �μ��� �޿� �� ��ü ��ȸ
	@Override
	public Map<String, Integer> departmentalSalay() {
		
		Map<String, Integer> map = new HashMap<>();
		
		for(Employee emp : empList) {
			String key = emp.getDepartmentTitle();
			int value =  map.get(key) == null ? 0 : map.get(key); // ���� ������
						// map���� ��ġ�ϴ� key�� ������ 0, ������ �ش� value ��ȯ
			map.put(key, value + emp.getSalary());
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
}
