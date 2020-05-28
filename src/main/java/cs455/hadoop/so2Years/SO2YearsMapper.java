package cs455.hadoop.so2Years;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs455.hadoop.utils.Average;

public class SO2YearsMapper extends Mapper<LongWritable, Text, Text, Average> {
	
	private Average average = new Average();
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if(key.get() == 0)
			return;
		String line = value.toString();
		String[] lineSplit = line.split(",");
		String date = lineSplit[11];
		date = date.replace("\"", "");
		String[] dateSplit = date.split("-");
		String year = dateSplit[0];
		try {
			average.setAverage(Double.parseDouble(lineSplit[13]));
			average.setCount(1);
			context.write(new Text(year), average);
		}
		catch(NumberFormatException e) {
			return;
		}
	}

}
