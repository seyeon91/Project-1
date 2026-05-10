
/**
 * Student 클래스의 설명을 작성하세요.
 *
 * @author (복창희, 호준수)
 * @version (2026.05.10)
 */
public class Student
{
    String name;
    String studentId;
    int year;
    int semester;
    int[] scores;
    
    /**
     * Student 클래스의 객체 생성자
     */
    public Student(String name, String studentId, int year, int semester)
    {
        this.name = name;
        this.studentId = studentId;
        this.year = year;
        this.semester = semester;
        this.scores = new int[6];
        for(int i = 0; i < 6; i++){
            this.scores[i] = -1;
         }
    }

    /**
     * 점수 > 등급 변환
     */
    public String getGrade(int score)
    {
        if(score >= 95){
            return "A+";
        }
        else if(score >= 90){
            return "A";
        }
        //나머지 작성
        else{
            return " ";
        }
    }
    
    double getGPA(String grade){
        return 0;
    }
    
    double calcAvgGPA(int[] credits){
        return 0;
    }
    
    void print(){
        System.out.println("이름:" + name + "학번" + studentId);
    }
}