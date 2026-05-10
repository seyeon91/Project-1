
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
            case "
        }
    }
    
    double calcAvgGPA(int[] credits){
        return 0;
    }
    
    void print(){
        System.out.println("이름:" + name + "학번" + studentId);
    }
}