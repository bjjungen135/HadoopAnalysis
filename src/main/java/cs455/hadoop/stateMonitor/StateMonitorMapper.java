package cs455.hadoop.stateMonitor;

import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StateMonitorMapper extends Mapper<LongWritable, Text, Text, Text> {

	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] lineSplit = line.split(",");
		if(key.get() == 0)
			return;
		if(!lineSplit[21].equals("State Name")) {
			context.write(new Text(lineSplit[21]), new Text(lineSplit[2] + "," + lineSplit[22]));
		}
	}
}
