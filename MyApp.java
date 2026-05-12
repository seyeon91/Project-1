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
    static final int CANCEL = -9;//취소
    static Scanner scan = new Scanner(System.in);

    // 과목&학점
    static String[] subjects = {"AI소프트웨어기초1", "소프트웨어공학개론", "프로그래밍응용1(PBL)",
                                "컴퓨터데이터구조", "DB설계와활용", "소프트웨어품질관리",
                                "SW프로젝트", "기초IT산업트렌드와진로", "MLOps(블렌디드)",
                                "IT기술마케팅방법론", "IT기술마케팅방법론", "AIX시스템최적화"};
    static int[] credits = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};

    // 학생 객체
    static Student[] students = new Student[MAX];
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
        System.out.println("  2. 전체 학생 성적표");
        System.out.println("  3. 학생 성적 조회");
        System.out.println("  4. 과목별 성적 조회");
        System.out.println("  0. 종료");
        System.out.println("----------------------------");
    }

    // 1. 성적 입력
    static void inputGrade(){
        if (count >= MAX) {
            System.out.println("최대 인원을 초과했습니다.");
            return;
        }

        System.out.println("\n[ 성적 입력 ] ");

        // 이름 입력 -> -9 이면 취소 
        System.out.print("이름  : ");
        String name = scan.nextLine();
        
        int id = inputInt("학번 : ", 0, 2147483647);//2147483647 = int 최대값 
        if (id == CANCEL){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }

        // 학번 입력 -> -9 이면 취소
        int year = inputInt("학년 (1~4, 취소: -9): ", 1, 4);
        if (year == CANCEL){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }
        
        // 학기 입력 -> CANCEL 이면 취소 
        int sem = inputInt("학기 (1~2, 취소: -9): ", 1, 2);
        if (sem == CANCEL){
            System.out.println("성적 입력을 취소합니다.");
            return;
        }

        Student std = new Student(name, id, year, sem); //객체 생성 
        
        System.out.println("\n[ 개설 과목 목록 ]");
        for(int i = 0; i < subjects.length; i++){
            System.out.print(subjects[i]);
            if(i < subjects.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("\n점수를 입력할 과목명을 직접 입력해주세요. (입력을 모두 마치려면 Enter를 누르세요.)");
        
        boolean cancelled = false;
        while(true){
            System.out.print("\n과목명 입력 (입력 완료: Enter): ");
            String subjectName = scan.nextLine();
            
            // 엔터만 치면 입력을 종료하고 저장 단계로 넘어감
            if(subjectName.isEmpty()){
                break;
            }
            
            // 입력한 과목명이 배열에 있는지 찾기
            int index = -1;
            for(int i = 0; i < subjects.length; i++){
                if(subjects[i].equals(subjectName)){
                    index = i;
                    break;
                }
            }
            
            // 잘못된 과목명을 입력했을 경우
            if(index == -1){
                System.out.println("존재하지 않는 과목입니다.");
                continue;
            }
            
            // 올바른 과목명이라면 해당 과목의 점수 입력
            int score = inputInt(subjects[index] + "점수 (0~100, 입력 전체 취소: q): ", 0, 100);
            if(score == CANCEL){
                cancelled = true;
                break;
            }
            
            // 학생 객체 점수 저장
            std.setScores(index, score);
            System.out.println(">> [" + subjects[index] + "] 과목 성적이 " + score + "점으로 등록되었습니다.");
        }

        if (cancelled){
            System.out.println("성적 입력을 취소합니다.");
        } else {
            students[count++] = std;
            System.out.println("\n등록 완료: " + name + " (" + id + ")");
        }
    }

    // 2. 전체 학생 성적표 
    static void viewAll(){
        if (count == 0){
            System.out.println("등록된 학생이 없습니다. ");
            return;
        }

        System.out.println("\n[ 전체 성적 조회 ]  (중단: -9)");

        for (int i = 0; i < count; i++){
            Student std = students[i]; 
            System.out.println("이름: " + std.getName()
                + "  학번: " + std.getStudentId()
                + "  " + std.getYear() + "학년 " + std.getSemester() + "학기");

            int[] scores = std.getScores();
            double sum = 0.0;
            int cnt = 0;
            
            for (int j = 0; j < subjects.length; j++){
                if (scores[j] >= 0){
                    System.out.println("  " + subjects[j]
                        + ": " + scores[j] + "점" 
                        + "  (" + std.getGrade(scores[j]) + ")");
                    sum += std.getGPA(std.getGrade(scores[j]));
                    cnt++;
                }
                else{
                    System.out.println("  " + subjects[j] + "\t: 미입력");
                }
            }

            double gpa = (int)(std.calcAvgGPA(credits) * 100)  / 100.0;
            System.out.println("  평점 평균: " + gpa);

            //마지막 학생이 아닐 때만 계속 여부 확인 
            if (i < count -1){
                System.out.print("  계속: Enter  /  중단: -9  >>  ");
                int input = inputInt("", -9, 1);
                if (input == CANCEL){
                    System.out.println("조회를 중단합니다.");
                    break;
                }
            }
        }
    } 

    // 3. 학생 성적 조회
    static void searchStudent(){
        int keyword = inputInt("\n검색할 학번 입력 (취소: -9): ", 0, 2147483647);//2147483647 = int 최대값 
        if(keyword == CANCEL){   // if 조건문 - 탈출
            System.out.println("조회를 취소합니다.");
            return;
        }
        
        boolean found = false;
        for(int i = 0; i < count; i++){
            if(students[i].getStudentId() != keyword){
               continue; 
            }
            
            Student s = students[i];
            System.out.println("\n[ 성적표 ]");
            s.print();
            System.out.println("과목명\t\t학점\t점수\t등급");
            
            int[] scores = s.getScores();
            double sum = 0.0;
            int cnt = 0;
            for(int j = 0; j < subjects.length; j++){
                if(scores[j] >= 0){
                   System.out.println(subjects[j]
                       + "\t" + credits[j] + "학점"
                       + "\t" + scores[j] + "점"
                       + "\t" + s.getGrade(scores[j]));
                }else{
                    System.out.println(subjects[j]
                        + "\t" + credits[j] + "학점"
                        + "\t미입력");
                }
            }
            
            double gpa = (int)((double) sum / cnt * 10) / 10.0;
            System.out.println("평점 평균: " + gpa);
            
            // 마지막 학생이 아닐 때만 계속 여부 확인
            if(i < count - 1){
                System.out.print("계속: Enter / 중단: -9 >>  ");
                break;
            }
        }
    }

    // 4. 과목별 성적 조회
    static void searchSubject(){
        System.out.println("\n과목 선택:  (취소: -9)");
        for(int i = 0; i < subjects.length; i++){
            System.out.println(subjects[i]);
            if( i < subjects.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.print("\n조회할 과목명 입력(취소: -9): ");
        String keyword = scan.nextLine();
        
        if(keyword.equals("q")){
            System.out.println("조회를 취소합니다.");
            return;
        }
        
        int index = -1;
        for(int i = 0; i < subjects.length; i++){
            if(subjects[i].equals(keyword)){
                index = i;
                break;
            }
        }
        
        if(index == -1){
            System.out.println("입력하신 과목명(" + keyword + ")을 찾을 수 없습니다. 정확히 입력해주세요.");
            return;
        }
        
        System.out.println("\n[ " + subjects[index] + " 성적 조회 ]");
        System.out.println("이름\t학번\t\t점수\t등급");
        
        int sum = 0, cnt = 0, max = -1, min = 101;
        
        for(int i = 0; i < count; i++){
            int score = students[i].getScores()[index];
            if(score < 0){
                continue;
            }
            System.out.println(students[i].getName()
                + "\t" + students[i].getStudentId()
                + "\t" + score + "점"
                + "\t" + students[i].getGrade(score));
            sum += score;
            cnt++;
            if(score > max){
                max = score;
            }
            
            if(score < min){
                min = score;
            }
        }
        
        if(cnt > 0){
            double avg = (int)((double) sum / cnt * 10) / 10.0;
            System.out.println("인원: " + cnt + "명"
                + " 최고: " + max + "점"
                + " 최저: " + min + "점"
                + " 평균: " + avg + "점");
            
        }else{
            System.out.println("입력된 성적이 없습니다.");
        }
    }

    // 프로그램 중단 기능 (-9 입력시 초기화)
    static int inputInt(String prompt, int min, int max){
        while (true){                   //while 반복문
            try {
                System.out.print(prompt);

                int val = scan.nextInt();
                scan.nextLine();
                if (val == CANCEL){  // if 조건문 -9 입력 시 취소
                    return CANCEL;
                }
                if (val >= min && val <= max){
                    return val;
                }
                System.out.println(" " + min + " ~ " + max + " 사이의 값을 입력해주세요. (취소: -9)");
            }
            catch (NumberFormatException e){
                scan.nextLine();
                System.out.println(" 숫자 또는 -9를 입력하세요.");
            }
        }
    }
}