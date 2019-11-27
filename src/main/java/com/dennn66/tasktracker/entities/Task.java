package com.dennn66.tasktracker.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasks")
public class Task implements Serializable, Comparable<Task>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;//id,
    @Column(name = "name")
    private String name;// название,
    @Column(name = "creator")
    private String creator;// имя владельца задачи,
    @Column(name = "assignee")
    private String assignee; // имя исполнителя,
    @Column(name = "description")
    private String description; // описание,
    @Column(name = "status")
    private Status status; // статус

    @Override
    public int compareTo(Task task) {
        return status.priority - task.status.priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        CREATED("Открыта", 1), INPROGRESS("В работе", 2), COMPLETED("Завершена", 3);
        String name;
        int priority;

        Status(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "'" + name + "'";
        }
    }

    public Task(String name, String creator, String assignee, String description, Status status) {
        this.name = name;
        this.creator = creator;
        this.assignee = assignee;
        this.description = description;
        this.status = status;
}
    public Task(String name, String creator, String description) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        assignee = "";
        status = Status.CREATED;
    }

    public Task() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder TaskString = new StringBuilder();
        return TaskString.append(id).
                append(" ").append(name).
                append(" status:").append(status).
                append(" assignee:").append(assignee).
                append(" author:").append(creator).
                append(" description: ").append(description).toString();
    }

    public void info(){  System.out.println(this); }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Task)) return false;
        return ((Task)obj).id.equals(id) && ((Task)obj).name.equals(name);
    }

    @Override
    public int hashCode() { return id.intValue() + name.hashCode(); }
}
