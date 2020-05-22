import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CSVCompare {
	
	public static String[] CSVCompared(String L2FilePath,String SACCRFilePath,String SourceSystem) throws IOException {
		
		try {
			System.out.println("Started to Working....!");
			int lastIndex = L2FilePath.lastIndexOf('.');
			String format = L2FilePath.substring(lastIndex);
			
			if(format.equalsIgnoreCase("XML")) {
				L2FilePath = ConvertXMLtoCSV.ConvertCSV(L2FilePath);
			}
			
			lastIndex = SACCRFilePath.lastIndexOf('.');
			format = SACCRFilePath.substring(lastIndex);
			
			if(format.equalsIgnoreCase("XML")) {
				SACCRFilePath = ConvertXMLtoCSV.ConvertCSV(SACCRFilePath);
			}
			MapingSheet.mappingPath(L2FilePath,SACCRFilePath,SourceSystem);
			//String mappingPath = "\\Mapping_Documet.csv";
			String newMappingPath = "src/mapping.csv";
			String L2ARMLFileName = L2FilePath.substring(L2FilePath.lastIndexOf("\\")+1);
			String SACCRFileName =SACCRFilePath.substring(SACCRFilePath.lastIndexOf("\\")+1);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet1 = workbook.createSheet("Summary");
			sheet1.autoSizeColumn(0);
			sheet1.autoSizeColumn(1);
			XSSFSheet sheet2 = workbook.createSheet(SACCRFileName);
			XSSFSheet sheet3 = workbook.createSheet(L2ARMLFileName);
			XSSFSheet sheet4 = workbook.createSheet("MisMatched Attribute");
			XSSFSheet sheet5 = workbook.createSheet("TestExecution_Report");
			XSSFSheet sheet6 = workbook.createSheet("Sample");
			//Header Font Style
			
			XSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short)10);
			font.setFontName("Arial");
			font.setColor(IndexedColors.WHITE.getIndex());
			font.setBold(true);
			font.setItalic(false);
			
			XSSFFont sheet1font = workbook.createFont();
			sheet1font.setFontHeightInPoints((short)14);
			sheet1font.setFontName("Calibri");
			sheet1font.setColor(IndexedColors.DARK_BLUE.getIndex());
			sheet1font.setBold(true);
			sheet1font.setItalic(false);
			
			CellStyle sheet1Style = workbook.createCellStyle();
			sheet1Style.setFont(sheet1font);
			
			XSSFFont sheet1passfont = workbook.createFont();
			sheet1passfont.setFontHeightInPoints((short)14);
			sheet1passfont.setFontName("Calibri");
			sheet1passfont.setColor(IndexedColors.DARK_BLUE.getIndex());
			sheet1passfont.setBold(true);
			sheet1passfont.setItalic(false);
			
			CellStyle sheet1passStyle = workbook.createCellStyle();
			sheet1passStyle.setFont(sheet1passfont);
			
			XSSFFont sheet1failfont = workbook.createFont();
			sheet1failfont.setFontHeightInPoints((short)14);
			sheet1failfont.setFontName("Calibri");
			sheet1failfont.setColor(IndexedColors.DARK_BLUE.getIndex());
			sheet1failfont.setBold(true);
			sheet1failfont.setItalic(false);
			
			CellStyle sheet1failStyle = workbook.createCellStyle();
			sheet1failStyle.setFont(sheet1failfont);
			
			XSSFFont sheet1font1 = workbook.createFont();
			sheet1font1.setFontHeightInPoints((short)14);
			sheet1font1.setFontName("Calibri");
			sheet1font1.setColor(IndexedColors.DARK_BLUE.getIndex());
			sheet1font1.setBold(true);
			sheet1font1.setItalic(false);
			
			CellStyle sheet1Style1 = workbook.createCellStyle();
			sheet1Style1.setFillBackgroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
			sheet1Style1.setFillPattern(FillPatternType.DIAMONDS);
			sheet1Style1.setFont(sheet1font1);
			
			CellStyle style = workbook.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			style.setFillPattern(FillPatternType.SPARSE_DOTS);
			style.setBorderBottom(BorderStyle.THICK);
			style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
			style.setBorderLeft(BorderStyle.THICK);
			style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
			style.setBorderRight(BorderStyle.THICK);
			style.setRightBorderColor(IndexedColors.BLUE.getIndex());
			style.setBorderTop(BorderStyle.THICK);
			style.setTopBorderColor(IndexedColors.BLUE.getIndex());
			style.setFont(font);
			
			XSSFFont failedFont = workbook.createFont();
			failedFont.setFontHeightInPoints((short)10);
			failedFont.setFontName("Arial");
			failedFont.setColor(IndexedColors.DARK_BLUE.getIndex());
			failedFont.setBold(true);
			failedFont.setItalic(false);
			
			CellStyle failedStyle = workbook.createCellStyle();
			failedStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
			failedStyle.setFillPattern(FillPatternType.LEAST_DOTS);
			failedStyle.setFont(failedFont);
			
			XSSFFont summarydFont = workbook.createFont();
			summarydFont.setFontHeightInPoints((short)10);
			summarydFont.setFontName("Arial");
			summarydFont.setColor(IndexedColors.DARK_BLUE.getIndex());
			summarydFont.setBold(true);
			summarydFont.setItalic(false);
			
			CellStyle summaryStyle = workbook.createCellStyle();
			summaryStyle.setFont(summarydFont);
			
			XSSFFont headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short)14);
			headerFont.setFontName("Calibri");
			headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
			headerFont.setBold(true);
			headerFont.setItalic(false);
			
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			
			Row rowForHeader = sheet2.createRow(sheet2.getLastRowNum()+1);
			Cell celll = rowForHeader.createCell(0);
			celll.setCellValue("Trade ID present in "+L2ARMLFileName+" but not present in "+SACCRFileName);
			celll.setCellStyle(headerStyle);
			rowForHeader = sheet2.createRow(sheet2.getLastRowNum()+1);
			celll = rowForHeader.createCell(0);
			celll.setCellValue("Trade ID");
			celll.setCellStyle(style);
			celll = rowForHeader.createCell(1);
			celll.setCellValue("Comments");
			celll.setCellStyle(style);
			
			rowForHeader = sheet3.createRow(sheet3.getLastRowNum()+1);
			celll = rowForHeader.createCell(0);
			celll.setCellValue("Trade ID present in "+SACCRFileName+" but not present in "+L2ARMLFileName);
			celll.setCellStyle(headerStyle);
			rowForHeader = sheet3.createRow(sheet3.getLastRowNum()+1);
			celll = rowForHeader.createCell(0);
			celll.setCellValue("Trade ID");
			celll.setCellStyle(style);
			celll = rowForHeader.createCell(1);
			celll.setCellValue("Comments");
			celll.setCellStyle(style);
			
			
			//sheet4
			rowForHeader=sheet4.createRow(sheet4.getLastRowNum()+1);
			String[] sheet4Header=("Trade ID,L2 Attribute Name,Value in L2 ARML,SACCR Attribute Name,Value in SACCR,Comments").split(",");
			Row rows=sheet4.createRow(sheet4.getLastRowNum());
			int columnCounts=0;
			int passCount=0,failCount=0;
			for(String field: sheet4Header)
			{
				celll=rows.createCell(columnCounts++);
				celll.setCellValue((String)field);
				celll.setCellStyle(style);
				
			}
			rowForHeader=sheet5.createRow(sheet5.getLastRowNum()+1);
			celll=rowForHeader.createCell(0);
			celll.setCellValue("Trade ID");
			celll.setCellStyle(style);
			celll=rowForHeader.createCell(1);
			celll.setCellValue("Result");
			celll.setCellStyle(style);
			celll=rowForHeader.createCell(2);
			celll.setCellValue("Reason");
			celll.setCellStyle(style);
			int saccrCount=0,L2ARMLCount=0,attributesCount=0;
			System.out.println(L2FilePath);
			System.out.println(SACCRFilePath);
			System.out.println(SourceSystem);
			lastIndex = L2FilePath.lastIndexOf('\\');
			String path=L2FilePath.substring(0,lastIndex+1);
			BufferedWriter result=new BufferedWriter(new FileWriter(new File(path+"result_Data.csv")));
			

			BufferedReader mappingFile = new BufferedReader(new FileReader(newMappingPath));
			BufferedReader L2File = new BufferedReader(new FileReader(L2FilePath));
			BufferedReader SACCRFile = new BufferedReader(new FileReader(SACCRFilePath));
			String rowByRow = mappingFile.readLine();
			String[] L2ARMLMappingattributes = null, SACCRMappingattributes=null;
			String[] L2ARMLattributes=null, SACCRattributes=null;
			Map<String,Map<String,String>> L2ARMLdata = new LinkedHashMap<>(),SACCRdata = new LinkedHashMap<>();
			result.write("Trade ID, L2 Attribute Name, Value in L2 ARML, SACCR Attribute Name, Value in SACCR,Comments\n");
			while(rowByRow != null)
			{
				rowByRow = rowByRow.toLowerCase();
				String[] temp = rowByRow.split(",");
				if(temp.length>0 && temp[0].equalsIgnoreCase(SourceSystem))
				{
					if(temp[1].contains("l2"))
					{
						L2ARMLMappingattributes=temp;
					}
					else
					{
						SACCRMappingattributes=temp;
					}
				}
				rowByRow = mappingFile.readLine();
			}
				mappingFile.close();
				System.out.println("length 1:"+L2ARMLMappingattributes.length);
				String rowByRowL2 = L2File.readLine();
				rowByRowL2 = rowByRowL2.toLowerCase();
				L2ARMLattributes=rowByRowL2.split(",");
				rowByRowL2 = L2File.readLine();
				while(rowByRowL2!=null)
				{
					if(rowByRowL2.length()<L2ARMLattributes.length)
					{
						break;
					}
				
			String[] temp=rowByRowL2.split(",");
			String tradeId=null;
				Map<String,String> tempMap = new LinkedHashMap<>();
				for (int i =0;i<L2ARMLattributes.length;i++)
				{
					if(L2ARMLattributes[i].equalsIgnoreCase("trade id") || L2ARMLattributes[i].equalsIgnoreCase("trade_id"))
					{
						tradeId=temp[i];
					}
					if(temp.length>i)
					{
						tempMap.put(L2ARMLattributes[i], temp[i]);
					}
					else
					{
						tempMap.put(L2ARMLattributes[i],"");		
					}
				}
				rowByRowL2 = L2File.readLine();
				L2ARMLdata.put(tradeId,tempMap);
				}
			System.out.println("L2 ARML done");
			String rowByRowSACCR = SACCRFile.readLine();
			System.out.println("rowByRowSACCR"+SACCRFile);
			rowByRowSACCR= rowByRowSACCR.toLowerCase();
			SACCRattributes = rowByRowSACCR.split(",");
			rowByRowSACCR = SACCRFile.readLine();
			while(rowByRowSACCR!=null)
			{
				if(rowByRowSACCR.length()<SACCRattributes.length)
				{
					break;
				}
			String[] temp=rowByRowSACCR.split(",");
			String tradeId = null;
			Map<String,String> tempMap = new LinkedHashMap<>();
			for(int i=0;i<SACCRattributes.length;i++)
			{
				if(SACCRattributes[i].equalsIgnoreCase("trade id") || SACCRattributes[i].equalsIgnoreCase("trade_id"))
				{
					tradeId=temp[i];
				}
				if(temp.length>i)
				{
					tempMap.put(SACCRattributes[i],temp[i]);
				}
				else
				{
					tempMap.put(SACCRattributes[i],"");
			}
				}
				rowByRowSACCR=SACCRFile.readLine();
				SACCRdata.put(tradeId,tempMap);
			}
			System.out.println("SACCR done");
			for(String tradeID: L2ARMLdata.keySet())
			{
				String[] sheet2Result=null;
				String[] sheet4Result=null;
				if(SACCRdata.containsKey(tradeID))
				{
					Map<String,String> L2Map = L2ARMLdata.get(tradeID);
					Map<String,String> SACCRMap = SACCRdata.get(tradeID);
					int flag=0;
					int count=0;
					//need to check the below loop
					for(int i=3;i<L2ARMLMappingattributes.length;i++) 
					{
						if(L2Map.containsKey(L2ARMLMappingattributes[i]) && SACCRMap.containsKey(SACCRMappingattributes[i]))
						{
							if(L2Map.get(L2ARMLMappingattributes[i]).equalsIgnoreCase(SACCRMap.get(SACCRMappingattributes[i])))
							{}
							else
							{
								flag=1;
								count++;
								sheet4Result= (tradeID+","+L2ARMLMappingattributes[i]+","+L2Map.get(L2ARMLMappingattributes[i])+","+SACCRMappingattributes[i]+","+SACCRMap.get(SACCRMappingattributes[i])+", The Value is not Matching\n").split(",");
								Row row=sheet4.createRow(sheet4.getLastRowNum()+1);
								int columnCount=0;
								for(String field:sheet4Result)
								{
									Cell cell=row.createCell(columnCount++);
									cell.setCellValue((String)field);
								}
								result.write(tradeID+","+L2ARMLMappingattributes[i]+","+L2Map.get(L2ARMLMappingattributes[i])+","+SACCRMappingattributes[i]+","+SACCRMap.get(SACCRMappingattributes[i])+", The value is not matching\n");
				attributesCount++;
							}
						}
						else
						{
							flag=1;
							count++;
							sheet4Result=(tradeID+","+L2ARMLMappingattributes[i]+",,,, This attribute is not present\n").split(",");
							Row row=sheet4.createRow(sheet4.getLastRowNum()+1);
							int columnCount=0;
							for(String field: sheet4Result)
							{
								Cell cell=row.createCell(columnCount++);
								cell.setCellValue((String) field);
							}
						result.write(tradeID+","+L2ARMLMappingattributes[i]+",,,,This attribute is not present\n");
						attributesCount++;
						}
					}
						if(flag==0)
						{
							Row row = sheet5.createRow(sheet5.getLastRowNum()+1);
							Cell cell=row.createCell(0);
							cell.setCellValue(tradeID);
							cell=row.createCell(1);
							cell.setCellValue("PASSED");
							passCount++;
						}
						else
						{
							Row row = sheet5.createRow(sheet5.getLastRowNum()+1);
							Cell cell=row.createCell(0);
							cell.setCellValue(tradeID);
							cell.setCellStyle(failedStyle);
							cell=row.createCell(1);
							

///
			
			
			cell.setCellValue("FAILED");
			cell.setCellStyle(failedStyle);
			cell=row.createCell(2);
			cell.setCellValue("Number of Failed Attributes : "+count);
			cell.setCellStyle(failedStyle);
			failCount++; 
			}
			}
			else
			{
				sheet2Result = (tradeID+", Trade ID is not present in SACCR file \n").split(",");
				Row row=sheet2.createRow(sheet2.getLastRowNum()+1);
				int columnCount =0;
				Cell cell =row.createCell(columnCount++);
				cell.setCellValue(sheet2Result[0]);
				cell=row.createCell(columnCount++);
				cell.setCellValue((String) sheet2Result[1]);
				result.write(tradeID+",,,,,Trade ID is not present in SACCR file \n");
				saccrCount++;
				row=sheet5.createRow(sheet5.getLastRowNum()+1);
				cell=row.createCell(0);
				cell.setCellValue(tradeID);
				cell.setCellStyle(failedStyle);
				cell=row.createCell(1);
				cell.setCellValue("FAILED");
				cell.setCellStyle(failedStyle);
				cell=row.createCell(2);
				cell.setCellValue("Trade ID is not present in SACCR file");
				cell.setCellStyle(failedStyle);
				failCount++;
			}
			}
			for(String tradeID: SACCRdata.keySet())
			{
				if(!L2ARMLdata.containsKey(tradeID))
				{
					String[] sheet3Result=null;
					sheet3Result = (tradeID+",, Trade ID is not present in L2ARML file \n").split(",");
					Row row =sheet3.createRow(sheet3.getLastRowNum()+1);
					int columnCount =0;
					Cell cell = row.createCell(columnCount++);
					cell.setCellValue(sheet3Result[0]);
					cell=row.createCell(columnCount++);
					cell.setCellValue((String) sheet3Result[1]);
					result.write(tradeID+",,,,,Trade ID is not present in L2ARML file \n");
					L2ARMLCount++;
					row=sheet5.createRow(sheet5.getLastRowNum()+1);
					cell=row.createCell(0);
					cell.setCellValue(tradeID);
					cell=row.createCell(1);
					cell.setCellValue("FAILED");
					cell.setCellStyle(failedStyle);
					cell=row.createCell(2);
					cell.setCellValue(" Trade ID is not present in L2ARML file");
					failCount++;
				}
			}
			result.write("no of trades in L2ARML : "+L2ARMLdata.size());
			result.write("no of trades in SACCR : "+SACCRdata.size());
			Row rowFoSummary = sheet1.createRow(2);
			Cell cells=rowFoSummary.createCell(0);
			cells.setCellStyle(sheet1Style);
			rowFoSummary = sheet1.createRow(3);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("Test Scenarios");
			cells.setCellStyle(sheet1Style1);
			rowFoSummary = sheet1.createRow(4);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("File comparision between " + L2ARMLFileName +"AND " + SACCRFileName);
			rowFoSummary= sheet1.createRow(5);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("File and source system Details:");
			cells.setCellStyle(sheet1Style1);
			rowFoSummary= sheet1.createRow(6);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("Source System : "+SourceSystem);
			rowFoSummary= sheet1.createRow(7);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("L2ARML File Path : "+L2FilePath);
			rowFoSummary= sheet1.createRow(8);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("SACCR File Path : "+SACCRFilePath);
			rowFoSummary= sheet1.createRow(9);
			cells=rowFoSummary.createCell(0);
			cells.setCellValue("Trade Count : ");
			cells.setCellStyle(sheet1Style1);
				rowFoSummary = sheet1.createRow(10);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Trades in L2ARML: "+L2ARMLdata.size());
				rowFoSummary = sheet1.createRow(11);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Trades in SACCR: "+SACCRdata.size());
				rowFoSummary = sheet1.createRow(12);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Missing Trades/Extra Trades: ");
				rowFoSummary = sheet1.createRow(13);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Missing Trades/Extra Trades in L2ARML : "+L2ARMLCount);
				rowFoSummary = sheet1.createRow(14);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Missing Trades/Extra Trades in SACCR : "+saccrCount);
				rowFoSummary = sheet1.createRow(15);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Attribute Mismatch Details: ");
				rowFoSummary = sheet1.createRow(16);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Number of Attribute MisMatch : "+attributesCount);
				rowFoSummary = sheet1.createRow(17);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Test Execution Report");
				cells.setCellStyle(sheet1Style1);
				rowFoSummary = sheet1.createRow(18);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Passed: "+passCount);
				cells.setCellStyle(sheet1Style1);
				rowFoSummary = sheet1.createRow(19);
				cells=rowFoSummary.createCell(0);
				cells.setCellValue("Failed: "+failCount);
				cells.setCellStyle(sheet1Style1);
				sheet1.autoSizeColumn(0);
				sheet2.autoSizeColumn(1);
				sheet3.autoSizeColumn(1);
				sheet4.autoSizeColumn(0);
				sheet4.autoSizeColumn(1);
				sheet4.autoSizeColumn(2);
				sheet4.autoSizeColumn(3);
				sheet4.autoSizeColumn(4);
				sheet4.autoSizeColumn(5);
				sheet5.autoSizeColumn(0);
				sheet5.autoSizeColumn(1);
				sheet5.autoSizeColumn(2);
					result.flush();
					result.close();
					L2File.close();
					SACCRFile.close();
					String tist=new SimpleDateFormat("YYYYMMDD_HHmm").format(new Date());
					System.out.println(tist);
					String outputFileName=path+"Compare_Result_Data_"+tist+".xlsx";
					try(FileOutputStream outputStream = new FileOutputStream(outputFileName))
					{
						workbook.write(outputStream);
					}
					workbook.close();
					String[] resultArr = new String[2];
					resultArr[0]="Validation Successful!\n\n Number of Trades Validated in L2ARML :"+L2ARMLdata.size()+"\n Number of tTrades Validated in SACCR: "+SACCRdata.size()+"\n\n Test result are available in the below path : "+
					outputFileName+"\n\n                                                          Do you Want to open the file?";
					resultArr[1]=outputFileName;
					return(resultArr);
					}
					catch(IOException e)
					{
						String[] resultArr=new String[2];
						resultArr[0]="The REquired File is Missing or already open";
						resultArr[1]="fileError";
						return(resultArr);
					}
					}
					
				

				
			}