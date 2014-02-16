package com.nsn.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example based on Hadoop 2.0 and copying standard 
 *  Wordcount example
 * @author acp
 *
 */
public class WordCountMapper  extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	public WordCountMapper()  {
		System.out.println("Init WordCount Mapper");
		}

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
	
		StringTokenizer itr = new StringTokenizer(value.toString());
		while( itr.hasMoreTokens()){
			word.set(itr.nextToken());
			context.write(word, one);
		}
		
	}

}
