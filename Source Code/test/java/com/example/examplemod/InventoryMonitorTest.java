package com.example.examplemod;

import junit.framework.TestCase;

public class InventoryMonitorTest extends TestCase {

	MockInventoryMonitor monitor = new MockInventoryMonitor();
	Worldfake world = new Worldfake(0); //starting the world time at 0 ticks (6am)

	public final int time_1 = 7000;
	public final int time_2 = 7333;
	public final int days_1 = 5;
	public final int days_2 = -1;


	public void testPayRent1()
	{
		assertEquals("Your rent is due!",monitor.payRentMock1());//returning the rent string
		assertFalse(monitor.payRentMock1()==null); //making sure it isn't returning an empty string.
	}

	//test if daysPassed is really being incremented in the isRentDue() method
	public void testIsRentDueDaysPassed(){

		monitor.setWorldTime(time_1);
		assertEquals(1, monitor.isRentDueDaysPassed());
		assertFalse(monitor.isRentDueDaysPassed() == -1);

		monitor.setWorldTime(time_2);
		assertEquals(1, monitor.isRentDueDaysPassed());
		assertNotSame(1, monitor.isRentDueDaysPassed());

	}

	//test the isRentDue() method
	public void testIsRendtDue(){

		monitor.setWorldTime(time_1);
		monitor.setDaysPassed(days_1);
		assertTrue(monitor.isRentDue());

		monitor.setWorldTime(time_1);
		monitor.setDaysPassed(days_2);
		assertFalse(monitor.isRentDue());

		monitor.setWorldTime(time_2);
		monitor.setDaysPassed(days_1);
		assertFalse(monitor.isRentDue());

		monitor.setWorldTime(time_2);
		monitor.setDaysPassed(days_2);
		assertFalse(monitor.isRentDue());

	}

	public void testPayRent2()
	{
		assertEquals("PAID!",monitor.payRentMock2("apple"));
		assertFalse(monitor.payRentMock2("apple")==null);

		assertEquals("1",monitor.payRentMock2("notApple")); //penalty strike 1
		assertEquals("2",monitor.payRentMock2("notApple"));//penalty strike 2
		assertEquals("GAMEOVER",monitor.payRentMock2("notApple"));//Penalty strike 3 Gameover
	}



}