package cs455.hadoop.so2Coast;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import cs455.hadoop.utils.Average;

public class CoastSO2Reducer extends Reducer<Text, Average, Text, DoubleWritable> {

	protected void reduce(Text key, Iterable<Average> values, Context context) throws IOException, InterruptedException {
		double sum = 0;
		long count = 0;
		for(Average average : values) {
			sum = sum + average.getAverage() * average.getCount();
			count = count + average.getCount();
		}
		context.write(key, new DoubleWritable(sum / count));
	}
	
}
