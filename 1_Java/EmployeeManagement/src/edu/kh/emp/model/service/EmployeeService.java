package edu.kh.emp.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.emp.model.vo.Employee;

public interface EmployeeService {
	
	/** ���ο� ��� ���� �߰� ���� 
	 * <p>emp�� ����� ������ �� empList�� �߰�</p>
	 * @param emp
	 * @return empList �߰� ���
	 * 
	 */
	public abstract boolean addEmployee(Employee emp);
	
	/** ��� ����Ʈ ��ȯ ����
	 * @return empList
	 */
	public abstract List<Employee> selectAll();
	
	
	/** ����� ��ġ�ϴ� ���� ���� ��ȸ ����
	 * @param empId
	 * @return emp
	 */
	public abstract Employee selectEmpNo(int empId);
	
	/** ����� ��ġ�ϴ� ��� ���� ���� ����
	 * @param emp
	 */
	public abstract void updateEmployee(Employee emp);

	/** ����� ��ġ�ϴ� ��� ���� ���� ����
	 * @param empId
	 * @return deleteEmp
	 */
	public abstract Employee deleteEmployee(int empId);

	
	/** �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ ����
	 * @param departmentTitle
	 * @return searchList
	 */ 
	public abstract List<Employee> selectDepartment(String departmentTitle);

	/** �Է� ���� �޿� �̻��� �޴� ��� ��� ���� ��ȸ ����
	 * @param salary 
	 * @return searchList
	 */
	public abstract List<Employee> selectSalary(int salary);
	
	/** �μ��� �޿� �� ��ü ��ȸ ����
	 * @return map
	 */
	public abstract Map<String, Integer> departmentalSalay();
	
}
