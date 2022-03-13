package gallery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Floor1Impl implements Gallery_inter {

	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "gallery";
	private static String pwd = "1234";

	private Connection conn;
	private ResultSet rs;
	private Statement stmt;
	
	@Override
	public void gallInfo() {
		System.out.println("1F(동양화)");
	}

	// 미술품 조회
	@Override
	public void picSearch() {
		try {
			connDB();
			
			String query = "select * from pic_tbl where pic_f='1층'";
			rs = stmt.executeQuery(query);

			if(rs.isBeforeFirst()) {System.out.println("정보 조회 완료");}
			else {System.out.println("현재 층에 해당 작품이 없습니다");}
			System.out.println();
			
			
			while(rs.next()) {
			System.out.println("작품명: "+rs.getString("pic_name"));
			System.out.println("작품 번호: "+rs.getInt("pic_num"));
			System.out.println("전시 층: "+rs.getString("pic_f"));
			System.out.println("작품 수량: "+rs.getInt("qty"));
			System.out.println();					
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 미술품 추가
	@Override
	public void picAdd() {
		try {
			connDB();	
			
			System.out.println("등록할 작품의 정보를 입력하세요.");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("작품명: ");
			String pic_name = sc.next();
			
			System.out.print("작품 번호: ");
			int pic_num = sc.nextInt();
			
			System.out.print("전시 층: ");
			String pic_f = sc.next();
			
			System.out.print("작품 수량: ");
			int qty = sc.nextInt();
			
			PreparedStatement pstmt = conn.prepareStatement("insert into pic_tbl values(?,?,?,?)");
				System.out.println("작품이 등록되었습니다.");
				pstmt.setString(1, pic_name);
				pstmt.setInt(2, pic_num);
				pstmt.setString(3, pic_f);
				pstmt.setInt(4, qty);
				pstmt.executeUpdate();
				
				conn.close();
				stmt.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 미술품 수량 변경
	@Override
	public void picUpdate() {
		String f = "1층";
		
		try {
			connDB(); // DB와 연결
			Scanner sc = new Scanner(System.in);
			System.out.print("수량을 변경할 작품 번호를 입력하세요: ");
			int num = sc.nextInt();
			
			if(1000 < num && num < 2000 ) {
			String query = "select * from pic_tbl where pic_num="+num;
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				System.out.println("작품명: "+rs.getString("pic_name"));
				System.out.println("작품 번호: "+rs.getInt("pic_num"));
				System.out.println("전시 층: "+rs.getString("pic_f"));
				System.out.println("작품 수량: "+rs.getInt("qty"));
				System.out.println();					
				}
			
			System.out.println("변경할 수량을 입력하세요: ");
			int n = sc.nextInt();
			
				String query2 = "update pic_tbl set qty"+n+" where pic_num="+num+" and pic_f='"+f+"'";
				stmt.executeUpdate(query2);
				System.out.println("수량이 변경되었습니다");
				

			rs.close(); } else {
				System.out.println("1층에 전시된 작품이 아니거나 존재하지 않는 작품입니다.");
			}
			stmt.close();
			conn.close();

		}	
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 미술품 삭제
	@Override
	public void picDelete() {
		try{
			connDB();
			
			System.out.println("삭제할 작품 번호를 입력하세요: ");
			
			Scanner sc = new Scanner(System.in);
			int delNum = sc.nextInt();
		
			String query = "DELETE FROM pic_tbl ";
			
			if(1000 < delNum && delNum < 2000 ) {
				query += "WHERE pic_num="+delNum;
				
				rs = stmt.executeQuery(query);
				stmt.executeUpdate(query);
				
				System.out.println(delNum + " 작품이 목록에서 삭제되었습니다.");
				
			} else {System.out.println("1층에 전시된 작품이 아니거나 존재하지 않는 작품입니다.");}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DB 연결
	@Override
	public void connDB() {
		try {
			Class.forName(driver);
			//System.out.println("oracle driver 로딩 성공");
			
			conn = DriverManager.getConnection(url, user, pwd);
			//System.out.println("Connection 생성 성공");

			stmt = conn.createStatement();
			//System.out.println("Statement 객체 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
