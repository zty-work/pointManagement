package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task.TaskDef;
import task.TaskPerformer;
import task.UserTask;
import taskStrategy.FixPointStrategy;
import taskStrategy.InfiniteLifeCycleStrategy;

import java.util.ArrayList;
import java.util.List;


//测试用例：难度1，场景1
//运营方发布一个任务
public class Diff1Test1 {

    //任务类型存储
    List<TaskDef> taskDefs = new ArrayList<>();
    //任务存储
    List<UserTask> tasks = new ArrayList<>();
    //用户列表
    List<TaskPerformer> allUsers = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        System.out.println("Tests begin!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tests end!");
    }

    @Test
    public void operateWithPoints() throws Exception {

        allUsers.add(new TaskPerformer("testuser1"));
        allUsers.add(new TaskPerformer("testuser2"));

        System.out.println("初始化系统状态：");
        printSystem();

        //系统添加任务
        TaskDef newTaskDef = new TaskDef("推荐用户", new FixPointStrategy(50), new InfiniteLifeCycleStrategy());
        taskDefs.add(newTaskDef);
        for (TaskPerformer taskPerformer : allUsers) {
            tasks.add(new UserTask(newTaskDef, taskPerformer));
        }

        TaskDef newTaskDef2 = new TaskDef("消费", new FixPointStrategy(100), new InfiniteLifeCycleStrategy());
        taskDefs.add(newTaskDef2);
        for (TaskPerformer taskPerformer : allUsers) {
            tasks.add(new UserTask(newTaskDef2, taskPerformer));
        }

        System.out.println("添加两个任务后系统状态：");
        printSystem();

    }

    private void printSystem() {
        System.out.println("-----用户列表-----");
        for (TaskPerformer taskPerformer : allUsers) {
            System.out.println(taskPerformer.getName());
        }
        System.out.println("-----任务类型列表-----");
        if (taskDefs.size() == 0) {
            System.out.println("空");
        } else {
            for (TaskDef taskDef : taskDefs) {
                System.out.println(taskDef.getName());
            }
        }
        System.out.println("-----任务列表-----");
        if (tasks.size() == 0) {
            System.out.println("空");
        } else {
            for (UserTask task : tasks) {
                System.out.println("任务类型:" + task.getTaskDef().getName() + "  任务拥有者:" + task.getTaskPerformer().getName() + " 任务状态:" + task.getStatus());
            }
        }
        System.out.println();
    }


}
