import java.util.Scanner;
/**
 * MyApp 클래스의 설명을 작성하세요.
 *
 * @author (호준수, 편규빈, 윤세연, 복창희)
 * @version (2026.05.10)
 */
public class MyApp
{
    static final int MAX = 200;// 총 인원수
    static final int CANCEL = -20260515;//취소
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
        
        System.out.println("\n[ 성적 입력 ] (취소: q)");
        
        // 이름 입력 -> q 이면 취소 
        System.out.print("이름  : ");
        String name = scan.nextLine();
        if (name.equals("q")){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }
        
        // 학번 입력 -> q 이면 취소
        System.out.print("학번  : "); 
        String id  = scan.nextLine();
        if (id.equals("q")){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }
        
        // 학년 입력 -> CANCEL 이면 취소 
        int year = inputIntCancel("학년 (1~4): ", 1, 2);
        if (year == CANCEL){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }
        
        // 학기 입력 -> CANCEL 이면 취소 
        int sem = inputIntCancel("학기 (1~2): ", 1, 2);
        if (sem == CANCEL){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }
        
        Student stu = new Student(name, id, year, sem); //객체 생성 
        
        System.out.println("\n 과목별 점수 입력 (0~100, 건너뛰기: -1, 취소: q)");
        boolean cancelled = false;
        for (int i = 0; i < subjects.length; i++){
             System.out.print(subjects[i] + "\t: ");
             int score = inputIntCancel("", -1, 100); 
             if (score == CANCEL){
                 cancelled = true;
                break;
            }
            if (score >= 0){
                stu.setScores(i, score);
            }
        }
        
        if (cancelled){
            System.out.println("성적 입력을 취소합니다.");
        } else {
            student[count++] = stu;
            System.out.println("\n등록 완료: " + name + " (" + id + ")");
        }
    }
    
    // 2. 전체 성적 조회 
    static void viewAll(){
        if (count == 0){
            System.out.println("등록된 학생이 없습니다. ");
            return;
        }
    
        System.out.println("\n[ 전체 성적 조회 ]  (중단: q)");
    
        for (int i = 0; i < count; i++){
           Student stu = student[i]; 
             System.out.println("이름: " + stu.getName()
                + "  학번: " + stu.getStudentId()
                + "  " + stu.getYear() + "학년 " + stu.getSemester() + "학기");
            
             int[] scores = stu.getScores();
             for (int j = 0; j < subjects.length; j++){
                 if (scores[j] >= 0){
                     System.out.println("  " + subjects[j]
                        + ": " + scores[j] + "점" 
                        + "  (" + stu.getGrade(scores[j]) + ")"); 
            }
            else{
                 System.out.println("  " + subjects[j] + "\t: 미입력");
                }
       }
       
       double gpa = Math.round(stu.calcAvgGPA(credits) * 100)  / 100.0;
       System.out.println("  평점 평균: " + gpa);
       
       //마지막 학생이 아닐 때만 계속 여부 확인 
       if (i < count -1){
           System.out.print("  계속: Enter  /  중단:q  >>  ");
           String input = scan.nextLine();
           if (input.equals("q")){
               System.out.println("조회를 중단합니다.");
               break;
            }
       }
    }
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