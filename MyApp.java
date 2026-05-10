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
    static Scanner sc = new Scanner(System.in);
    
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
        System.out.println("  2. 젼체 성저 조회");
        System.out.println("  3. 학생별 성적 조회");
        System.out.println("  4. 과목별 성저 조회");
        System.out.println("  0. 종료");
        System.out.println("------------------------------");
    }
    
    // 1. 성적 입력
    static void inputGrade(){
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
}