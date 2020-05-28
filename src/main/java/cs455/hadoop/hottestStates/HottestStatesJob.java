package cs455.hadoop.hottestStates;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cs455.hadoop.so2Coast.CoastSO2Job;
import cs455.hadoop.so2Coast.CoastSO2Mapper;
import cs455.hadoop.so2Coast.CoastSO2Reducer;
import cs455.hadoop.utils.Average;

public class HottestStatesJob {
	
	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "SO2 Coasts");
			job.setJarByClass(HottestStatesJob.class);
			job.setMapperClass(HottestStatesMapper.class);
			job.setReducerClass(HottestStatesReducer.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Average.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(DoubleWritable.class);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
	}

}
