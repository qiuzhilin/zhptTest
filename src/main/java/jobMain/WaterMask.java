package jobMain;

import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import warterMask.WarterMaskET;

import javax.annotation.Nullable;


public class WaterMask  {
    public static void main(String[] args) throws Exception {
        //创建flink流式执行环境
        StreamExecutionEnvironment evn=StreamExecutionEnvironment.getExecutionEnvironment();
        //设定为eventTime 时间
        evn.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //从socket获取数据
        DataStream<String> dataStream=evn.socketTextStream("127.0.0.1",8000,"\n")
                .assignTimestampsAndWatermarks(new WarterMaskET()).name("eventTimeSouce").setParallelism(1);

        //一对一map，拆分返回tuple2<String,String>
        DataStream<Tuple2<String,String>> dtuple2=dataStream.map(new MapFunction<String, Tuple2<String,String>>() {
            @Override
            public Tuple2<String,String> map(String s) throws Exception {
                String[] arr=s.split(",");
                System.out.println("前："+arr[0]);
                Thread.sleep(15000);
                System.out.println("后："+arr[0]);
                return new Tuple2<String,String>(arr[0],arr[1]);
            }
        }).name("eventime map").setParallelism(5);

        dtuple2.keyBy(0).window(TumblingProcessingTimeWindows.of(Time.seconds(5))).fold("Start:", new FoldFunction<Tuple2<String, String>, String>() {
            @Override
            public String fold(String s, Tuple2<String, String> o) throws Exception {
                return s + " - " + o.f1;
            }
        }).print();

        evn.execute("WaterMark Test Demo");


    }
}
