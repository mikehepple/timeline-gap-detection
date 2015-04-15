package com.github.mikehepple

import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;

@ToString
@EqualsAndHashCode
class Duration implements Comparable<Duration> {
	
	Date start;
	Date end;

	public Duration(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	public boolean contains(Date date) {
		return date > start && date < end
	}
	
	public boolean contains(Duration duration) {
		return contains(duration.start) || contains(duration.end)
	}
	
	public void extend(Date date) {
		if (date < start) {
			start = date
		} else if (date > end) {
			end = date
		} else {
			throw new IllegalArgumentException("${date} is not between ${start} and ${end}")
		}
	}
	
	public void extend(Duration duration) {
		if (duration.start < start) {
			extend(duration.start)
		} 
		if (duration.end > end) {
			extend(duration.end)
		}
	}
	
	@Override
	public int compareTo(Duration o) {
		return start.compareTo(o.start)
	}
	
}
