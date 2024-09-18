//import JustTest.Employee;
//import JustTest.EmployeeOperation;
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Level;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.rules.ExpectedException;
//import org.mockito.Mockito;
//import org.mockito.mock.*;
//
//
//public class TestMock {
//    private  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestMock.class);
//    {
//        BasicConfigurator.configure();
//    }
//    private static boolean setupDone = false;
//    private String globalName;
//    private String localName;
//
//    @Before
//    public void setUp() {
//        if(!setupDone) {
//            globalName = "Rajeev";
//            logger.log(Level.INFO,"Initial Setup");
//            setupDone = true;
//        }
//    }
//
//    @Before
//    public void doThisFirst() {
//        localName = "initial";
//        logger.log(Level.INFO,"Before Test, value" + localName);
//    }
//
//    @Test
//    public void firstTest() {
//        logger.log(Level.INFO,"First Test start value" + localName);
//        localName = "Changed";
//        logger.log(Level.INFO,"First Test end value" + localName);
//    }
//
//    @Rule
//    public ExpectedException exceptionRule = ExpectedException.none();
//
//    @Test
//    public void secondTest(){
//        exceptionRule.expect(NumberFormatException.class);
//        exceptionRule.expectMessage("For input string");
//
//        Integer.parseInt("1a");
//        //logger.log(Level.INFO,"Second Test start value" + localName);
//        //localName = null;
//        //logger.log(Level.INFO,"Second Test end value" + localName);
//        //localName.length();
//    }
//
//    @Test
//    public void employeeCheck() {
//        EmployeeOperation employeeOperation = new EmployeeOperation();
//        assertEquals(employeeOperation.get(2), Optional.empty());
//        EmployeeOperation employeeOps = Mockito.mock(EmployeeOperation.class);
//
//        Mockito.when(employeeOps.get(0)).thenReturn(Optional.of(new Employee("Kiyara", "Changat")));
//        logger.log(Level.INFO, String.format("Mock employee = %s", employeeOps.get(0).map(Employee::getName).orElse("None")));
//
//    }
//
//
//
//}
