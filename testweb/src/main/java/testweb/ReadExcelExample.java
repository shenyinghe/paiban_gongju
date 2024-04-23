package testweb;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ReadExcelExample {

	public static void main(String[] args) {
		readTasksFromExcel();
	}
	
	
	

	public static List<Task> readTasksFromExcel() {
		Workbook workbook=null;
		FileInputStream file=null;
		try {
			file = new FileInputStream("C:\\sh\\risk_menu_list2.xlsx");

			workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			
			List<Task> tasks = new ArrayList<>();
			
			for(int k=1;k<=sheet.getLastRowNum();k++) {
				Row row=sheet.getRow(k);
//				
//			}
//			for (Row row : sheet) {
//				
				String taskName=null;
				int workDays=0;
				String person=null;
				int sort=0;
				
				for (int i = 0; i < row.getLastCellNum(); i++) {
					Cell cell = row.getCell(i);
					switch (cell.getCellType()) {
					case STRING:
						//System.out.print(cell.getStringCellValue() + "\t");
						if(i==1) {
							taskName=cell.getStringCellValue();
						}
						if(i==3) {
							person=cell.getStringCellValue();
						}
						break;
					case NUMERIC:
						//System.out.print(cell.getNumericCellValue() + "\t");
						if(i==0) {
							sort=(int) cell.getNumericCellValue();
							
						}
						if(i==2) {
							workDays=(int) cell.getNumericCellValue();
						}
						break;
					case BOOLEAN:
						//System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					default:
						//System.out.print(" \t");
					}
				}
				System.out.println("shenyinghe1:"+sort);
				Task task=new Task(sort,taskName, workDays, "岗位A", person, new Date(), new Date());
				tasks.add(task);
				System.out.println();
			}
			Collections.sort(tasks, new Comparator<Task>() {
				@Override
				public int compare(Task t1, Task t2) {
					//return Integer.compare(t2.sort,t1.sort);
					return Integer.compare(t1.sort, t2.sort);
				}
			});
			return tasks;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}