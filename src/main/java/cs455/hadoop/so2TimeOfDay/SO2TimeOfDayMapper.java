package cs455.hadoop.so2TimeOfDay;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs455.hadoop.utils.Average;

public class SO2TimeOfDayMapper extends Mapper<LongWritable, Text, Text, Average> {
	
	private Average average = new Average();
	private boolean setup = false;
	private HashSet<String> years;
	
	//Time GMT is in the 12th position
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(key.get() == 0)
			return;
		if(!setup)
			setup();
		String line = value.toString();
		String[] lineSplit = line.split(",");
		String date = lineSplit[11];
		date = date.replace("\"", "");
		String[] dateSplit = date.split("-");
		String year = dateSplit[0];
		if(years.contains(year)) {
			try {
				average.setAverage(Double.parseDouble(lineSplit[13]));
				average.setCount(1);
				context.write(new Text(lineSplit[12]), average);
			}
			catch(NumberFormatException e) {
				return;
			}
		}
	}
	
	private void setup() {
		years = new HashSet<>();
		years.add("2000");
		years.add("2001");
		years.add("2002");
		years.add("2003");
		years.add("2004");
		years.add("2005");
		years.add("2006");
		years.add("2007");
		years.add("2008");
		years.add("2009");
		years.add("2010");
		years.add("2011");
		years.add("2012");
		years.add("2013");
		years.add("2014");
		years.add("2015");
		years.add("2016");
		years.add("2017");
		years.add("2018");
		years.add("2019");
	}
	
}
