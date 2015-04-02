import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * BackEnd Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 27, 2015</pre>
 */
public class BackEndTest {
    final String DAILY = "../daily.txt";
    final String USERS = "../users.txt";
    final String TICKETS = "../tickets.txt";
    final String HISTORY = "../history.txt";

    final String EXPECTEDDAILY = "../expectedDaily.txt";
    final String EXPECTEDUSERS = "../expectedUsers.txt";
    final String EXPECTEDTICKETS = "../expectedTickets.txt";
    final String EXPECTEDHISTORY = "../expectedHistory";

    BackEnd bE;
    PrintWriter dailyWriter;
    PrintWriter userWriter;
    PrintWriter ticketWriter;
    PrintWriter historyWriter;

    PrintWriter expectedDailyWriter;
    PrintWriter expectedUsersWriter;
    PrintWriter expectedTicketsWriter;
    PrintWriter expectedHistoryWriter;

    ArrayList<User> testUserArrayList;
    ArrayList<User> expectedUserArrayList;
    ArrayList<Ticket> testTicketArrayList;
    ArrayList<Ticket> expectedTicketArrayList;


    /**
     * ***************************************************************************************
     * ***************************  METHODS TO BE USED IN TESTING  ***************************
     * ***************************************************************************************
     */
    @Before
    public void before() throws Exception {
        bE = new BackEnd();
        userWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/users.txt");
        dailyWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/daily.txt");
        ticketWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/tickets.txt");
        historyWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/history.txt");
        expectedDailyWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/expectedDaily.txt");
        expectedUsersWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/expectedUsers.txt");
        expectedTicketsWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/expectedTickets.txt");
        expectedHistoryWriter = new PrintWriter("/home/peter/Desktop/Artificial Intelligence/Project/CSCI3060-2015/expectedHistory");
        testUserArrayList = new ArrayList<User>();
        testTicketArrayList = new ArrayList<Ticket>();
        expectedUserArrayList = new ArrayList<User>();
        expectedTicketArrayList = new ArrayList<Ticket>();

    }

    @After
    public void after() throws Exception {

    }

    public boolean filesEqual(String file1, String file2) {
        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String line1;
            String line2;
            while (true) // Continue while there are equal lines
            {
                line1 = reader1.readLine();
                line2 = reader2.readLine();

                if (line1 == null) // End of file 1
                {
                    return (line2 == null ? true : false); // Equal only if file 2 also ended
                }

                if (!line1.equalsIgnoreCase(line2)) // Different lines, or end of file 2
                {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in filesEqual");
        }


        return true;
    }

    public boolean compareFiles() {
        if (filesEqual(EXPECTEDTICKETS, TICKETS) && filesEqual(EXPECTEDUSERS, USERS)) return true;
        else return false;
    }

    public boolean userListsEqual(ArrayList<User> list1, ArrayList<User> list2) {
        int counter = 0;
        for (int i = list1.size(); i > 0; i--) {
            if (list1.get(i - 1).username.equals(list2.get(i - 1).username)
                    && list1.get(i - 1).type.equals(list2.get(i - 1).type)
                    && list1.get(i - 1).credit == list2.get(i - 1).credit) {
                counter++;
            }
        }
        if (counter == list1.size() && counter == list2.size()) return true;
        else return false;
    }

    public boolean ticketListsEqual(ArrayList<Ticket> list1, ArrayList<Ticket> list2) {
        int counter = 0;
        for (int i = list1.size(); i > 0; i--) {
            if (list1.get(i - 1).event.equals(list2.get(i - 1).event)
                    && list1.get(i - 1).seller.equals(list2.get(i - 1).seller)
                    && list1.get(i - 1).tickets == list2.get(i - 1).tickets
                    && list1.get(i - 1).price == list2.get(i - 1).price) {
                counter++;
            }
        }
        if (counter == list1.size() && counter == list2.size()) return true;
        else return false;
    }

    public boolean compareArrayLists(ArrayList<Ticket> list1, ArrayList<User> list2) {
        if (ticketListsEqual(list1, bE.ticks) && userListsEqual(list2, bE.users)) {
            return true;
        } else return false;
    }

    public void initArrayLists() {
        bE.users = new ArrayList<User>();
        bE.ticks = new ArrayList<Ticket>();
    }

    /**
     * ***************************************************************************************
     * **********************************TESTING MAIN*****************************************
     * ****************************************************************************************
     */
    @Test
    public void testMain() throws Exception {
        dailyWriter.write("01_RealHacker-----_AA_000100" + "\n"); //create new user
        dailyWriter.write("01_FakeHacker-----_AA_000500" + "\n"); //create new user
        dailyWriter.write("03_batman dances------------_RealHacker-----_040_000010" + "\n"); //create event
        dailyWriter.write("04_batman dances------------_RealHacker-----_003_000010" + "\n"); //buy ticket
        dailyWriter.write("05_FakeHacker-----_testadmin_------000050" + "\n"); //refund
        dailyWriter.write("06_FakeHacker-----_AA_000010" + "\n"); //add credit
        dailyWriter.write("02_RealHacker-----_AA_000000" + "\n"); //delete
        dailyWriter.write("00_END OF SESSION-_00_000000"); //end session
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedUserArrayList.clear();
        User expectedFakeHacker = new User("FakeHacker-----", "AA", 560);
        expectedUserArrayList.add(expectedFakeHacker);

        expectedTicketArrayList.clear();
        Ticket expectedBatmanDances = new Ticket("batman dances------------","RealHacker-----", 37,10);
        expectedTicketArrayList.add(expectedBatmanDances);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    /**
     * ***************************************************************************************
     * **********************************TESTING BUILDUSERS************************************
     * ****************************************************************************************
     */

    @Test
    public void testBuildUsersWhileLoopTest1ZeroLoops() throws Exception {
        userWriter.write("");
        testUserArrayList.clear();

        ArrayList<User> zeroLoopArrayList = bE.buildUsers(testUserArrayList);
        assertEquals(expectedUserArrayList, zeroLoopArrayList);
    }

    @Test
    public void testBuildUsersWhileLoopTest2OneLoops() throws Exception {
        userWriter.write("maharshall-----_AA_010000");
        userWriter.close();

        User testUser1 = new User("maharshall-----", "AA", 10000);
        expectedUserArrayList.add(testUser1);

        ArrayList<User> oneLoopArrayList = bE.buildUsers(testUserArrayList);

        assertEquals(true, userListsEqual(oneLoopArrayList, expectedUserArrayList));
    }

    @Test
    public void testBuildUsersWhileLoopTest3TwoLoops() throws Exception {
        userWriter.write("maharshall-----_AA_010000" + "\n");
        userWriter.append("alty-----------_FS_010000");
        userWriter.close();

        User testUser1 = new User("maharshall-----", "AA", 10000);
        User testUser2 = new User("alty-----------", "FS", 10000);

        expectedUserArrayList.add(testUser1);
        expectedUserArrayList.add(testUser2);

        ArrayList<User> oneLoopArrayList = bE.buildUsers(testUserArrayList);

        assertEquals(true, userListsEqual(oneLoopArrayList, expectedUserArrayList));
    }

    @Test
    public void testBuildUsersWhileLoopTest4ManyLoops() throws Exception {
        userWriter.write("maharshall-----_AA_010000" + "\n");
        userWriter.append("alty-----------_FS_010000" + "\n");
        userWriter.append("oliverqueen----_BS_010000" + "\n");
        userWriter.append("JohnCena-------_AA_999999" + "\n");
        userWriter.append("testadmin------_AA_010000" + "\n");
        userWriter.append("testuser-------_FS_010000" + "\n");
        userWriter.append("pooruser-------_BS_000001" + "\n");
        userWriter.close();

        User testUser1 = new User("maharshall-----", "AA", 10000);
        User testUser2 = new User("alty-----------", "FS", 10000);
        User testUser3 = new User("oliverqueen----", "BS", 10000);
        User testUser4 = new User("JohnCena-------", "AA", 999999);
        User testUser5 = new User("testadmin------", "AA", 10000);
        User testUser6 = new User("testuser-------", "FS", 10000);
        User testUser7 = new User("pooruser-------", "BS", 1);

        expectedUserArrayList.add(testUser1);
        expectedUserArrayList.add(testUser2);
        expectedUserArrayList.add(testUser3);
        expectedUserArrayList.add(testUser4);
        expectedUserArrayList.add(testUser5);
        expectedUserArrayList.add(testUser6);
        expectedUserArrayList.add(testUser7);

        ArrayList<User> oneLoopArrayList = bE.buildUsers(testUserArrayList);

        assertEquals(true, userListsEqual(oneLoopArrayList, expectedUserArrayList));


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
     * ***************************************************************************************
     * **********************************TESTING BUILDEVENTS************************************
     * ****************************************************************************************
     */
    @Test
    public void testBuildEventsWhileLoopTest1ZeroLoops() throws Exception {
        ticketWriter.write("");
        testTicketArrayList.clear();

        ArrayList<Ticket> zeroLoopArrayList = bE.buildEvents(testTicketArrayList);
        assertEquals(expectedTicketArrayList, zeroLoopArrayList);
    }

    @Test
    public void testBuildEventsWhileLoopTest2OneLoops() throws Exception {
        ticketWriter.write("batman dances------------_testadmin------_010_000010");
        ticketWriter.close();

        Ticket testTicket1 = new Ticket("batman dances------------", "testadmin------", 10, 10);
        expectedTicketArrayList.add(testTicket1);

        ArrayList<Ticket> oneLoopArrayList = bE.buildEvents(testTicketArrayList);

        assertEquals(true, ticketListsEqual(oneLoopArrayList, expectedTicketArrayList));
    }

    @Test
    public void testBuildEventsWhileLoopTest3TwoLoops() throws Exception {
        ticketWriter.write("batman dances------------_testadmin------_010_000010" + "\n");
        ticketWriter.append("poorman dances-----------_testadmin------_001_000010");
        ticketWriter.close();

        Ticket testTicket1 = new Ticket("batman dances------------", "testadmin------", 10, 10);
        Ticket testTicket2 = new Ticket("poorman dances-----------", "testadmin------", 1, 10);

        expectedTicketArrayList.add(testTicket1);
        expectedTicketArrayList.add(testTicket2);

        ArrayList<Ticket> oneLoopArrayList = bE.buildEvents(testTicketArrayList);

        assertEquals(true, ticketListsEqual(oneLoopArrayList, expectedTicketArrayList));

    }

    @Test
    public void testBuildEventsWhileLoopTest4ManyLoops() throws Exception {
        ticketWriter.write("batman dances------------_testadmin------_010_000010" + "\n");
        ticketWriter.append("poorman dances-----------_testadmin------_001_000010" + "\n");
        ticketWriter.append("awesome Rave-------------_peter----------_010_000020" + "\n");
        ticketWriter.append("less cool Rave-----------_peter----------_040_000040" + "\n");
        ticketWriter.append("awesome party------------_batman---------_01_000001" + "\n");
        ticketWriter.append("less cool party----------_peter----------_050_000050" + "\n");
        ticketWriter.append("party in my pants--------_peter----------_099_000000" + "\n");

        ticketWriter.close();

        Ticket testTicket1 = new Ticket("batman dances------------", "testadmin------", 10, 10);
        Ticket testTicket2 = new Ticket("poorman dances-----------", "testadmin------", 1, 10);
        Ticket testTicket3 = new Ticket("awesome Rave-------------", "peter----------", 10, 20);
        Ticket testTicket4 = new Ticket("less cool Rave-----------", "peter----------", 40, 40);
        Ticket testTicket5 = new Ticket("awesome party------------", "batman---------", 1, 1);
        Ticket testTicket6 = new Ticket("less cool party----------", "peter----------", 50, 50);
        Ticket testTicket7 = new Ticket("party in my pants--------", "peter----------", 99, 0);

        expectedTicketArrayList.add(testTicket1);
        expectedTicketArrayList.add(testTicket2);
        expectedTicketArrayList.add(testTicket3);
        expectedTicketArrayList.add(testTicket4);
        expectedTicketArrayList.add(testTicket5);
        expectedTicketArrayList.add(testTicket6);
        expectedTicketArrayList.add(testTicket7);

        ArrayList<Ticket> oneLoopArrayList = bE.buildEvents(testTicketArrayList);

        assertEquals(true, ticketListsEqual(oneLoopArrayList, expectedTicketArrayList));
    }

    /**
     * ***************************************************************************************
     * **********************************TESTING WRITE USERS************************************
     * ****************************************************************************************
     */
    @Test
    public void testWriteUsersWhileLoopTest1ZeroLoops() throws Exception {
        expectedUsersWriter.write("");
        expectedUsersWriter.close();

        testUserArrayList.clear();
        bE.writeUsers(testUserArrayList);

        assertEquals(true, filesEqual(EXPECTEDUSERS, USERS));
    }

    @Test
    public void testWriteUsersWhileLoopTest2OneLoops() throws Exception {
        expectedUsersWriter.write("jimmy_AA_100");
        expectedUsersWriter.close();

        User testUser1 = new User("jimmy", "AA", 100);
        testUserArrayList.add(testUser1);
        bE.writeUsers(testUserArrayList);

        assertEquals(true, filesEqual(EXPECTEDUSERS, USERS));
    }

    @Test
    public void testWriteUsersWhileLoopTest3TwoLoops() throws Exception {
        expectedUsersWriter.write("jimmy_AA_100" + "\n");
        expectedUsersWriter.append("bobby_FS_10");
        expectedUsersWriter.close();

        User testUser1 = new User("jimmy", "AA", 100);
        User testUser2 = new User("bobby", "FS", 10);

        testUserArrayList.add(testUser1);
        testUserArrayList.add(testUser2);
        bE.writeUsers(testUserArrayList);

        assertEquals(true, filesEqual(EXPECTEDUSERS, USERS));
    }

    @Test
    public void testWriteUsersWhileLoopTest4ManyLoops() throws Exception {
        expectedUsersWriter.write("jimmy_AA_100" + "\n");
        expectedUsersWriter.append("bobby_FS_10" + "\n");
        expectedUsersWriter.append("ricky_SS_150" + "\n");
        expectedUsersWriter.append("q_BS_110" + "\n");
        expectedUsersWriter.close();

        User testUser1 = new User("jimmy", "AA", 100);
        User testUser2 = new User("bobby", "FS", 10);
        User testUser3 = new User("ricky", "SS", 150);
        User testUser4 = new User("q", "BS", 110);

        testUserArrayList.add(testUser1);
        testUserArrayList.add(testUser2);
        testUserArrayList.add(testUser3);
        testUserArrayList.add(testUser4);
        bE.writeUsers(testUserArrayList);

        assertEquals(true, filesEqual(EXPECTEDUSERS, USERS));
    }

    /**
     * ***************************************************************************************
     * *******************************TESTING WRITE EVENTS**************************************
     * ****************************************************************************************
     */
    @Test
    public void testWriteEventWhileLoopTest1ZeroLoops() throws Exception {
        expectedTicketsWriter.write("");
        expectedTicketsWriter.close();

        testTicketArrayList.clear();
        bE.writeEvents(testTicketArrayList);

        assertEquals(true, filesEqual(EXPECTEDTICKETS, TICKETS));

    }

    @Test
    public void testWriteEventWhileLoopTest2OneLoops() throws Exception {
        expectedTicketsWriter.write("Rave_jimmy_20_20");
        expectedTicketsWriter.close();

        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        testTicketArrayList.add(testEvent1);
        bE.writeEvents(testTicketArrayList);

        assertEquals(true, filesEqual(EXPECTEDTICKETS, TICKETS));
    }

    @Test
    public void testWriteEventWhileLoopTest3TwoLoops() throws Exception {
        expectedTicketsWriter.write("Rave_jimmy_20_20" + "\n");
        expectedTicketsWriter.append("Batman Dances_Bruce_5_100");
        expectedTicketsWriter.close();

        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        Ticket testEvent2 = new Ticket("Batman Dances", "Bruce", 5, 100);

        testTicketArrayList.add(testEvent1);
        testTicketArrayList.add(testEvent2);
        bE.writeEvents(testTicketArrayList);

        assertEquals(true, filesEqual(EXPECTEDTICKETS, TICKETS));
    }

    @Test
    public void testWriteEventWhileLoopTest4ManyLoops() throws Exception {
        expectedTicketsWriter.write("Rave_jimmy_20_20" + "\n");
        expectedTicketsWriter.append("Batman Dances_Bruce_5_100" + "\n");
        expectedTicketsWriter.append("JohnCena Vs TheRock_JohnCena_45_200" + "\n");
        expectedTicketsWriter.append("Party_MyPants_99_1");
        expectedTicketsWriter.close();

        Ticket testEvent1 = new Ticket("Rave", "jimmy", 20, 20);
        Ticket testEvent2 = new Ticket("Batman Dances", "Bruce", 5, 100);
        Ticket testEvent3 = new Ticket("JohnCena Vs TheRock", "JohnCena", 45, 200);
        Ticket testEvent4 = new Ticket("Party", "MyPants", 99, 1);

        testTicketArrayList.add(testEvent1);
        testTicketArrayList.add(testEvent2);
        testTicketArrayList.add(testEvent3);
        testTicketArrayList.add(testEvent4);
        bE.writeEvents(testTicketArrayList);

        assertEquals(true, filesEqual(EXPECTEDTICKETS, TICKETS));
    }

    /**
     * ***************************************************************************************
     * ****************************TESTING PARSE TRANSACTIONS***********************************
     * ****************************************************************************************
     */

    @Test
    public void testParseTransactionsCase0() throws Exception {
        dailyWriter.write("00_END OF SESSION-_00_000000");
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test
    public void testParseTransactionsCase1() throws Exception {
        dailyWriter.write("01_realHacker-----_AA_000000");
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        User testUser1 = new User("realHacker-----", "AA", 0);
        expectedUserArrayList.add(testUser1);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test
    public void testParseTransactionsCase2() throws Exception {
        dailyWriter.write("02_realHacker-----_AA_000000");
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test
    public void testParseTransactionsCase3() throws Exception {
        dailyWriter.write("03_batman dances------------_testadmin------_010_000010");
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        Ticket testEvent1 = new Ticket("batman dances------------", "testadmin------", 10, 10);
        expectedTicketArrayList.add(testEvent1);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test //not working
    public void testParseTransactionsCase4() throws Exception {
        dailyWriter.write("04_batman dances------------_testadmin------_002_000010");
        dailyWriter.close();

        initArrayLists();

        User testUser1 = new User("testadmin------", "AA", 500);
        bE.users.add(testUser1);

        Ticket testEvent1 = new Ticket("batman dances------------", "testadmin------", 40, 10);
        bE.ticks.add(testEvent1);

        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        Ticket expectedEvent1 = new Ticket("batman dances------------", "testadmin------", 8, 10);

        expectedTicketArrayList.add(expectedEvent1);
        expectedUserArrayList.add(testUser1);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test //error in daily file input
    public void testParseTransactionsCase5() throws Exception {
        dailyWriter.write("05_testuser-------_testadmin_------000010");
        dailyWriter.close();

        initArrayLists();

        User testUser1 = new User("testadmin------", "AA", 500);
        bE.users.add(testUser1);

        User testUser2 = new User("testuser-------", "FS", 500);
        bE.users.add(testUser2);

        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        User expectedUser1 = new User("testadmin------", "AA", 490);
        expectedUserArrayList.add(expectedUser1);

        User expectedUser2 = new User("testuser-------", "FS", 510);
        expectedUserArrayList.add(expectedUser2);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test
    public void testParseTransactionsCase6() throws Exception {
        dailyWriter.write("06_testuser-------_AA_000010");
        dailyWriter.close();

        initArrayLists();

        User testUser1 = new User("testuser-------", "AA", 10);
        bE.users.add(testUser1);

        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        User expectedUser1 = new User("testuser-------", "AA", 20);
        expectedUserArrayList.add(expectedUser1);

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    @Test
    public void testParseTransactionsDefaultCase() throws Exception {
        dailyWriter.write("07_testuser-------_AA_000010");
        dailyWriter.close();

        initArrayLists();
        bE.parseTransactions();

        expectedTicketArrayList.clear();
        expectedUserArrayList.clear();

        assertEquals(true, compareArrayLists(expectedTicketArrayList, expectedUserArrayList));
    }

    /**
     * ***************************************************************************************
     * *******************************TESTING WRITE HISTORY*************************************
     * ****************************************************************************************
     */
    @Test
    public void testWriteHistoryWhileLoopTest1ZeroLoops() throws Exception {
        expectedHistoryWriter.write("");
        expectedHistoryWriter.close();

        expectedDailyWriter.write("");
        expectedDailyWriter.close();

        dailyWriter.write("");
        dailyWriter.close();

        bE.writeHistory();

        assertEquals(true, filesEqual(EXPECTEDHISTORY, HISTORY) && filesEqual(EXPECTEDDAILY, DAILY));
    }

    @Test
    public void testWriteHistoryWhileLoopTest2OneLoops() throws Exception {
        expectedHistoryWriter.write("01_RealHacker-----_AA_000000");
        expectedHistoryWriter.close();

        expectedDailyWriter.write("");
        expectedDailyWriter.close();

        dailyWriter.write("01_RealHacker-----_AA_000000");
        dailyWriter.close();

        bE.writeHistory();

        assertEquals(true, filesEqual(EXPECTEDHISTORY, HISTORY) && filesEqual(EXPECTEDDAILY, DAILY));
    }

    @Test
    public void testWriteHistoryWhileLoopTest3TwoLoops() throws Exception {
        expectedHistoryWriter.write("01_RealHacker-----_AA_000000" + "\n");
        expectedHistoryWriter.write("02_testuser-------_FS_000000");
        expectedHistoryWriter.close();

        expectedDailyWriter.write("");
        expectedDailyWriter.close();

        dailyWriter.write("01_RealHacker-----_AA_000000" + "\n");
        dailyWriter.write("02_testuser-------_FS_000000");
        dailyWriter.close();

        bE.writeHistory();

        assertEquals(true, filesEqual(EXPECTEDHISTORY, HISTORY) && filesEqual(EXPECTEDDAILY, DAILY));
    }

    @Test
    public void testWriteHistoryWhileLoopTest4ManyLoops() throws Exception {
        expectedHistoryWriter.write("01_RealHacker-----_AA_000000" + "\n");
        expectedHistoryWriter.write("02_testuser-------_FS_000000" + "\n");
        expectedHistoryWriter.write("03_batman dances------------_testadmin------_010_000010" + "\n");
        expectedHistoryWriter.write("04_batman dances------------_testadmin------_040_000010" + "\n");
        expectedHistoryWriter.write("05_testuser-------_testadmin_------000010" + "\n");
        expectedHistoryWriter.write("00_END OF SESSION-_00_000000");
        expectedHistoryWriter.close();

        expectedDailyWriter.write("");
        expectedDailyWriter.close();

        dailyWriter.write("01_RealHacker-----_AA_000000" + "\n");
        dailyWriter.write("02_testuser-------_FS_000000" + "\n");
        dailyWriter.write("03_batman dances------------_testadmin------_010_000010" + "\n");
        dailyWriter.write("04_batman dances------------_testadmin------_040_000010" + "\n");
        dailyWriter.write("05_testuser-------_testadmin_------000010" + "\n");
        dailyWriter.write("00_END OF SESSION-_00_000000");
        dailyWriter.close();

        bE.writeHistory();

        assertEquals(true, filesEqual(EXPECTEDHISTORY, HISTORY) && filesEqual(EXPECTEDDAILY, DAILY));
    }

    /**
     * Method: writeLog()
     */
    @Test
    public void testWriteLog() throws Exception {
//TODO: Test goes here... 
    }


} 
