package Advanced;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import EmployeeSample.DBManager;

public class StudentDao {
    private static final String TABLE_NAME_STUDENTS = "students";
    private static final String TABLE_NAME_HOBBIES = "hobbies";

    public Student load(Integer id) {
        try (Connection con = DBManager.createConnection()) {
            String sql = """
                    SELECT
                    s.id AS student_id,
                    s.name AS student_name,
                    s.age AS student_age,
                    h.id AS hobby_id,
                    h.name AS hobby_name
                    FROM
                    """
                    + TABLE_NAME_STUDENTS +
                    """
                     s
                    LEFT JOIN
                    """
                    + TABLE_NAME_HOBBIES +
                    """
                     h
                    ON
                    s.id = h.student_id
                    WHERE s.id = ?;
                    """;

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    Student student = null;

                    while (rs.next()) {
                        if (student == null) {
                            student = new Student();
                            student.setId(rs.getInt("student_id"));
                            student.setName(rs.getString("student_name"));
                            student.setAge(rs.getInt("student_age"));
                            student.setHobbyList(new ArrayList<>());
                        }

                        Hobby hobby = new Hobby();
                        hobby.setId(rs.getInt("hobby_id"));
                        hobby.setName(rs.getString("hobby_name"));
                        hobby.setStudentId(student.getId());
                        student.getHobbyList().add(hobby);
                    }

                    return student;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("load処理に失敗しました", e);
        }
    }

    public List<Student> findAll() {
        try (Connection con = DBManager.createConnection()) {
            String sql = """
                    SELECT
                    s.id AS student_id,
                    s.name AS student_name,
                    s.age AS student_age,
                    h.id AS hobby_id,
                    h.name AS hobby_name
                    FROM
                    """
                    + TABLE_NAME_STUDENTS +
                    """
                     s
                    LEFT JOIN
                    """
                    + TABLE_NAME_HOBBIES +
                    """
                     h
                    ON
                    s.id = h.student_id;
                    """;

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    List<Student> students = new ArrayList<>();

                    while (rs.next()) {
                        Integer studentId = rs.getInt("student_id");

                        Student existingStudent = findStudentInList(students, studentId);

                        if (existingStudent == null) {
                            Student student = new Student();
                            student.setId(studentId);
                            student.setName(rs.getString("student_name"));
                            student.setAge(rs.getInt("student_age"));
                            student.setHobbyList(new ArrayList<>());

                            Hobby hobby = new Hobby();
                            hobby.setId(rs.getInt("hobby_id"));
                            hobby.setName(rs.getString("hobby_name"));
                            hobby.setStudentId(studentId);
                            student.getHobbyList().add(hobby);

                            students.add(student);
                        } else {
                            Hobby hobby = new Hobby();
                            hobby.setId(rs.getInt("hobby_id"));
                            hobby.setName(rs.getString("hobby_name"));
                            hobby.setStudentId(studentId);
                            existingStudent.getHobbyList().add(hobby);
                        }
                    }

                    return students;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("findAll処理に失敗しました", e);
        }
    }

    public void insert(Student student) {
        try (Connection con = DBManager.createConnection()) {
            String insertStudentSql = """
                    INSERT INTO
                    """
                    + TABLE_NAME_STUDENTS +
                    """
                    (name, age)
                    VALUES (?, ?)
                    RETURNING id;
                    """;

            String insertHobbySql = """
                    INSERT INTO
                    """
                    + TABLE_NAME_HOBBIES +
                    """
                    (name, student_id)
                    VALUES (?, ?);
                    """;

            try {
                con.setAutoCommit(false);


                try (PreparedStatement pstmt = con.prepareStatement(insertStudentSql)) {
                    pstmt.setString(1, student.getName());
                    pstmt.setInt(2, student.getAge());
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            student.setId(rs.getInt("id"));
                        } else {
                            throw new RuntimeException("学生の挿入に失敗しました");
                        }
                    }
                }


                try (PreparedStatement pstmt = con.prepareStatement(insertHobbySql)) {
                    for (Hobby hobby : student.getHobbyList()) {
                        pstmt.setString(1, hobby.getName());
                        pstmt.setInt(2, student.getId());
                        pstmt.executeUpdate();
                    }
                }

                con.commit();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception rollbackException) {
                    rollbackException.printStackTrace();
                }
                e.printStackTrace();
                throw new RuntimeException("挿入処理に失敗しました", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DBの接続に失敗しました", e);
        }
    }
    
    private Student findStudentInList(List<Student> students, Integer studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
