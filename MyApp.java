import java.util.Scanner;
/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (호준수, 편규빈, 윤세연, 복창희)
 * @version (2026.05.10)
 */
public class MyApp
{
    static final int MAX = 200;
    static Scanner scan = new Scanner(System.in);
    
    // 과목&학점
    static String[] subjects = {"소프트웨어공학개론", "데이터사이언스개론", "컴퓨터데이터구조",
                                "프로그래밍응용", "테크니컬라이팅", "프로그래밍기초"};
    static int[] credits = { 3, 3, 3, 3, 3, 3};
    
    // 학생 객체
    static Student[] student = new Student[MAX];
    static int count = 0;
    
    public static void main(String[] args){
        System.out.println("=================================");
        System.out.println("    선문대학교 성적처리 시스템");
        System.out.println("=================================");
        
        int menu;
        do{
            printMenu();
            menu = inputInt("메뉴 선택: ", 0, 4);
            
            switch (menu){
                case 1:
                    inputGrade();
                    break;
                case 2:
                    viewAll();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    searchSubject();
                    break;
                case 0:
                    System.out.println("\n시스템을 종료합니다.");
                    break; 
            }
        }
        while (menu != 0);
        System.exit(1);
    }
    
    static void printMenu(){
        System.out.println("\n----------------------------");
        System.out.println("  1. 성적 입력");
        System.out.println("  2. 전체 성적 조회");
        System.out.println("  3. 학생별 성적 조회");
        System.out.println("  4. 과목별 성적 조회");
        System.out.println("  0. 종료");
        System.out.println("------------------------------");
    }
    
    // 1. 성적 입력
    static void inputGrade(){
        if (count >= MAX) {
            System.out.println("최대 인원을 초과했습니다.");
            return;
        }
        
        System.out.println("\n[ 성적 입력 ]");
        System.out.print("이름  : "); String name = scan.nextLine();
        System.out.print("학번  : "); String id   = scan.nextLine();
        
    }
    
    // 2. 전체 성적 조회 
    static void viewAll(){
    }
    
    // 3. 학생별 성적 조회
    static void searchStudent(){
        
    }
    
    // 4. 과목별 성적 조회
    static void searchSubject(){
        
    }
    
    // 정수 입력 (예외처리)
    static int inputInt(String prompt, int min, int max){
        while (true){                   //while 반복문
            try {
                if (!prompt.isEmpty()){
                    System.out.print(prompt);
                }
                int val = Integer.parseInt(scan.nextLine());
                if (val >= min && val <= max){ 
                    return val;
                }
                System.out.println(" " + min + " ~ " + max + " 사이의 값을 입력해주세요.");
            }
            catch (NumberFormatException e){        // 예외처리
                System.out.println(" 숫자만 입력 가능합니다.");
            }
        }
    }
    // 프로그램 종료기능 (q 입력시 종료)
    static int inputIntCancel(String prompt, int min, int max){
        while (true){                   //while 반복문
            try {
                if (!prompt.isEmpty()){
                    System.out.print(prompt);
                }
                String line = scan.nextLine();
                if (line.equals("q")){  // if 조건문 - q 입력 시 취소
                    return CANCEL;
                }
                int val = Integer.parseInt(line);
                if (val >= min && val <= max){
                    return val;
                }
                System.out.println(" " + min + " ~ " + max + " 사이의 값을 입력해주세요. (취소: q)");
            }
            catch (NumberFormatException e){        // 예외처리
                System.out.println(" 숫자 또는 q를 입력하세요.");
            }
        }
    }
}