package edu.kh.emp.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.service.EmployeeServiceImpl;
import edu.kh.emp.model.vo.Employee;

public class EmployeeViewImpl implements EmployeeView{
	
	private Scanner sc = new Scanner(System.in);
	
	private EmployeeService service = new EmployeeServiceImpl();
	
	@Override
	public void displayMenu() {
		
		int input = 0;
		
		do {
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- ��� ���� ���α׷� -----");
				System.out.println("1. ���ο� ��� ���� �߰�");
				System.out.println("2. ��ü ��� ���� ��ȸ");
				System.out.println("3. ����� ��ġ�ϴ� ��� ���� ��ȸ");
				System.out.println("4. ����� ��ġ�ϴ� ��� ���� ����");
				System.out.println("5. ����� ��ġ�ϴ� ��� ���� ����");
				System.out.println("6. �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ");
				System.out.println("7. �Է� ���� �޿� �̻��� �޴� ��� ��� ���� ��ȸ");
				System.out.println("8. �μ��� �޿� �� ��ü ��ȸ");
				System.out.println("0. ���α׷� ����");
				
				System.out.print("�޴� ���� >> ");
				input = sc.nextInt();
				
				System.out.println();
	
				switch (input) {
				case 1: addEmployee(); break;
				case 2: selectAll(); break;
				case 3: selectEmpId(); break;
				case 4: updateEmployee(); break;
				case 5: deleteEmployee(); break;
				case 6: selectDepartment(); break;
				case 7: selectSalary(); break;
				case 8: departmentalSalay(); break;
				case 0: System.out.println("<���α׷� ����>"); break;
	
				default: System.out.println("�޴��� ǥ�ÿ� ��ȣ�� �Է����ּ���.");
				}
				
				System.out.println();
				
				
			} catch (InputMismatchException e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�. ���ڸ� �Է����ּ���.");
				input = -1;
				sc.nextLine();
			}
			
		}while(input != 0);
	}

	@Override
	public void addEmployee() {
		System.out.println("<���ο� ��� ���� �߰�>");
		
		System.out.print("��� �̸� : ");
		String empName = sc.next();
		
		System.out.print("�ֹ� ��� ��ȣ : ");
		String empNo = sc.next();
		
		System.out.print("�̸��� : ");
		String email = sc.next();
		
		System.out.print("��ȭ��ȣ : ");
		String phone = sc.next();
		
		System.out.print("�μ��� : ");
		String departmentTitle = sc.next();
		
		System.out.print("���޸� : ");
		String jobName = sc.next();
		
		System.out.print("�޿� : ");
		int salary = sc.nextInt();
		
		Employee emp = new Employee(empName, empNo, email, phone, departmentTitle, jobName, salary);
		
		if(service.addEmployee(emp)) {
			System.out.println("��� ������ �߰��Ǿ����ϴ�.");
		}else {
			System.out.println("��� ���� �߰� ����");
		}
		
	}

	// ��ü ��� ��ȸ
	@Override
	public void selectAll() {
		System.out.println("<��ü ��� ���� ��ȸ>");
		printAll(service.selectAll());
		
	}

	// ����� ��ġ�ϴ� ��� ���� ��ȸ
	@Override
	public void selectEmpId() {
		System.out.println("<����� ��ġ�ϴ� ���� ���� ��ȸ>");
		int empId = inputEmpId();
		
		printOne(service.selectEmpNo(empId));
	}
	
	// ����� ��ġ�ϴ� ��� ���� ����
	@Override
	public void updateEmployee() {
		System.out.println("<����� ��ġ�ϴ� ��� ���� ����>");
		
		int empId = inputEmpId();
		
		if( service.selectEmpNo(empId) == null ) {
			System.out.println("����� ��ġ�ϴ� ����� �������� �ʽ��ϴ�.");
			
		} else {
			System.out.print("�̸��� : ");
			String email = sc.next();
			
			System.out.print("��ȭ��ȣ : ");
			String phone = sc.next();
			
			System.out.print("�μ��� : ");
			String departmentTitle = sc.next();
			
			System.out.print("���޸� : ");
			String jobName = sc.next();
			
			System.out.print("�޿� : ");
			int salary = sc.nextInt();
			
			Employee updateEmp = new Employee();
			updateEmp.setEmpId(empId);
			updateEmp.setEmail(email);
			updateEmp.setPhone(phone);
			updateEmp.setDepartmentTitle(departmentTitle);
			updateEmp.setJobName(jobName);
			updateEmp.setSalary(salary);
			
			service.updateEmployee(updateEmp);
		}
	}
	
	
	// ����� ��ġ�ϴ� ��� ���� ����
	@Override
	public void deleteEmployee() {
		System.out.println("<����� ��ġ�ϴ� ��� ���� ����>");
		
		int empId = inputEmpId();
		
		if( service.selectEmpNo(empId) == null) {
			System.out.println("����� ��ġ�ϴ� ����� �������� �ʽ��ϴ�.");
		} else {
			
			System.out.print("������ �����Ͻðڽ��ϱ�? (Y/N) : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				Employee deleteEmp = service.deleteEmployee(empId);
				System.out.println(deleteEmp.getEmpName() + "�� ������ �����Ǿ����ϴ�.");
			} else {
				System.out.println("���� ���");
			}
			
		}
	}
	
	// �Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ
	@Override
	public void selectDepartment() {
		System.out.println("<�Է� ���� �μ��� ��ġ ��� ��� ���� ��ȸ>");
		System.out.print("�μ��� �Է� : ");
		String departmentTitle = sc.next();
		
		printAll( service.selectDepartment(departmentTitle) );
	}
	
	
	// �Է� ���� �޿� �̻��� �޴� ��� ��� ���� ��ȸ
	@Override
	public void selectSalary() {
		System.out.println("<�Է� ���� �޿� �̻��� �޴� ��� ��� ���� ��ȸ>");
		System.out.print("�޿� �Է� : ");
		int salary = sc.nextInt();
		
		printAll( service.selectSalary(salary));
	}
	
	
	
	// �μ��� �޿� �� ��ü ��ȸ
	@Override
	public void departmentalSalay() {
		System.out.println("<�μ��� �޿� �� ��ü ��ȸ>");
		Map<String, Integer> map = service.departmentalSalay();
		
		System.out.println("  �μ�  |  �޿� �հ�   ");
		System.out.println("-----------------------");
		for(String key : map.keySet()) {
			System.out.printf(" %4s | %d\n", key, map.get(key));
		}
		
	}
	
	
	//-----------------------------------------------------------------------------------------
	// ���� �޼���
	
	
	// ��� ���� ��� ���
	@Override
	public void printAll(List<Employee> empList) {
		if(empList.isEmpty()) {
			System.out.println("��ȸ�� ��� ������ �����ϴ�.");
			
		} else {
			System.out.println("��� |   �̸�  | �ֹ� ��� ��ȣ |        �̸���        |   ��ȭ ��ȣ   | �μ� | ��å | �޿�" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
		}
		
	}

	
	// ��� ���� 1�� ���
	@Override
	public void printOne(Employee emp) {
		if(emp == null) {
			System.out.println("����� ��ġ�ϴ� ����� �������� �ʽ��ϴ�.");
			
		} else {
			System.out.println("��� |   �̸�  | �ֹ� ��� ��ȣ |        �̸���        |   ��ȭ ��ȣ   | �μ� | ��å | �޿�" );
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
					emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
		}
	}

	
	// ����� �Է� �޾� �����ϴ� �޼���
	@Override
	public int inputEmpId() {
		System.out.print("��� �Է� : ");
		int empNo = sc.nextInt();
		sc.nextLine();
		return empNo;
	}



	
	
	
	
	
	
}
