package task;

import taskStrategy.TaskCycleLifeStrategy;
import taskStrategy.TaskPointStrategy;

public class TaskDef {
    private String name;
    TaskPointStrategy taskPointStrategy;
    TaskCycleLifeStrategy taskCycleLifeStrategy;

    public TaskDef(String name, TaskPointStrategy taskPointStrategy, TaskCycleLifeStrategy taskCycleLifeStrategy) {
        this.name = name;
        this.taskPointStrategy = taskPointStrategy;
        this.taskCycleLifeStrategy = taskCycleLifeStrategy;
    }

    public void setTaskPointStrategy(TaskPointStrategy taskPointStrategy) {
        this.taskPointStrategy = taskPointStrategy;
    }

    public void setTaskCycleLifeStrategy(TaskCycleLifeStrategy taskCycleLifeStrategy) {
        this.taskCycleLifeStrategy = taskCycleLifeStrategy;
    }

    public String getName() {
        return name;
    }
}
