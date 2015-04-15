package com.github.mikehepple;

import java.util.List;

public class Timeline {
	
	private final List<Duration> durations;

	public Timeline(List<Duration> durations) {
		this.durations = Collections.unmodifiableList(compressDurations(durations.sort()))
	}
	
	public List<Duration> getGaps(Date startDate, Date endDate) {
		List<Duration> gaps = []
		
		Date firstDate = durations[0].start
		Date lastDate = durations[durations.size()-1].end
		
		if (firstDate > startDate) {
			gaps += new Duration(startDate, firstDate)
		}
		if (lastDate < endDate) {
			gaps += new Duration(lastDate, endDate)
		}
		
		for (int x=0; x <= durations.size() - 2; x++) {
			gaps += new Duration(durations[x].end, durations[x+1].start)
		}
		
		return gaps.sort()
	}
	
	protected static List<Duration> compressDurations(List<Duration> durations) {
		List<Duration> surplusDurations = []
		durations.each { Duration duration ->
			Duration overlapping = durations.find { Duration candidate ->
				return candidate.contains(duration)
			}
			if (overlapping) {
				surplusDurations += duration
				overlapping.extend(duration)
			}
		}
		if (surplusDurations) {
			// Remove and compress again
			durations.removeAll(surplusDurations)
			durations = compressDurations(durations)
		}
		return durations
	}

}
