package cs455.hadoop.stateMonitor;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class StateMonitorJob {
	
	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "State Monitor");
			job.setJarByClass(StateMonitorJob.class);
			//job.setMapperClass(StateMonitorMapper.class);
			job.setReducerClass(StateMonitorReducer.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, StateMonitorMapper.class);
			MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, StateMonitorMapper.class);
			FileOutputFormat.setOutputPath(job, new Path(args[2]));
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
