package org.example;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;


public class Screen {

    public static class TokenizerMapper extends Mapper<Object, Text, Text, Text>{
        private Text category = new Text();
        private Text wordAndCount = new Text();
        private MultipleOutputs<Text, Text> mos;

        @Override
        public void setup(Context context) {
            mos = new MultipleOutputs<Text, Text>(context);
        }

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String[] parts = value.toString().split(",");
            String categoryStr = parts[0].trim();
            String wordAndCountStr = parts[1].trim();
            String[] word_count = wordAndCountStr.split("\\s+");
            String word = word_count[0].trim();
            String count = word_count[1].trim();
            wordAndCountStr = word + "\t" + count;
            category.set(categoryStr);
            wordAndCount.set(wordAndCountStr);
            mos.write(category, wordAndCount, categoryStr);
        }

        @Override
        public void cleanup(Context context) throws IOException, InterruptedException {
            mos.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "split by category");
        job.setJarByClass(Screen.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        // 设置输入路径
        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // 设置 MultipleOutputs 的输出格式和文件名格式
        MultipleOutputs.addNamedOutput(job, "output", TextOutputFormat.class, Text.class, Text.class);
        MultipleOutputs.setCountersEnabled(job, true);
        // 运行作业
        job.waitForCompletion(true);
    }
}
