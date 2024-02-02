package com.app.EmployeManagement.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {
    @Id
    private int id;
    private String name;
    private Time duration;
    private Timestamp start_date;

    public Project(int id, String name, Time duration, Timestamp start_date) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.start_date = start_date;
    }

    public Project() {
    }

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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && Objects.equals(getName(), project.getName()) && Objects.equals(getDuration(), project.getDuration()) && Objects.equals(getStart_date(), project.getStart_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDuration(), getStart_date());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", start_date=" + start_date +
                '}';
    }
}
