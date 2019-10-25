package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.dao.ReimbursementDAOImpl;
import com.revature.pojo.Reimbursement;
import com.revature.util.LoggerUtil;

@RunWith(MockitoJUnitRunner.class)
public class ReimbursementDAOTest {
	private ReimbursementDAOImpl rDAO = new ReimbursementDAOImpl();
	private Reimbursement R = new Reimbursement();
	
	@Mock
	private Connection conn;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void insertReimb() {
		//public Reimbursement(String requestorEmail, String location, String type, String description, String format,
		//LocalDate date, double originalAmount, double tentativeAmount)
		String email = "john.smith@gmail.com";
		String loc = "New Yoke";
		String type = "Course";
		String descr = "description!";
		String format = "letter";
		LocalDate d = LocalDate.now();
		double oAmount = 99.99;
		double tAmount = 50.00;

		Reimbursement r = new Reimbursement(email, loc, type, descr, format, d , oAmount, tAmount );
		assertEquals(true, rDAO.insert(r) );
	}
	
	@Test
	public void fetchSingleReimb() {
		String email = "john.smith@gmail.com";
		String loc = "New Yoke";
		String type = "Course";
		String descr = "description!";
		String format = "letter";
		LocalDate d = LocalDate.now();
		double oAmount = 99.99;
		double tAmount = 50.00;
		Reimbursement r = new Reimbursement(email, loc, type, descr, format, d , oAmount, tAmount );
		ArrayList<Reimbursement> rList = new ArrayList<Reimbursement>();
		rList.add(r);
		assertEquals(rList, rDAO.getR(email) );
	}
	
	@Test
	public void fetAllReimb() {
		String email = "john.smith@gmail.com";
		String loc = "New Yoke";
		String type = "Course";
		String descr = "description!";
		String format = "letter";
		LocalDate d = LocalDate.now();
		double oAmount = 99.99;
		double tAmount = 50.00;
		Reimbursement r = new Reimbursement(email, loc, type, descr, format, d , oAmount, tAmount );
		ArrayList<Reimbursement> rList = new ArrayList<Reimbursement>();
		rList.add(r);
		assertEquals(rList, rDAO.getR(null) );
	}
	
	@Test
	public void checkForAllAcceptFields() {
		int PASS_ID = 1;
		assertEquals(true, rDAO.isRequestAcceptedByAll(PASS_ID) );
		int FAIL_ID = 2;
		assertEquals(false, rDAO.isRequestAcceptedByAll(FAIL_ID) );

	}
	
}
