package ex01_internet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainClass {

	/*
		URL
		1. Uniform Resource Locator		// 모양이 하나다
		2. 정형화된 자원의  경로
		3. 실제로는 웹 주소를 의미한다.		// https s = 시큐어 (보안)
		4. 구성										주소에 ?는 한개 밖에 나올 수 없다 . 마지막 인코딩기호는 삼성전자
			프로토콜://	    호스트		/  서버경로  ?파라미터=값&파라미터=값&...
				https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90
		  	1) 프로토콜 : 통신규약, https(secure + http : 보안이 처리된 하이퍼텍스트 전송 프로토콜)
		  	2) 호스트 : 서버의 대표 주소
		  	3) 서버경로 : URL Mapping(URL Pattern) = //사용자가 만드는 경로//
		  	4) 파라미터 : 서버로 보내는 데이터를 의미(변수라고 생각하면 된다.)
	 */
	
	public static void ex01() {
		
		String apiURL = "https://search.naver.com/search.naver?query=삼성전자";		
		URL url = null;			// 예외 처리때문에 만들지 않음..  예외처리는 트라이 안에 넣어야한다
		
		try {
			
			url = new URL(apiURL);	// 반드시 예외 처리가 필요한 코드
			
			
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost());
			System.out.println("파라미터 : " + url.getQuery());	// 파라미터 = 자바에서는 Query라고 부름
			
		} catch(MalformedURLException e) {		// 잘못된 모양의 이셉션 예외처리
			System.out.println("apiURL 주소 오류");
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	public static void ex02() {
		
		// 웹 접속을 담당하는 HttpURLConnection
		
		String apiURL = "https://www.naver.com";
		URL url = null;
		HttpURLConnection con = null;
		
		try {
			
			url = new URL(apiURL);	// MalformedURLException 처리가 필요하다.
			con = (HttpURLConnection)url.openConnection();	// IOException 처리가 필요하다.
			
			/*
				HTTP 응답 코드 공부하기
				1. 200 : 정상
				2. 40X : 잘못된 요청(사용자가 잘못하였음)
				3. 50X : 서버측 오류(서버가 잘못하였음)
			 */
			System.out.println("정상 응답 : " + HttpURLConnection.HTTP_OK);
			System.out.println("Not Found : " + HttpURLConnection.HTTP_NOT_FOUND);
			System.out.println("Internal Error : " + HttpURLConnection.HTTP_INTERNAL_ERROR);
			
			// apiURL 접속 확인
			int responseCode = con.getResponseCode();						// 응답코드가 숫자이기 때문에 int
			if(responseCode == 200) {
				System.out.println(apiURL + " 접속 완료 ");
			}
			
			// 요청 방식(요청 메소드)
			String requestMethod = con.getRequestMethod();
			System.out.println("요청 방식 : " + requestMethod);
			
			// 컨텐트 타입
			String contentType = con.getContentType();
			System.out.println("컨텐트 타입 : " + contentType);
			
			// 요청 헤더
			String userAgent = con.getRequestProperty("User-Agent");
			System.out.println("User-Agent : " + userAgent);
			
			String referer = con.getRequestProperty("Referer");
			System.out.println("Referer : " + referer);  // 이전 주소가 나옴
			
			// 접속 종료
			con.disconnect(); 	// 생략해도됨
			
		} catch(MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		} catch(IOException e) {
			System.out.println("apiURL 접속 오류");
		}
		
	}
	
	public static void ex03() {
		
		String apiURL = "https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png";
		URL url = null;
		HttpURLConnection con = null;
		
		// InputStream in = null;			// Daum 로그를 읽어 들이는 입력 스트림
		// FileOutputStream out = null;	// C:\storage\daum.png로 내보내는 출력 스트림
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		File file = new File("C:" + File.separator + "storage", "daum.png");

		try {
			
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();		// 자바와 다음사이트를 연결하는메소드
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {			// 접속되었다면 작업을 수행하겠다.
				
				in = new BufferedInputStream(con.getInputStream());
				out = new BufferedOutputStream(new FileOutputStream(file));
				
				//	in = con.getInputStream();
				//	out = new FileOutputStream("C:" + File.separator + "storage" + File.separator + "daum.png");	// 파일값 스트림으로 전달할 수 있음
				
				byte[] b = new byte[10];
				int readByte = 0;
				
				while((readByte = in.read(b)) != -1) {
					out.write(b, 0, readByte);
				}
				
				System.out.println("다운로드 완료");
				
				out.close();
				in.close();
				con.disconnect();
				
			}
			
		} catch(MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		} catch(IOException e) {
			// 접속 실패 또는 스트림 관련 오류
			e.printStackTrace();
		}
		
	}
	
	public static void ex04() {
		
		String apiURL = "https://gdlms.cafe24.com/uflist/curri/10004/bbs/68_63d8848f7d506.txt";
		URL url = null;
		HttpURLConnection con = null;
		
	//	InputStreamReader reader = null;
	//	FileWriter writer = null;
	//	File file = new File("C:" + File.separator + "storage","다운로드문서.txt");
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		File file = null;
		
		try {
			
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			
			String message = null;
			int responsCode = con.getResponseCode();
			if(responsCode == HttpURLConnection.HTTP_OK) {
			//	reader = new InputStreamReader(con.getInputStream());		// 바이트 기반 스트림을 문자스트림으로 바꿔서 사용해야한다.
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				file = new File("C:" + File.separator + "storage", "다운로드문서.txt");
				message = "다운로드 성공";
			} else {
			//	reader = new InputStreamReader(con.getErrorStream());
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				file = new File("C:" + File.separator + "storage", "다운로드실패.html");
				message = "다운로드 실패";
			}
			
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[2];
			int readCount = 0; 		// 2개씩 읽어오다가 마지막에 1개를 받아야 할 수도있으니 int readcount
			
			while((readCount = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCount);
			}
			
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(sb.toString());
			
			writer.close();
			reader.close();
			con.disconnect();
			
			System.out.println("다운로드 완료");
			
		} catch(MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void ex05() {
		
		/*
			인코딩(암호화) : 원본 데이터를 UTF-8 방식으로 암호화
			디코딩(복호화) : UTF-8 방식으로 암호화된 데이터를 복원
		 */
		
		String data = "한글 english 12345 !@#$+-";
		
		try {
			
			// 인코딩
			String encodeData = URLEncoder.encode(data, "UTF-8");
			System.out.println(encodeData);
			
			// 디코딩
			String decodeData = URLDecoder.decode(encodeData, "UTF-8");
			System.out.println(decodeData);
			
		} catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}
		
	}
	
	public static void main(String[] args) {
		ex05();

	}

}

