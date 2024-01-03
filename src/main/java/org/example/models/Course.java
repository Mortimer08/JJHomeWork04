package org.example.models;

import java.util.Random;

public class Course {
    private static final Random rnd = new Random();
    private static final String[] titles = new String[]{"History", "Physics", "Math", "Literature", "Chemistry", "Technology", "Biology"};
    private static final int[] durations = new int[]{3, 5, 6, 7, 12};

    public static Course create() {
        return new Course(durations[rnd.nextInt(durations.length)], titles[rnd.nextInt(titles.length)]);
    }

    public Course() {
    }

    public Course(int id,  String title, int duration) {
        this.id = id;
        this.duration = duration;
        this.title = title;
    }

    public Course(int duration, String title) {

        this.duration = duration;
        this.title = title;
    }

    public void updateTitle() {
        this.title = titles[rnd.nextInt(titles.length)];
    }

    public void updateDuration() {
        this.duration = durations[rnd.nextInt(durations.length)];
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    int id;

    int duration;
    String title;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", duration=" + duration +
                ", title='" + title + '\'' +
                '}';
    }
}
