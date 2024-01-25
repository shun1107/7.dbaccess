package Advanced;

import java.util.List;

public class Advanced3 {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.findAll();

        System.out.println("全学生情報:");

        for (Student student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("名前: " + student.getName());
            System.out.println("年齢: " + student.getAge());

            List<Hobby> hobbies = student.getHobbyList();
            
            System.out.println("趣味:");
            for (Hobby hobby : hobbies) {
                System.out.println(" - " + hobby.getName());
            }

            System.out.println("-------------------");
        }
    }
}
