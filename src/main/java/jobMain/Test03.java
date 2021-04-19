package jobMain;

import org.apache.flink.streaming.api.datastream.DataStream;
import processFunction.FraudDetector;
import sink.AlterSink;
import vo.Alert;
import vo.Transaction;

public class Test03 {
    public static void alter(DataStream<Alert> alerts){

        AlterSink alterSink=new AlterSink();
        alerts.addSink(alterSink).setParallelism(1);
    }
}
