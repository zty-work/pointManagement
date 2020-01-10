package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task.*;
import taskStrategy.*;
import transaction.PerformTaskTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


//测试用例：难度2，场景1
//运营方发布不同类型的任务
public class Diff2Test1 {
    //任务类型存储
    List<TaskDef> taskDefs = new ArrayList<>();
    //任务存储
    List<UserTask> tasks = new ArrayList<>();
    //用户列表
    List<TaskPerformer> allUsers = new ArrayList<>();
    TaskTimerManager taskTimerManager = TaskTimerManager.getInstance();

    @Before
    public void setUp() throws Exception {
        System.out.println("Tests begin!");
        init();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tests end!");
    }

    public void init() {
        allUsers.add(new TaskPerformer("testuser1"));
        System.out.println("初始化系统状态：");
        printSystem();
    }

    @Test
    public void differentTask() throws Exception {
        //无限制
        TaskDef newTaskDef = new TaskDef("消费(无限制)", new FixPointStrategy(50), new InfiniteLifeCycleStrategy());
        taskDefs.add(newTaskDef);
        UserTask userTaskInfinite = null;
        for (TaskPerformer taskPerformer : allUsers) {
            userTaskInfinite = new UserTask(newTaskDef, taskPerformer);
            tasks.add(userTaskInfinite);
        }
        //一次性
        TaskDef newTaskDef2 = new TaskDef("活动(一次性)", new FixPointStrategy(100), new OneLifeCycleStrategy());
        taskDefs.add(newTaskDef2);
        UserTask userTaskOneTime = null;
        for (TaskPerformer taskPerformer : allUsers) {
            userTaskOneTime = new UserTask(newTaskDef2, taskPerformer);
            tasks.add(userTaskOneTime);
        }
        //固定次数
        TaskDef newTaskDef3 = new TaskDef("推荐用户(固定3次)", new FixPointStrategy(100),
                new TotalCountDownLifeCycleStrategy(3));
        taskDefs.add(newTaskDef3);
        UserTask userTaskFix = null;
        for (TaskPerformer taskPerformer : allUsers) {
            userTaskFix = new UserTask(newTaskDef3, taskPerformer);
            tasks.add(userTaskFix);
        }
        //每30秒一次，设定时间为每日一点更新
        Date date = new Date();
        date.setHours(1);
        date.setMinutes(0);
        date.setSeconds(0);
        TaskDef newTaskDef4 = new TaskDef("每日签到(每日一次)", new FixPointStrategy(100), new DailyCountDownLifeCycleStrategy(1, date));
        taskDefs.add(newTaskDef4);
        UserTask userTaskDaily = null;
        for (TaskPerformer taskPerformer : allUsers) {
            userTaskDaily = new UserTask(newTaskDef4, taskPerformer);
            tasks.add(userTaskDaily);
        }
        //每10秒一次
        TaskDef newTaskDef5 = new TaskDef("测试(每十秒一次)", new FixPointStrategy(100),
                new FixDurationCountDownLifeCycleStrategy(1, 10000, new Date()));
        taskDefs.add(newTaskDef5);
        UserTask userTaskTenSecond = null;
        for (TaskPerformer taskPerformer : allUsers) {
            userTaskTenSecond = new UserTask(newTaskDef5, taskPerformer);
            tasks.add(userTaskTenSecond);
        }

        System.out.println("添加任务后系统状态：");
        printSystem();
        assertEquals(userTaskInfinite.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskOneTime.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskFix.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskDaily.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskTenSecond.getStatus(), UserTaskStatus.Active);


        //完成所有任务
        for (UserTask task : tasks) {
            new PerformTaskTransaction(task).commit();
        }
        System.out.println("完成一次所有任务后系统状态：");
        printSystem();
        assertEquals(userTaskInfinite.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskOneTime.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskFix.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskDaily.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskTenSecond.getStatus(), UserTaskStatus.Finished);


        new PerformTaskTransaction(userTaskFix).commit();
        new PerformTaskTransaction(userTaskFix).commit();
        System.out.println("完成固定次数任务：");
        printSystem();
        assertEquals(userTaskInfinite.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskOneTime.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskFix.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskDaily.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskTenSecond.getStatus(), UserTaskStatus.Finished);


        System.out.println("等待20秒.....");
        Thread.sleep(20000);
        System.out.println("20秒后系统状态：");
        printSystem();
        assertEquals(userTaskInfinite.getStatus(), UserTaskStatus.Active);
        assertEquals(userTaskOneTime.getStatus(), UserTaskStatus.Finished);
        assertEquals(userTaskFix.getStatus(), UserTaskStatus.Finished);
        if (new Date().getTime() > date.getTime() + 86400000) {
            assertEquals(userTaskDaily.getStatus(), UserTaskStatus.Active);
        }else {
            assertEquals(userTaskDaily.getStatus(), UserTaskStatus.Finished);
        }
        assertEquals(userTaskTenSecond.getStatus(), UserTaskStatus.Active);


    }

    private void printSystem() {
        System.out.println("-----任务状态-----");
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
