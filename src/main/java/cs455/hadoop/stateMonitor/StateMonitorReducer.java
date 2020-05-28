package cs455.hadoop.stateMonitor;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

public class StateMonitorReducer extends Reducer<Text, Text, Text, IntWritable> {
	
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int monitors = 0;
		HashSet<String> counties = new HashSet<>();
		for(Text val : values) {
			if(counties.contains(val.toString()))
				continue;
			else {
				monitors += 1;
				counties.add(val.toString());
			}
		}
		context.write(key, new IntWritable(monitors));
	}
}
