package jobMain;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeWindowAggs {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStateBackend(new FsStateBackend("file:///D:///checkpoints"));
        DataStream<String> text = env.socketTextStream("localhost", 8000, "\n").name("tcpTest");
      //  DataStream<EventFLowVo> transactions=text.map(new EventFlowMapFunction());

        DataStream<Tuple3<String,String,Integer>> alerts = text.map(new MapFunction<String, Tuple3<String,String,Integer>>() {
            @Override
            public Tuple3<String,String,Integer> map(String s) throws Exception {
                String[] str=s.split(",");
                SimpleDateFormat sdf=new SimpleDateFormat("YYYYmmddHH");
                return new Tuple3<String,String,Integer>(str[0],sdf.format(new Date()),1);
            }
        }).keyBy(0,1).timeWindow(Time.seconds(5)).sum(2);
                //.timeWindow(Time.seconds(5)).sum(1);
//                .keyBy(EventFLowVo::getKeyStr).window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
//                .process(new ProcessWindowFunction<EventFLowVo, Tuple2<String, Integer>, String, TimeWindow>() {
//
//s
//                    @Override
//                    public void process(String s, Context context, Iterable<EventFLowVo> iterable, Collector<Tuple2<String, Integer>> collector) throws Exception {
//                        Integer count=0;
//                        for(EventFLowVo vo :iterable) count++;
//                        collector.collect(new Tuple2<String, Integer>(s.split("_")[0],count));
//                        collector.collect(new Tuple2<String, Integer>(s.split("_")[1],count));
//                    }
//                });

        alerts.print();

                //.name("fraud-detector");

       // alerts.addSink(new AlterSink()).setParallelism(1);
        // alerts.addSink(new AlterSink());
/*        DataStream<Alert> alerts = transactions
                .keyBy(Transaction::getAccountId)
                .process(new FraudDetector())
                .name("fraud-detector");

        alerts
                .addSink(new AlertSink())
                .name("send-alerts");*/

        env.execute("count");
    }
}
