package com.nsn.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class TestWordCount {
	
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text,IntWritable,Text,IntWritable> reduceDriver;

	public TestWordCount() {
		
	}
	
	@Before
	public void setup(){
		WordCountMapper mapper =  new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
		reduceDriver = new ReduceDriver<Text,IntWritable,Text,IntWritable>();
		reduceDriver.setReducer(reducer);
		mapReduceDriver = new MapReduceDriver();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
		
	}
	
	@Test
	public void testMapper() throws IOException{
		mapDriver.withInput(new LongWritable(1), new Text("cat cat dog"));
	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
	    mapDriver.withOutput(new Text("cat"), new IntWritable(1));
	    mapDriver.withOutput(new Text("dog"), new IntWritable(1));
	    mapDriver.runTest();
	}
	
	@Test
	public void testReducer() throws IOException{
		
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("cat"),values);
		reduceDriver.withOutput(new Text("cat"), new IntWritable(2));
		reduceDriver.runTest();
	}
	@Test
	public void testMapReduce() throws IOException{
		mapReduceDriver.withInput(new LongWritable(1), new Text("cat cat mouse"));
		mapReduceDriver.addOutput(new Text("cat"), new IntWritable(2));
		mapReduceDriver.addOutput(new Text("mouse"), new IntWritable(1));
		mapReduceDriver.runTest();
	}
}
