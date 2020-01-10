package test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task.*;
import taskStrategy.*;
import transaction.ConsumerTransaction;
import transaction.Flow;
import transaction.PerformTaskTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static task.UserTaskStatus.Active;


public class Diff1Test2 {
    public List<UserTask> initializing() {
        TaskPerformer taskPerformer = new TaskPerformer("用户1");
        TaskPerformer taskPerformer2 = new TaskPerformer("用户2");
        List<UserTask> allTasks = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String str = "2019/1/1 00:00:00";
        String str2 = "2019/2/8 00:00:00";
        String str3 = "2019/8/8 10:30:00";
        String str4 = "2019/2/8 23:15:00";
        Date date = new Date();
        Date date2 = new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        allTasks.add(new UserTask(new TaskDef("新手限定", new FixPointStrategy(800), new OneLifeCycleStrategy()), taskPerformer));
        allTasks.add(new UserTask(new TaskDef("新手限定", new FixPointStrategy(800), new OneLifeCycleStrategy()), taskPerformer2));
        allTasks.add(new UserTask(new TaskDef("冬日限定", new FixPointStrategy(800), new OneLifeCycleStrategy()), taskPerformer));
        allTasks.add(new UserTask(new TaskDef("冬日限定", new FixPointStrategy(800), new OneLifeCycleStrategy()), taskPerformer2));
        allTasks.add(new UserTask(new TaskDef("每一天两次", new FixPointStrategy(800), new DailyCountDownLifeCycleStrategy(2, date)), taskPerformer));
        allTasks.add(new UserTask(new TaskDef("每一天两次", new FixPointStrategy(800), new DailyCountDownLifeCycleStrategy(2, date)), taskPerformer2));
        allTasks.add(new UserTask(new TaskDef("每5秒一次", new FixPointStrategy(800), new FixDurationCountDownLifeCycleStrategy(1, 5000, date)), taskPerformer));
        allTasks.add(new UserTask(new TaskDef("分享（无限次）", new FixPointStrategy(800), new InfiniteLifeCycleStrategy()), taskPerformer));
        allTasks.add(new UserTask(new TaskDef("分享（无限次）", new FixPointStrategy(800), new InfiniteLifeCycleStrategy()), taskPerformer2));
        new PerformTaskTransaction(allTasks.get(0)).commit();
        new PerformTaskTransaction(allTasks.get(1)).commit();
        new PerformTaskTransaction(allTasks.get(4)).commit();
        new PerformTaskTransaction(allTasks.get(5)).commit();
        new PerformTaskTransaction(allTasks.get(6)).commit();
        return allTasks;
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Tests begin! Good luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tests end! Are you satisfied?");
    }

    public void printCompletedTask(List<UserTask> userTasks, String username) {
        System.out.println("已完成任务：");
        for (UserTask userTask : userTasks) {
            if (userTask.getTaskPerformer().getName().equals(username)) {
                for (UserTaskAction userTaskAction : userTask.getUserTaskActions()) {
                    if (userTaskAction.getUserTaskActionType().equals(UserTaskActionType.COMPLETE)) {
                        System.out.print(userTask.getTaskDef().getName());
                        System.out.println(userTaskAction.getActionTime().toString());
                    }
                }
            }
        }
    }

    public List<String> getToCompleteTaskNames(List<UserTask> userTasks, String username) {
        System.out.println("待完成任务：");
        List<String> toCompleteTaskNames=new ArrayList<>();
        for (UserTask userTask : userTasks) {
            if ((userTask.getStatus().equals(Active)) && (userTask.getTaskPerformer().getName().equals(username))) {
                System.out.println(userTask.getTaskDef().getName());
                toCompleteTaskNames.add(userTask.getTaskDef().getName());
            }
        }
        return toCompleteTaskNames;
    }

    @Test
    public void queryTasks() throws Exception {
        String username = "用户1";
        List<UserTask> userTasks = initializing();
        System.out.println("当前用户：" + username);
        printCompletedTask(userTasks, username);
        List<String> toCompleteTaskNames=new ArrayList<>();
        toCompleteTaskNames.add("冬日限定");
        toCompleteTaskNames.add("每一天两次");
        toCompleteTaskNames.add("分享（无限次）");
        assertEquals(toCompleteTaskNames,getToCompleteTaskNames(userTasks,username));
        System.out.println("等待5秒：");
        Thread.sleep(5000);
        List<String> toCompleteTaskNames2=new ArrayList<>();
        toCompleteTaskNames2.add("冬日限定");
        toCompleteTaskNames2.add("每一天两次");
        toCompleteTaskNames2.add("每5秒一次");
        toCompleteTaskNames2.add("分享（无限次）");
        printCompletedTask(userTasks, username);
        assertEquals(toCompleteTaskNames2,getToCompleteTaskNames(userTasks,username));
        System.out.println("再完成一次每日任务（2/2）");
        new PerformTaskTransaction(userTasks.get(4)).commit();
        toCompleteTaskNames2.remove("每一天两次");
        printCompletedTask(userTasks, username);
        assertEquals(toCompleteTaskNames2,getToCompleteTaskNames(userTasks,username));
    }
}
