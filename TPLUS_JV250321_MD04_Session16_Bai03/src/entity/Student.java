package entity;

import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void inputData (Scanner scanner) {
        System.out.println("Nhap ten hoc sinh: ");
        this.name = scanner.nextLine();
        System.out.println("Nhap tuoi hoc sinh: ");
        this.age = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "entity.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
