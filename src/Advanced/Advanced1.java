package Advanced;

import java.util.ArrayList;
import java.util.List;

public class Advanced1 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setName("田中太郎");
        student1.setAge(25);

        Hobby hobby1 = new Hobby();
        hobby1.setName("読書");
        student1.setHobbyList(List.of(hobby1));

        students.add(student1);

        Student student2 = new Student();
        student2.setName("山本花子");
        student2.setAge(22);

        Hobby hobby2 = new Hobby();
        hobby2.setName("音楽鑑賞");
        student2.setHobbyList(List.of(hobby2));

        students.add(student2);

        Student student3 = new Student();
        student3.setName("佐藤健太");
        student3.setAge(28);

        Hobby hobby3 = new Hobby();
        hobby3.setName("スポーツ");
        student3.setHobbyList(List.of(hobby3));

        students.add(student3);

        StudentDao dao = new StudentDao();

        for (Student student : students) {
            dao.insert(student);
        }

        System.out.println("データベースへの挿入が完了しました。");
    }
}
