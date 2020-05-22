

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertXMLtoCSV {
	public static String ConvertCSV(String xmlFilePath) throws IOException{
		System.out.println("Started to work");
		//String xmlFilePath="C:\\ETL\\New Folder (2) \\ test.xml";
		String xmlstr=new String(Files.readAllBytes(Paths.get(xmlFilePath)));
		String file;
		int lastIndex=xmlFilePath.lastIndexOf("\\");
		file=xmlFilePath.substring(lastIndex+1);
		String path=xmlFilePath.substring(0,lastIndex+1);
		path=path+file+"_Converted.csv";
		BufferedWriter result=new BufferedWriter(new FileWriter(new File(path)));
		Map<String,String> listOfRecordsFile=new LinkedHashMap<>();
		Map<String,String> listOfAllAttributes=new LinkedHashMap<>();
		listOfRecordsFile=parseXML(xmlstr);
		Map<String,Map<String,String>> listOfAllRecords=new LinkedHashMap<>();
		for(String key:listOfRecordsFile.keySet()){
			Map<String,String> recordFile=new LinkedHashMap<>();
			recordFile=parseRow(listOfRecordsFile.get(key));
			listOfAllAttributes.putAll(recordFile);
			listOfAllRecords.put(key, recordFile);
			
		}
		for(String Attribute:listOfAllAttributes.keySet()){
			result.write(Attribute+",");
			
		}
		result.write("\n");

		System.out.println("Size :"+listOfAllRecords.size());
		for(String key:listOfAllRecords.keySet()){
			//System.out.println("key"+key);
			Map<String,String> eachRow=listOfAllRecords.get(key);
			for(String Attribute:listOfAllAttributes.keySet()){
				if(eachRow.containsKey(Attribute)){
					result.write(eachRow.get(Attribute)+",");
				}
				else{
					result.write(",");
				}
			}
			result.write("\n");
		}
		result.flush();
		result.close();
		return path;
		
	}
	static Map<String,String> parseXML(String xmlstr){
		xmlstr=xmlstr.toUpperCase();
		int initialPosition=xmlstr.indexOf("<ARMESSAGE>");
		int finalPosition=0;
		Map<String,String> listOfRecords=new LinkedHashMap<>();
		while(initialPosition!=-1){
			finalPosition=xmlstr.indexOf("</ARMESSAGE>",initialPosition);
			String subString =xmlstr.substring(initialPosition,finalPosition+12);
			int valueInitialPosition=subString.indexOf("<NAME>TRADE ID</NAME>");
			valueInitialPosition=subString.indexOf("<VALUE>",valueInitialPosition);
			int valueFinalPosition=subString.indexOf("</VALUE>",valueInitialPosition);
			String tradeID=subString.substring(valueInitialPosition,valueFinalPosition);
			listOfRecords.put(tradeID,subString);
			initialPosition=xmlstr.indexOf("<ARMESSAGE>",finalPosition);

			System.out.println("Trade Id :"+tradeID);
			
		}
		return listOfRecords;
		
	}
	static Map<String,String> parseRow(String data){
		Map<String,String> RecordFile=new LinkedHashMap<>();
		int initialPosition=data.indexOf("<");
		while(initialPosition!=-1){
			int finalPosition=data.indexOf('>',initialPosition);
			if(finalPosition+1==data.length()){
				break;
			}
			if(data.charAt(finalPosition+1)!='<'){
				String attributeName=data.substring(initialPosition+1,finalPosition);
				String value="";
				if(attributeName.equals("NAME")){
					initialPosition=finalPosition+1;
					finalPosition=data.indexOf('<',initialPosition);
					attributeName=data.substring(initialPosition,finalPosition);
					initialPosition=data.indexOf("<VALUE>",finalPosition)+7;
					finalPosition=data.indexOf('<',initialPosition);
					value=data.substring(initialPosition,finalPosition);
					
					
					
				}
				else{
					initialPosition=finalPosition+1;
					finalPosition=data.indexOf('<',initialPosition);
					value=data.substring(initialPosition,finalPosition);
					
				}
				RecordFile.put(attributeName,value);
				
			}
			else{
				String tagName=data.substring(initialPosition+1,finalPosition);
				if(!tagName.contains("/")){
					RecordFile.put(tagName, "");
					
				}
			}
			initialPosition= data.indexOf('<',finalPosition);
			
		}
		return RecordFile;
	}
	

}
