package exam;

public class MainCLassStudy {

	// 문제1. int serial의 첫 번째 글자가 1,3,5이면 "남자", 2,4,6이면 "여자"를 출력하시오.
		// int serial의 첫 번째 글자는 항상 1 ~ 6 사이의 정수라고 가정한다.
		// 예시
		// 남자입니다.
	public static void q1() {
		int number = 1234567;
		if(number / 1000000 % 2 == 1) {
			System.out.println("남자입니다.");
		}else 
			System.out.println("여자입니다.");
	}
	
	// 문제2. int a와 int b에 저장된 결과를 이용해서 사칙연산 결과를 출력하시오.
		// 예시
		// 7 + 2 = 9
		// 7 - 2 = 5
		// 7 * 2 = 14
		// 7 / 2 = 3.5
	public static void q2() {
		int a = 7;
		int b = 2;
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println((double)a / b);
	} 
		
	// 문제3. 구구단을 모두 출력하지 말고 5 x 5 = 25까지만 출력하시오.
		// 예시
		// 2 x 1 = 2
		// 2 x 2 = 4
		// ...
		// 5 x 5 = 25
		public static void q3() {
			for(int dan = 2; dan <= 5; dan++) {
			if(dan == 5) {
				for(int n = 1; n <= 5; n++) {
					System.out.println(dan + "x" + n + "=" + (dan * n));
				} 
			}else {
					for(int n = 1; n <= 9; n++) {
						System.out.println(dan + "x" + n + "=" + (dan * n));
					}
				}
				} 
			}
		// 문제4. begin부터 end 사이의 모든 정수들의 평균을 출력하시오.
		// 단, 항상 begin은 end보다 작거나 같은 상태이다.
		// 예시
		// 1부터 6사이 모든 정수의 평균은 3.5입니다.
		public static void q4() {
			int begin = 1;   
			int end = 6;
			int total = 0;  
			
			for(int n = begin; n <= end; n++) {
				total += n;
			}
			System.out.println(begin + "부터" + end + "사이 모든 정수의 평균은" + total / (end - begin + 1.0));
			
		}
		// 문제5. 1부터 100 사이의 모든 짝수와 홀수를 각각 더한 결과를 모두 출력하시오.
		// 예시
		// 짝수 합은 2550입니다.
		// 홀수 합은 2500입니다.
		public static void q5() {
			int evenTotal = 0;  // 짝수 합
			int oddTotal = 0;   // 홀수 합
			
			for(int n = 1; n <= 100; n++) {
				if(n % 2 == 0) {
					evenTotal += n;
				} else {oddTotal += n;}
			}
			System.out.println("짝수합" + evenTotal);
			System.out.println("홀수합" + oddTotal);
			}
		
		// 문제6. 배열에 저장된 모든 정수를 모두 더하시오.
		// 단, 0보다 작은 값은 더하지 마시오.
		// 예시
		// 합계는 6입니다.
		public static void q6() {
			int total = 0;
			int[] arr = {1, -1, -2, 2, 3, -3};
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] >= 0) {
					total += arr[i];
				}
			}
			System.out.println(total);
			}
		
		// 문제7. 제시된 배열에 변수 ch에 저장된 문자가 몇 개 포함되어 있는지 갯수를 구해서 출력하시오.
		// 예시
		// 배열에 포함된 h는 2개입니다.
		public static void q7() {
	        char ch = 'h';
	        char[] characters = {'a', 'h', 'e', 'h', 'p'};
	        int count = 0;
	        
	        for(int i = 0; i < 5; i ++) {
	        	if(characters[i] == ch) {
	        		count ++;
	        	}
	        }
	        System.out.println(count);
		}
			
		// 문제8. 점수에 따라 가져갈 수 있는 모든 사은품을 출력하시오.
		// 점수가 60점 이상인 경우 : "행주"
		// 점수가 70점 이상인 경우 : "행주", "도마"
		// 점수가 80점 이상인 경우 : "행주", "도마", "식칼"
		// 점수가 90점 이상인 경우 : "행주", "도마", "식칼", "냄비"
		// 예시
		// 행주도마
		public static void q8() {
			String strScore = "75";
			int score = Integer.parseInt(strScore) ;
			
			String str = "";
			
			if(score >= 60) {
				str += "행주";
			}
			if(score >= 70) {
				str += "도마";
			}
			if(score >= 80) {
				str += "식칼";
			}
			if(score >= 90) {
				str += "냄비";
			}
			System.out.println(str);
			
		}
		
		// 문제9. 1 ~ 100 사이의 정수를 대상으로 369 게임 결과를 아래와 같이 출력하시오.
		// 예시
		// 1   2   짝   4   5   짝   7   8   짝   10
		// 11  12  짝   14  15  짝   17  18  짝   20
		// 21  22  짝   24  25  짝   27  28  짝   짝
		// 짝  짝  짝짝 짝  짝  짝짝 짝  짝  짝짝 40
		// ...
		// 81  82  짝   84  85  짝   87  88  짝   짝
		// 짝  짝  짝짝 짝  짝  짝짝 짝  짝  짝짝 100
		public static void q9() {
			boolean condition1 = false;  // 일의 자리가 3의 배수인가?
			boolean condition2 = false;  // 십의 자리가 3의 배수인가?
			
			
			
	
	
	public static void main(String[] args) {
		q8();

	}
}

