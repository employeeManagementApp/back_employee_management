package com.app.EmployeManagement.Entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Time duration;
    private Timestamp start_date;
    private boolean status;

    public Project(int id, String name, Time duration, Timestamp start_date, boolean status) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.start_date = start_date;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && isStatus() == project.isStatus() && Objects.equals(getName(), project.getName()) && Objects.equals(getDuration(), project.getDuration()) && Objects.equals(getStart_date(), project.getStart_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDuration(), getStart_date(), isStatus());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", start_date=" + start_date +
                ", status=" + status +
                '}';
    }
}
