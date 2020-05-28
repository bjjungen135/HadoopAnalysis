package cs455.hadoop.meanSO2;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs455.hadoop.utils.Average;

public class MeanSO2Mapper extends Mapper<LongWritable, Text, Text, Average> {
	
	private boolean setup = false;
	private HashSet<String> states;
	private Average average = new Average();
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(key.get() == 0)
			return;
		if(!setup)
			setup();
		String line = value.toString();
		String[] lineSplit = line.split(",");
		if(states.contains(lineSplit[21])) {
			try {
				average.setAverage(Double.parseDouble(lineSplit[13]));
				average.setCount(1);
				context.write(new Text(lineSplit[21]), average);
			}
			catch(NumberFormatException e) {
				return;
			}
		}
	}
	
	private void setup() {
		states = new HashSet<>();
		states.add("\"Arizona\"");
		states.add("\"Texas\"");
		states.add("\"Nevada\"");
		states.add("\"Mississippi\"");
		states.add("\"Florida\"");
		states.add("\"Louisiana\"");
		states.add("\"Arkansas\"");
		states.add("\"Oklahoma\"");
		states.add("\"Kansas\"");
		states.add("\"Georgia\"");
	}
	
}
