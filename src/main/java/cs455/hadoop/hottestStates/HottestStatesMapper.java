package cs455.hadoop.hottestStates;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs455.hadoop.utils.Average;

public class HottestStatesMapper extends Mapper<LongWritable, Text, Text, Average> {
	
	private Average average = new Average();
	private boolean setup = false;
	private HashSet<String> months;
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(key.get() == 0)
			return;
		if(!setup)
			setup();
		String line = value.toString();
		String[] lineSplit = line.split(",");
		String date = lineSplit[11];
		if(date.contains("#") || date.contains("/"))
			return;
		String[] dateSplit = date.split("-");
		String month = dateSplit[1];
		if(months.contains(month)) {
			try {
				average.setAverage(Double.parseDouble(lineSplit[13]));
				if(average.getAverage() < 0)
					return;
				average.setCount(1);
				context.write(new Text(lineSplit[21]), average);
			}
			catch(NumberFormatException e) {
				return;
			}
		}
	}
	
	private void setup() {
		months = new HashSet<>();
		months.add("06");
		months.add("07");
		months.add("08");
	}

}
