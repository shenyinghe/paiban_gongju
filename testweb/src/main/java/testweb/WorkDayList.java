package testweb;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkDayList {

	private static final List<LocalDate> holidayList = new ArrayList<>();
	
	private static final List<LocalDate> hardWorkdayList = new ArrayList<>();
	

	static {
		// 添加法定节假日
		holidayList.add(LocalDate.of(2024, 5, 1)); // Labour Day
		holidayList.add(LocalDate.of(2024, 5, 2)); // Labour Day
		holidayList.add(LocalDate.of(2024, 5, 3)); // Labour Day
		holidayList.add(LocalDate.of(2024, 5, 4)); // Labour Day
		holidayList.add(LocalDate.of(2024, 5, 5)); // Labour Day
		holidayList.add(LocalDate.of(2024, 6, 8)); // Labour Day
		holidayList.add(LocalDate.of(2024, 6, 9)); // Labour Day
		holidayList.add(LocalDate.of(2024, 6, 10)); // Labour Day
		holidayList.add(LocalDate.of(2024, 9, 15)); // Labour Day
		holidayList.add(LocalDate.of(2024, 9, 16)); // Labour Day
		holidayList.add(LocalDate.of(2024, 9, 17)); // Labour Day
		
	}
	
	static {
		// 添加法定节假日的调整工作日（原来是周末，但是变为上班日的）
		hardWorkdayList.add(LocalDate.of(2024, 4, 28));
		hardWorkdayList.add(LocalDate.of(2024, 5, 11));
		hardWorkdayList.add(LocalDate.of(2024, 9, 14));
		hardWorkdayList.add(LocalDate.of(2024, 9, 29));
		
	}
	

	public static void main(String[] args) {
		LocalDate startDate = LocalDate.of(2024, 4, 22);
		LocalDate endDate = LocalDate.of(2024, 12, 31);

		List<LocalDate> workDayList = getWorkDayList(startDate, endDate);
		for (LocalDate date : workDayList) {
			System.out.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
	}

	public static List<LocalDate> getWorkDayList(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> workDayList = new ArrayList<>();
		LocalDate currentDate = startDate;

		while (!currentDate.isAfter(endDate)) {
			if (isHardWordday(currentDate) || (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY
					&& !isHoliday(currentDate))) {
				workDayList.add(currentDate);
			}
			currentDate = currentDate.plusDays(1);
		}
		return workDayList;
	}
	
	public static boolean isHardWordday(LocalDate date) {
		for (LocalDate hard : hardWorkdayList) {
			if (date.isEqual(hard)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean isHoliday(LocalDate date) {
		for (LocalDate holiday : holidayList) {
			if (date.isEqual(holiday)) {
				return true;
			}
		}
		return false;
	}
}