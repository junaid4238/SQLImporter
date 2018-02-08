package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Parse CSV file in chunks and provide us the required data.
 * 
 * @author Junaid
 *
 */
public class CSVEngine {
	
	/**
	 * Create constructor
	 */
	public CSVEngine() {
		
	}
		
	/**
	 * Returns the csv file headers from given file directory.
	 * 
	 * @param FILEDIR Directory of the file to read from.
	 * @return Iteratable String of values.
	 * @throws IOException
	 */
	public Iterator<String> readCSVHeaders(String FILEDIR) throws IOException {
		//Reader in = new FileReader("src/application/objectdata.csv");
		Reader in = new FileReader(FILEDIR);
		CSVFormat csvFormate = CSVFormat.DEFAULT.withSkipHeaderRecord(false);
		CSVParser csvFileParser = new CSVParser(in, csvFormate);
		
		CSVRecord csvRecord = csvFileParser.getRecords().get(0);
		
		/*for (int i = 0; i < csvRecords.size(); i++) {
			System.out.println(csvRecords.get(i));
		}*/
		//check csv file valid or not
			//System.out.println(csvRecord.size());
		/*Iterator<String> values = csvRecord.iterator();
		for (String string : csvRecord) {
			System.out.println(string);
		}*/
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
	
	
	/**
	 * Returns records of csv file from given directory.
	 * 
	 * @param FILEDIR Directory of the file to read from.
	 * @return List of all records from csv file.
	 * @throws IOException
	 */
	public List<CSVRecord> readFileRecords(String FILEDIR) throws IOException {
		Reader in = new FileReader(FILEDIR);
		CSVFormat csvFormate = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		CSVParser csvFileParser = new CSVParser(in, csvFormate);
		
		return csvFileParser.getRecords();
	}
}
