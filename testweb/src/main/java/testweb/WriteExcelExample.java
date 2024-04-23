package testweb;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class WriteExcelExample {
	
	
	public static void writeExcel(List<Task> tasks) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0;i<tasks.size();i++) {
			Task task=tasks.get(i);
			if(i==0) {
				Row headerRow = sheet.createRow(i);
				headerRow.createCell(0).setCellValue("序列");
				headerRow.createCell(1).setCellValue("任务名");
				headerRow.createCell(2).setCellValue("预估人天");
				headerRow.createCell(3).setCellValue("负责人");
				headerRow.createCell(4).setCellValue("开始日期");
				headerRow.createCell(5).setCellValue("结束日期");
			}
			
			Row headerRow = sheet.createRow(i+1);
			headerRow.createCell(0).setCellValue(task.sort);
			headerRow.createCell(1).setCellValue(task.taskName);
			headerRow.createCell(2).setCellValue(task.estimatedTime);
			headerRow.createCell(3).setCellValue(task.personInCharge);
			headerRow.createCell(4).setCellValue(task.startDate==null?"--":sdf.format(task.startDate));
			headerRow.createCell(5).setCellValue(task.endDate==null?"--":sdf.format(task.endDate));
			
		}
		try (FileOutputStream fileOut = new FileOutputStream("C:\\sh\\risk_menu_list2_output.xlsx")) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		Row headerRow = sheet.createRow(0);
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("Name");

		Row dataRow = sheet.createRow(1);
		Cell dataCell = dataRow.createCell(0);
		dataCell.setCellValue("John Doe");

		try (FileOutputStream fileOut = new FileOutputStream("C:\\sh\\risk_menu_list2_output.xlsx")) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}