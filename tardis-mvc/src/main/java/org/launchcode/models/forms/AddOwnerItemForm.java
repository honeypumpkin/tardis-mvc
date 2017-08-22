package org.launchcode.models.forms;

import org.launchcode.models.Task;
import org.launchcode.models.Owner;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by LaunchCode
 */
public class AddOwnerItemForm {

    @NotNull
    private int ownerId;

    @NotNull
    private int taskId;

    private Iterable<Task> tasks;

    private Owner owner;

    public AddOwnerItemForm() {}

    public AddOwnerItemForm(Iterable<Task> tasks, Owner owner) {
        this.tasks = tasks;
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Iterable<Task> getTasks() {
        return tasks;
    }

    public Owner getOwner() {
        return owner;
    }
}
