
/**
 * Student 클래스의 설명을 작성하세요.
 *
 * @author (복창희, 호준수)
 * @version (2026.05.10)
 */
public class Student
{
    //학생 클래스
    private String name;
    private int studentId;
    private int year;
    private int semester;
    private int[] scores;
    
    
    //Student 클래스의 객체 생성자
    public Student(String name, int studentId, int year, int semester){
        this.name = name;
        this.studentId = studentId;
        this.year = year;
        this.semester = semester;
        this.scores = new int[100];
        for(int i = 0; i < 100; i++){
            this.scores[i] = -1;
         }
    }
    
    public String getName(){
        return name;
    }
    public int getStudentId(){
        return studentId;
    }
    public int getYear(){
        return year;
    }
    public int getSemester(){
        return semester;
    }
    public int[] getScores(){
        return scores;
    }
    public void setScores(int index, int score){
        this.scores[index] = score;
    }
    
    // 점수 > 등급 변환
    public String getGrade(int score){
        if(score >= 95){
            return "A+";
        }
        else if(score >= 90){
            return "A";
        }
        else if(score >= 85){
            return "B+";
        }
        else if(score >= 80){
            return "B";
        }
        else if(score >= 75){
            return "C+";
        }
        else if(score >= 70){
            return "C";
        }
        else if(score >= 65){
            return "D+";
        }
        else if(score >= 60){
            return "D";
        }
        else{
            return "F";
        }
    }
    //학점
    public double getGPA(String grade){
        switch (grade){
            case "A+":
                return 4.5;
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "D+":
                return 1.5;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }
    //평균 계산기
    public double calcAvgGPA(int[] credits){
        double total = 0;
        int creditSum = 0;
        for (int i = 0; i < scores.length; i++){
            if(scores[i] >= 0){
                total += getGPA(getGrade(scores[i])) * credits[i];
                creditSum += credits[i];
            }
        }
        return (creditSum > 0) ? total / creditSum : 0.0;
    }
    //학생 정보 출력
    public void print(){
        System.out.println("이름: " + name + " 학번 " + studentId + " " + year + " 학년 " + semester + " 학기 ");
    }
}