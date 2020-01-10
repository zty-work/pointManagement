package task;

import taskStrategy.FixDurationCountDownLifeCycleStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TaskTimerManager {
    private Map<UserTask, TimerTask> tasksToRefresh = new HashMap<>();
    private static TaskTimerManager instance;
    Timer timer = new Timer();

    private TaskTimerManager() {
    }

    public static TaskTimerManager getInstance() {
        if (instance == null) {
            instance = new TaskTimerManager();
        }
        return instance;
    }


    public void addTask(UserTask task, FixDurationCountDownLifeCycleStrategy lifeCycleStrategy) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                task.setStatus(UserTaskStatus.Active);
                lifeCycleStrategy.refresh();
            }
        };
        timer.schedule(timerTask, lifeCycleStrategy.getStartTime(), lifeCycleStrategy.getDuration());
        tasksToRefresh.put(task, timerTask);
    }

    public void deleteTask(UserTask task){

    }

}
