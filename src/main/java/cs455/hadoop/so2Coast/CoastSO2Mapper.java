package cs455.hadoop.so2Coast;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs455.hadoop.utils.Average;

public class CoastSO2Mapper extends Mapper<LongWritable, Text, Text, Average> {
	
	private HashSet<String> east = new HashSet<>();
	private HashSet<String> west = new HashSet<>();
	private boolean setup = false;
	private Average average = new Average();
	

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(key.get() == 0)
			return;
		if(!setup)
			setup();
		String line = value.toString();
		String[] lineSplit = line.split(",");
		if(east.contains(lineSplit[21])) {
			try {
				average.setAverage(Double.parseDouble(lineSplit[13]));
				average.setCount(1);
				context.write(new Text("East"), average);
			}
			catch(NumberFormatException e) {
				return;
			}
		}
		if(west.contains(lineSplit[21])) {
			try {
				average.setAverage(Double.parseDouble(lineSplit[13]));
				average.setCount(1);
				context.write(new Text("West"), average);
			}
			catch(NumberFormatException e) {
				return;
			}
		}
	}
	
	private void setup() {
		east.add("\"Maine\"");
		east.add("\"New Hampshire\"");
		east.add("\"Massachusetts\"");
		east.add("\"Rhode Island\"");
		east.add("\"Connecticut\"");
		east.add("\"New York\"");
		east.add("\"New Jersey\"");
		east.add("\"Delaware\"");
		east.add("\"Maryland\"");
		east.add("\"Virginia\"");
		east.add("\"North Carolina\"");
		east.add("\"South Carolina\"");
		east.add("\"Georgia\"");
		east.add("\"Florida\"");
		west.add("\"California\"");
		west.add("\"Oregon\"");
		west.add("\"Washington\"");
		west.add("\"Alaska\"");
		setup = true;
	}
		
}
