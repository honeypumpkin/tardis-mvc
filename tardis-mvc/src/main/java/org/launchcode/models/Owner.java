package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Owner extends AbstractEntity {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Task> tasks;

    public Owner() {}

    public void addItem(Task item) {
        tasks.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
