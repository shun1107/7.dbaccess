package Advanced;

import java.util.List;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private List<Hobby> hobbyList;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public List<Hobby> getHobbyList() {
        return hobbyList;
    }
    public void setHobbyList(List<Hobby> hobbyList) {
        this.hobbyList = hobbyList;
    }
}
