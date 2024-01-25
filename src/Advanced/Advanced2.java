package Advanced;

import java.util.List;

public class Advanced2 {
    public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Student student = dao.load(1);

        System.out.println("学生情報:");

        System.out.println("ID: " + student.getId());
        System.out.println("名前: " + student.getName());
        System.out.println("年齢: " + student.getAge());

        List<Hobby> hobbies = student.getHobbyList();
        System.out.println("趣味:");
        for (Hobby hobby : hobbies) {
            System.out.println(" - " + hobby.getName());
        }
    }
}
