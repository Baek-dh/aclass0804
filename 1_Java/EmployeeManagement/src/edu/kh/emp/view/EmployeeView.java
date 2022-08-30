package edu.kh.emp.view;

import java.util.List;

import edu.kh.emp.model.vo.Employee;

public interface EmployeeView {

	
	/** �޴� ��� �޼���
	 */
	public abstract void displayMenu();
	
	/** ���ο� ��� ���� �߰�
	 * <ul>
	 * <li>����� �Է� ���� ����.</li>
	 * <li>EmployeeServiceImpl�� �ۼ��Ǿ��ִ� empIdCount�� ������� ���</li>
	 * <li>�Է� ���� ���� �̿��ؼ� Employee ��ü�� �����Ͽ� service�� ����</li>
	 * <ul>
	 */
	public abstract void addEmployee();
	
	/** ��ü ��� ��ȸ
	 * service���� ���� empList�� printAll() �޼��带 �̿��ؼ� ���
	 */
	public abstract void selectAll();
	
	/** ����� ��ġ�ϴ� ��� ���� ��ȸ
	 * <p>����� �Է� �޾� service�� ���� �� ��ȯ ���� ���� printOne �޼��带 �̿��ؼ� ���</p>
	 */
	public abstract void selectEmpId();
	
	
	/**
	 * ����� ��ġ�ϴ� ��� ���� ����
	 */
	public abstract void updateEmployee();
	
	/**
	 * ����� ��ġ�ϴ� ��� ���� ����
	 * 
	 */
	public abstract void deleteEmployee();
	
	/**
	 * �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ
	 */
	public abstract void selectDepartment();
	
	/**
	 * �Է� ���� �޿� �̻��� �޴� ��� ��� ���� ��ȸ
	 */
	public abstract void selectSalary();
	
	
	/** 
	 * �μ��� �޿� �� ��ü ��ȸ
	 */
	public abstract void departmentalSalay();
	
	
	//-------------------------------------------------------------
	// ���� �޼���
	
	/** ��� ���� ��� ���
	 * ���� ���� empList�� �ִ� ������ ���� for���� �̿��� ��� ���
	 * @param empList
	 */
	public abstract void printAll(List<Employee> empList);
	
	/** ��� ���� 1�� ���
	 * ���� ���� emp ��ü�� ������ ���
	 * @Param emp
	 */
	public abstract void printOne(Employee emp);
	
	
	/** ����� �Է� �޾� �����ϴ� �޼���
	 * @return empNo
	 */
	public abstract int inputEmpId();
	
	
}
