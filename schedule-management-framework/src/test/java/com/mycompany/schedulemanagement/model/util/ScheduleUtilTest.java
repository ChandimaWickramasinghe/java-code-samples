package com.mycompany.schedulemanagement.model.util;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mycompany.schedulemanagement.exception.InvalidTaskException;
import com.mycompany.schedulemanagement.model.dto.Task;
import com.mycompany.schedulemanagement.model.util.ScheduleUtil;

/**
 * ScheduleUtilTest is the Test class for ScheduleUtil
 *
 * @author Chandima Wickramasinghe
 * @version 1.0
 * @since 2014-12-19
 */
public class ScheduleUtilTest {

	static String validFileName;
	static String invalidFileName;
	static List<Task> taskList;
	
	static {
		validFileName = ClassLoader.getSystemResource("TaskDetails.txt").getPath();
		invalidFileName = "InvalidPath";
		taskList = new ArrayList<Task>();
		taskList.add(new Task("Fisrt task", 30));
		taskList.add(new Task("Second task", 90));
		taskList.add(new Task("Third task", 60));
		taskList.add(new Task("Fourth task", 40));
		taskList.add(new Task("Fifth task", 90));
		taskList.add(new Task("Sixth task", 30));
		taskList.add(new Task("Seventh task", 45));
		taskList.add(new Task("Eighth task", 05));
		taskList.add(new Task("Nineth task", 30));
		taskList.add(new Task("Eleventh task", 60));
		
	}

	@Test
	public void testGetTaskListFromValidFile() {

		try {
			assertNotNull(ScheduleUtil.getTaskListFromFile(validFileName));
		} catch (InvalidTaskException e) {
			fail("Unexpected error occured.");
		}
	}

	@Test
	public void testGetTaskListFromInvalidFile() {
		try {
			assertNotNull(ScheduleUtil.getTaskListFromFile(invalidFileName));
			fail("Expected exception didn't occure");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.file") + invalidFileName)
					.equals(e.getMessage()));
		}
	}

	@Test
	public void testGetTotalTasksTime() {

		assertTrue(ScheduleUtil.getTotalTasksTime(taskList) == 480);
	}

	@Test
	public void testGetNumberOfPossibleTracks() {
		assertTrue(ScheduleUtil.getNumberOfPossibleTracks(taskList) == 2);
	}

	@Test
	public void testGetNextScheduledTimeValidTime() {
		try {
			assertTrue("10:00 AM".equals(ScheduleUtil.getNextScheduledTime(
					"09:30 AM", 30)));
		} catch (InvalidTaskException e) {
			fail("Unexpected exception occured.");
		}
	}

	@Test
	public void testGetNextScheduledTimeInvalidTime() {
		try {
			ScheduleUtil.getNextScheduledTime("09:30AM", 30);
			fail("Expected exception didn't occur.");
		} catch (InvalidTaskException e) {
			assertTrue((ScheduleUtil
					.getProperty("exception.message.invalid.time") + "09:30AM")
					.equals(e.getMessage()));
		}
	}

}
