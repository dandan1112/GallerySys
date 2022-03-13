package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpDAO extends Emp_abs {
	
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "gallery";
	private static String pwd = "1234";

	private Connection conn;
	private ResultSet rs;
	private Statement stmt;

	// 사원 조회
	@Override
	public void dataSearch() {
		try {
			connDB();
			
			String query = "select * from emp_tbl ";
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				System.out.println("사번: " + rs.getString("emp_id"));
				System.out.println("비밀번호: " + rs.getString("pwd"));
				System.out.println("사원명: " + rs.getString("emp_name"));
				System.out.println("직책: " + rs.getString("position"));
				System.out.println();
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DB 연결
	@Override
	public void connDB() {
		try {
			Class.forName(driver);
//			System.out.println("oracle driver 로딩 성공");
			conn = DriverManager.getConnection(url, user, pwd);
//			System.out.println("Connection 생성 성공");

			stmt = conn.createStatement();
//			System.out.println("Statement 객체 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 사원 정보 추가
	public void empInsert() {
		try {
			connDB();	
			
			System.out.println("추가할 사원 정보를 입력하세요.");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("사번: " );
			int emp_id = sc.nextInt();
			
			System.out.print("비밀번호: ");
			String pwd = sc.next();
			
			System.out.print("사원명: ");
			String emp_name = sc.next();
			
			System.out.print("직책: ");
			String position = sc.next();
			
			PreparedStatement pstmt = conn.prepareStatement("insert into emp_tbl values(?,?,?,?)");
				pstmt.setInt(1, emp_id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, emp_name);
				pstmt.setString(4, position);
				pstmt.executeUpdate();
				System.out.println("사원 정보가 입력되었습니다.");
			
				conn.close();
				stmt.close();
				

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 사원 정보 수정
	public void empUpdate() {
		try {
			connDB(); // DB와 연결
			Scanner sc = new Scanner(System.in);
			System.out.print("직책을 변경할 사원의  사번을 입력하세요: ");
			int num = sc.nextInt();
			
			String query = "select * from emp_tbl where emp_id=" + num;
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				System.out.println("사번: " + rs.getString("emp_id"));
				System.out.println("비밀번호: " + rs.getString("pwd"));
				System.out.println("사원명: " + rs.getString("emp_name"));
				System.out.println("직책: " + rs.getString("position"));
				System.out.println();
			}
			
			System.out.print("변경할 직책을 입력하세요: ");
			String pos = sc.next();
			
				String query2 = "update emp_tbl set position='"+pos +"' where emp_id="+num;
				stmt.executeUpdate(query2);
				System.out.println("직책이 변경되었습니다."); 


			rs.close();
			stmt.close();
			conn.close();

		}	
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 사원 정보 삭제
	public void empDelete() {
		try{
			connDB();
			
			System.out.println("삭제할 사원의 사번을 입력하세요.");
			Scanner sc = new Scanner(System.in);
			int delId = sc.nextInt();
		
			String query = "DELETE FROM emp_tbl ";
			
			if(delId > 0) {
				query += "WHERE emp_id="+delId;
				
				rs = stmt.executeQuery(query);
				stmt.executeUpdate(query);
				System.out.println("삭제되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 관리자 권한 부여
	public void mngDB() {
		try {
			connDB();
			
			System.out.println("관리권한을 부여할 사원의 사번을 입력하세요.");
			Scanner sc = new Scanner(System.in);
			int mngId = sc.nextInt();
			
			System.out.println("사원이 관리할 작품 번호를 입력하세요.");
			int mngNum = sc.nextInt();
					
			PreparedStatement pstmt=conn.prepareStatement("insert into manage values(SEQ_MNG.NEXTVAL,?,?)");
			pstmt.setInt(1, mngId);
			pstmt.setInt(2, mngNum);
			pstmt.executeUpdate();

			System.out.println("데이터가 추가되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}