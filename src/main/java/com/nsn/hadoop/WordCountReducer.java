package com.nsn.hadoop;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable result = new IntWritable();
	
	public WordCountReducer() {
		System.out.println("Init WordCountReducer");
	}
	
	@Override
	protected void reduce(Text word, Iterable<IntWritable> intOne,
			Context context)
			throws IOException, InterruptedException {
		int sum=0;
		Iterator<IntWritable> itr= intOne.iterator();
		
		while (itr.hasNext()){
			sum  += itr.next().get();
			
		}
		result.set(sum);
		context.write(word, result);
	}

}
