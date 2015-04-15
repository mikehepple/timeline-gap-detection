package com.github.mikehepple;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

public class TimelineTest {
	
	static Date d1, d2, d3, d4, d5, d6
	
	@BeforeClass
	public static void createDates() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		d1 = df.parse("2000-01-01 00:00:00")
		d2 = df.parse("2000-01-02 00:00:00")
		d3 = df.parse("2000-01-03 00:00:00")
		d4 = df.parse("2000-01-04 00:00:00")
		d5 = df.parse("2000-01-05 00:00:00")
		d6 = df.parse("2000-01-06 00:00:00")
	}

	@Test
	public void test() {
		Timeline timeline = new Timeline([
			new Duration(d2, d3),
			new Duration(d4, d5)
		])
		List<Duration> gaps = timeline.getGaps(d1, d6)
		
		assertEquals(3, gaps.size())
		assertEquals(new Duration(d1, d2), gaps[0])
		assertEquals(new Duration(d3, d4), gaps[1])
		assertEquals(new Duration(d5, d6), gaps[2])
	}
	
	@Test
	public void testCompress() {
		Duration duration1 = new Duration(d1, d3)
		Duration duration2 = new Duration(d2, d4)
		Timeline timeline = new Timeline([duration1, duration2])
		
		List<Duration> durations = timeline.durations
		assertEquals(1, durations.size())
		assertEquals(new Duration(d1, d4), durations[0])
	}

}
