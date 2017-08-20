package org.launchcode.models.forms;

import org.launchcode.models.Menu;
import org.launchcode.models.Task;

import javax.validation.constraints.NotNull;

/**
 * Created by LaunchCode
 */
public class AddMenuItemForm {

    @NotNull
    private int menuId;

    @NotNull
    private int taskId;

    private Iterable<Task> tasks;

    private Menu menu;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Iterable<Task> tasks, Menu menu) {
        this.tasks = tasks;
        this.menu = menu;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public Menu getMenu() {
        return menu;
    }
}
