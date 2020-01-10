package test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task.TaskPerformer;
import transaction.Account;
import transaction.ConsumerTransaction;
import transaction.Flow;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Diff2Test2 {
    public TaskPerformer initializing(){
        TaskPerformer taskPerformer = new TaskPerformer("用户1");
        List<Flow> performerFlows=new ArrayList<>();
        performerFlows.add(new Flow(1000,"初始积分"));
        taskPerformer.setAccount(new Account(performerFlows));
        return taskPerformer;
    }
    @Before
    public void setUp() throws Exception {
        System.out.println("Tests begin! Good luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tests end! Are you satisfied?");
    }

    @Test
    public void exchangeWithPoints() throws Exception {
        TaskPerformer taskPerformer=initializing();
        int point=taskPerformer.getAccount().getBalance();
        System.out.println("当前积分"+point+"分，可以兑换700分的可乐，现在进行兑换");
        List<Flow> exchangeFlows=new ArrayList<>();
        exchangeFlows.add(new Flow(-700,"兑换可乐"));
        ConsumerTransaction consumerTransaction=new ConsumerTransaction(exchangeFlows,taskPerformer);
        consumerTransaction.commit();
        assertEquals(point-700,taskPerformer.getAccount().getBalance());
       // assertEquals("兑换可乐",taskPerformer.getAccount().getFlows().get(-1).getDescription());
    }
}
