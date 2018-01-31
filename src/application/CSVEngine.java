package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVEngine {

	public CSVEngine() {
		// TODO Auto-generated constructor stub
		try {
			readCSVHeaders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Iterator<String> readCSVHeaders() throws IOException {
		Reader in = new FileReader("src/application/objectdata.csv");
		CSVFormat csvFormate = CSVFormat.DEFAULT.withSkipHeaderRecord(false);
		CSVParser csvFileParser = new CSVParser(in, csvFormate);
		
		CSVRecord csvRecord = csvFileParser.getRecords().get(0);
		
		/*for (int i = 0; i < csvRecords.size(); i++) {
			System.out.println(csvRecords.get(i));
		}*/
		//check csv file valid or not
			//System.out.println(csvRecord.size());
		Iterator<String> values = csvRecord.iterator();
		for (String string : csvRecord) {
			System.out.println(string);
		}
		return csvRecord.iterator();
		
		
		//System.err.println(csvRecords.get(1));
		/*Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
		    String id = record.get(1);
		    System.out.println(id +record.get(1));
		}*/
		/*CSVParser record = CSVFormat.DEFAULT.withSkipHeaderRecord(false).parse(in);
		System.err.println(record.getRecords());*/
		/*String[] headers = csvFileParser.
		System.out.println(headers.length);*/
	}
}
