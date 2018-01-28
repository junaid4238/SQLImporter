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
	void readCSVHeaders() throws IOException {
		Reader in = new FileReader("src/application/objectdata.csv");
		CSVFormat csvFormate = CSVFormat.DEFAULT.withHeader();
		CSVParser csvFileParser = new CSVParser(in, csvFormate);
		List csvRecords = csvFileParser.getRecords();
		System.err.println(csvRecords.get(0));
		/*Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
		    String id = record.get("ID");
		    System.out.println(id);
		}*/
	}
}
