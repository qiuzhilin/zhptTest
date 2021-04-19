package jobMain;

import org.apache.flink.streaming.api.datastream.DataStream;
import processFunction.FraudDetector;
import sink.AlterSink;
import vo.Alert;
import vo.Transaction;

public class Test02 {
    public static void disposeDataStream(DataStream<Transaction> td){
        DataStream<Alert> alerts = td
                .keyBy(Transaction::getUid)
                .process(new FraudDetector())
                .name("fraud-detector");
        alerts.addSink(AlterSink.getInstance());
       // Test03.alter(alerts);
    }
}
