package testweb;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoSchedule {

	public static void main(String[] args) {
		List<Task> tasks = ReadExcelExample.readTasksFromExcel();

//		tasks=new ArrayList<Task>();
//		tasks.add(new Task("Task1", 3, "岗位A", "张三", new Date(), new Date()));
//		tasks.add(new Task("Task7", 8, "岗位A", "张三", new Date(), new Date()));
//		tasks.add(new Task("Task2", 5, "岗位A", "张三", new Date(), new Date()));
//		tasks.add(new Task("Task3", 2, "岗位A", "李四", new Date(), new Date()));
//		tasks.add(new Task("Task8", 2, "岗位A", "李四", new Date(), new Date()));
//		tasks.add(new Task("Task9", 2, "岗位A", "李四", new Date(), new Date()));
//		tasks.add(new Task("Task10", 2, "岗位A", "李四", new Date(), new Date()));
//		tasks.add(new Task("Task11", 2, "岗位A", "李四", new Date(), new Date()));
//		tasks.add(new Task("Task12", 2, "岗位A", "李四", new Date(), new Date()));
		

		autoScheduleTasks(tasks);

		for (Task task : tasks) {
			System.out.println(task);
		}
		
		WriteExcelExample.writeExcel(tasks);
		System.out.println(1);
	}
	
	
	public static Date getStartDate(String person,Map<String, Object> lastWorkDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		Date yesterdayDate = calendar.getTime();
		Date lastworkDate=lastWorkDate.get(person)==null?yesterdayDate:(Date) lastWorkDate.get(person);
		
		
		return lastworkDate;
	}

	public static void autoScheduleTasks(List<Task> tasks) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		//Date currentDate = new Date();
		
		Map<String, Object> lastWorkDate=new HashMap<String, Object>();
		
		//工作日清单
		LocalDate startDate = LocalDate.of(2024, 4, 22);
		LocalDate endDate = LocalDate.of(2024, 12, 31);
		List<LocalDate> workDayList = WorkDayList.getWorkDayList(startDate, endDate);

		for (Task task : tasks) {
			boolean isOverlap = false;
			if(task.estimatedTime==0) {
				task.startDate=null;
				task.endDate=null;
				continue;
			}
			for (Task t : tasks) {
				// && !t.startDate.after(task.endDate) && !task.startDate.after(t.endDate)
				if (!task.personInCharge.equals(t.personInCharge)) {
					isOverlap = true;
					break;
				}
			}
			
			if(!isOverlap) {
				//如果没命中
				//currentDate=getStartDate(task.personInCharge, lastWorkDate);
				isOverlap = true;
			}

			if (isOverlap) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(getStartDate(task.personInCharge, lastWorkDate));
				
				
				//循环判断是否是工作日
				while(true) {
					calendar.add(Calendar.DATE, 1);
					LocalDate localStartDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					if(workDayList.contains(localStartDate)) {
						task.startDate = calendar.getTime();
						break;
					}else {
						continue;
					}
				}
				
				
				if(task.estimatedTime==1) {
					//calendar.add(Calendar.DATE, 1);
					LocalDate localEndDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					if(workDayList.contains(localEndDate)) {
						task.endDate = calendar.getTime();
						lastWorkDate.put(task.personInCharge, task.endDate);
					}
				}
				for(int i=1;i<task.estimatedTime;i++) {
					while(true) {
						calendar.add(Calendar.DATE, 1);
						LocalDate localEndDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						
						if(workDayList.contains(localEndDate)) {
							task.endDate = calendar.getTime();
							lastWorkDate.put(task.personInCharge, task.endDate);
							break;
						}else {
							continue;
						}
					}
				}
			}
		}
	}
}