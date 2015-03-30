import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.util.*;

import java.io.PrintWriter;

/** 
* BackEnd Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 27, 2015</pre> 
* @version 1.0 
*/ 
public class BackEndTest {
    BackEnd bE;
    PrintWriter dailyWriter;
    PrintWriter userWriter;
    ArrayList<User> testUserArrayList;
    ArrayList<Ticket> testTicketArrayList;



    @Before
public void before() throws Exception {
        bE = new BackEnd();
        dailyWriter = new PrintWriter("../daily.txt");
        userWriter = new PrintWriter("../users.txt");
        testUserArrayList = new ArrayList<User>();
        testTicketArrayList = new ArrayList<Ticket>();


    }

@After
public void after() throws Exception { 
    dailyWriter.close();
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: buildUsers(ArrayList<User> users) 
* 
*/ 
@Test
public void testBuildUsers() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testBuildUsersWhileLoopTest1ZeroLoops() throws Exception {
        userWriter.write("");
        bE.buildUsers(testUserArrayList);
    }

    @Test
    public void testBuildUsersWhileLoopTest2OneLoops() throws Exception {
        userWriter.write("");
        userWriter.write("maharshall-----_AA_010000");
        bE.buildUsers(testUserArrayList);
    }

    @Test
    public void testBuildUsersWhileLoopTest3TwoLoops() throws Exception {
        userWriter.write("");
        userWriter.write("maharshall-----_AA_010000");
        userWriter.write("alty-----------_FS_010000");
        bE.buildUsers(testUserArrayList);

    }

    @Test
    public void testBuildUsersWhileLoopTest4ManyLoops() throws Exception {
        userWriter.write("");
        userWriter.write("maharshall-----_AA_010000");
        userWriter.write("oliverqueen----_BS_010000");
        userWriter.write("JohnCena-------_AA_999999");
        userWriter.write("testadmin------_AA_010000");
        userWriter.write("testuser-------_FS_010000");
        userWriter.write("pooruser-------_BS_000001");
        bE.buildUsers(testUserArrayList);
    }

/** 
* 
* Method: buildEvents(ArrayList<Ticket> ticks) 
* 
*/ 
@Test
public void testBuildEvents() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testBuildEventsWhileLoopTest1ZeroLoops() throws Exception {
        userWriter.write("");
        bE.buildEvents(testTicketArrayList);
    }

    @Test
    public void testBuildEventsWhileLoopTest2OneLoops() throws Exception {
        userWriter.write("");
        userWriter.write("batman dances------------_testadmin------_010_000010");
        bE.buildEvents(testTicketArrayList);

    }

    @Test
    public void testBuildEventsWhileLoopTest3TwoLoops() throws Exception {
        userWriter.write("");
        userWriter.write("batman dances------------_testadmin------_010_000010");
        userWriter.write("poorman dances-----------_testadmin------_001_000010");
        bE.buildEvents(testTicketArrayList);

    }

    @Test
    public void testBuildEventsWhileLoopTest4ManyLoops() throws Exception {
        userWriter.write("");
        userWriter.write("batman dances------------_testadmin------_010_000010");
        userWriter.write("poorman dances-----------_testadmin------_001_000010");
        userWriter.write("johnCenaVsTheRock--------_testadmin------_020_000200");
        userWriter.write("ballinRave---------------_testadmin------_020_000400");
        userWriter.write("ChineseFireBall----------_testadmin------_003_000030");
        bE.buildEvents(testTicketArrayList);

    }

/** 
* 
* Method: writeUsers(ArrayList<User> users) 
* 
*/ 
@Test
public void testWriteUsers() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testWriteUsersWhileLoopTest1ZeroLoops() throws Exception {
        bE.writeUsers(testUserArrayList);

    }

    @Test
    public void testWriteUsersWhileLoopTest2OneLoops() throws Exception {
        User testUser1 = new User("jimmy", "AA", 100);
        testUserArrayList.add(testUser1);
        bE.writeUsers(testUserArrayList);

    }

    @Test
    public void testWriteUsersWhileLoopTest3TwoLoops() throws Exception {
        User testUser2 = new User("bobby", "FS", 10);
        testUserArrayList.add(testUser2);
        bE.writeUsers(testUserArrayList);

    }

    @Test
    public void testWriteUsersWhileLoopTest4ManyLoops() throws Exception {
        User testUser3 = new User("rickky","SS", 150);
        User testUser4 = new User("q", "BS", 110);

        testUserArrayList.add(testUser3);
        testUserArrayList.add(testUser4);
        bE.writeUsers(testUserArrayList);

    }

/**
*
* Method: writeEvents(ArrayList<Ticket> ticks)
*
*/
@Test
public void testWriteEvents() throws Exception {
//TODO: Test goes here...
}
    @Test
    public void testWriteEventWhileLoopTest1ZeroLoops() throws Exception {
        bE.writeEvents(testTicketArrayList);

    }

    @Test
    public void testWriteEventWhileLoopTest2OneLoops() throws Exception {
        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        testTicketArrayList.add(testEvent1);
        bE.writeEvents(testTicketArrayList);

    }

    @Test
    public void testWriteEventWhileLoopTest3TwoLoops() throws Exception {
        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        Ticket testEvent2 = new Ticket("Batman Dances", "Bruce", 5, 100);
        testTicketArrayList.add(testEvent1);
        testTicketArrayList.add(testEvent2);
        bE.writeEvents(testTicketArrayList);

    }

    @Test
    public void testWriteEventWhileLoopTest4ManyLoops() throws Exception {
        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        Ticket testEvent2 = new Ticket("Batman Dances", "Bruce", 5, 100);
        Ticket testEvent3 = new Ticket("JohnCena Vs TheRock", "JohnCena", 45, 200);
        Ticket testEvent4 = new Ticket("Party", "MyPants", 99, 1);

        testTicketArrayList.add(testEvent1);
        testTicketArrayList.add(testEvent2);
        testTicketArrayList.add(testEvent3);
        testTicketArrayList.add(testEvent4);

        bE.writeEvents(testTicketArrayList);

    }

/**
*
* Method: parseTransactions()
*
*/
@Test
public void testParseTransactionsWhileLoopTest1ZeroLoops() throws Exception {
    dailyWriter.write("");
    bE.parseTransactions();

}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase0() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("00_END OF SESSION-_00_000000");
    bE.parseTransactions();
}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase1() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("01_RealHacker-----_AA_000000");
    bE.parseTransactions();

}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase2() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("02_testuser-------_FS_000000");
    bE.parseTransactions();

}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase3() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("03_batman dances------------_testadmin------_010_000010");
    bE.parseTransactions();

}


@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase4() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("04_batman dances------------_testadmin------_040_000010");
    bE.parseTransactions();

}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase5() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("05_testuser-------_testadmin_------000010");
    bE.parseTransactions();

}

@Test
public void testParseTransactionsWhileLoopTest2OneLoopsCase6() throws Exception {
    dailyWriter.write("");
    dailyWriter.write("06_testuser-------_AA_000010");
    bE.parseTransactions();

}

@Test
    public void testParseTransactionsWhileLoopTest2OneLoopsDefaultCase() throws Exception {
        dailyWriter.write("");
        dailyWriter.write("xx_testuser-------_AA_000010");
    bE.parseTransactions();

}

    @Test
    public void testParseTransactionsWhileLoopTest2TwoLoops() throws Exception {
        dailyWriter.write("");
        dailyWriter.write("01_RealHacker-----_AA_000000");
        dailyWriter.append("00_END OF SESSION-_00_000000");
        bE.parseTransactions();

    }

    @Test
    public void testParseTransactionsWhileLoopTest2ManyLoops() throws Exception {
        dailyWriter.write("");
        dailyWriter.write("01_RealHacker-----_AA_000000");
        dailyWriter.append("02_testuser-------_FS_000000");
        dailyWriter.append("03_batman dances------------_testadmin------_010_000010");
        dailyWriter.append("04_batman dances------------_testadmin------_040_000010");
        dailyWriter.append("05_testuser-------_testadmin_------000010");
        dailyWriter.append("06_testuser-------_AA_000010");
        dailyWriter.append("00_END OF SESSION-_00_000000");
        bE.parseTransactions();

    }



/** 
* 
* Method: writeHistory() 
* 
*/ 
@Test
public void testWriteHistory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: writeLog() 
* 
*/ 
@Test
public void testWriteLog() throws Exception { 
//TODO: Test goes here... 
} 


} 
