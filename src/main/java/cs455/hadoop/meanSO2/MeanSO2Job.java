package cs455.hadoop.meanSO2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cs455.hadoop.utils.Average;

public class MeanSO2Job {

	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "Mean SO2 States");
			job.setJarByClass(MeanSO2Job.class);
			job.setMapperClass(MeanSO2Mapper.class);
			job.setReducerClass(MeanSO2Reducer.class);
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
