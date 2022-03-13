package main;

import java.util.Scanner;

import employee.EmpDAO;
import employee.Emp_abs;
import gallery.Floor1Impl;
import gallery.Floor2Impl;
import gallery.Gallery_inter;

public class Main {

	public static void main(String[] args) {
		
		Emp_abs emp = new EmpDAO();
		Gallery_inter gallery1 = new Floor1Impl();
		Gallery_inter gallery2 = new Floor2Impl();
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
		System.out.println("수행할 작업을 선택해주세요.");
		System.out.println("1번 미술품 관리, 2번 사원 관리, 3번 종료");
		int a = sc.nextInt();
		
		while(true) {
			System.out.println();
			
		// 미술품 관리
		if(a==1) {
			System.out.println("관리할 층을 입력해주세요 (1,2)");
 				int floor = sc.nextInt();
 				
				if(floor==1) {
					System.out.println();
					gallery1.gallInfo();
					menu();
					int choice = sc.nextInt();
					
					switch(choice){
						case 1 :
							gallery1.picSearch();
							break;
						case 2 :
							gallery1.picAdd();
							break;
						case 3 :
							gallery1.picUpdate();
							break;
						case 4 :	
							gallery1.picDelete();
							break;
						case 5 :	
							System.out.println();
							System.out.println("종료되었습니다.");
							return;
						default :
							System.out.println("잘못 입력하셨습니다.");
							System.out.println();
							break;
					}
				
				}
				
				else if(floor==2) {
					System.out.println();
					
					gallery2.gallInfo();
					menu();
					int choice2 = sc.nextInt();
					
					switch(choice2){
						case 1 :
							gallery2.picSearch();
							break;
						case 2 :
							gallery2.picAdd();
							break;
						case 3 :
							gallery2.picUpdate();
							break;
						case 4 :	
							gallery2.picDelete();
							break;
						case 5 :	
							System.out.println();
							System.out.println("종료되었습니다.");
							return;
						default :
							System.out.println("잘못 입력하셨습니다.");
							System.out.println();
							break;
					}
				
			}
				else {
				System.out.println();
				System.out.println("층을 잘못 입력하셨습니다.");
			}
		}
		// 사원 관리
		else if(a==2) {
			emp_menu();
			int choice = sc.nextInt();
			
			switch(choice){
				case 1 :
					((EmpDAO)emp).dataSearch();
					break;
				case 2 :
					((EmpDAO)emp).empInsert();
					break;
				case 3 :
					((EmpDAO)emp).empUpdate();
					break;
				case 4 :	
					((EmpDAO)emp).empDelete();
					break;
				case 5 :	
					((EmpDAO)emp).mngDB();
					break;
				case 6 :	
					System.out.println("종료되었습니다.");
					return;
				default :
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
		}
		else{
			break;
		}
	}// while 종료
		if(a==3) {
			System.out.println("종료되었습니다.");
			break;
		}
		else {
			System.out.println("잘못 입력하셨습니다.");
			System.out.println();
		}
		}//while 홈 종료
}
	
	public static void menu() {
		System.out.println("실행하실 번호를 입력하세요.");
		System.out.println("┌───────────────────┐");
		System.out.println("│\t1.조회\t    │");
		System.out.println("│\t2.입력\t    │");
		System.out.println("│\t3.수정\t    │");
		System.out.println("│\t4.삭제\t    │");
		System.out.println("│\t5.종료\t    │");
		System.out.println("└───────────────────┘");
	}
	
	public static void emp_menu() {
		System.out.println("실행하실 번호를 입력하세요.");
		System.out.println("┌───────────────────┐");
		System.out.println("│\t1.조회\t    │");
		System.out.println("│\t2.입력\t    │");
		System.out.println("│\t3.수정\t    │");
		System.out.println("│\t4.삭제\t    │");
		System.out.println("│\t5.관리\t    │");
		System.out.println("│\t6.종료\t    │");
		System.out.println("└───────────────────┘");
	}
}