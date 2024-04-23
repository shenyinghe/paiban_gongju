package testweb;

import java.util.*;
import java.text.SimpleDateFormat;

class Task {
	int sort;
	String taskName;
	int estimatedTime;
	String position;
	String personInCharge;
	Date startDate;
	Date endDate;

	public Task(int sort,String taskName, int estimatedTime, String position, String personInCharge, Date startDate,
			Date endDate) {
		this.sort=sort;
		this.taskName = taskName;
		this.estimatedTime = estimatedTime;
		this.position = position;
		this.personInCharge = personInCharge;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "序列号："+sort+"，任务名：" + taskName + "，任务预计用时：" + estimatedTime + "，岗位：" + position + "，负责人：" + personInCharge + "，开始日期："
				+(startDate==null?null:sdf.format(startDate)) + "，结束日期：" +(endDate==null?null:sdf.format(endDate));
	}
}