package jobMain;


import map.TcpMaperFunction;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import processFunction.FraudDetector;
import sink.AlterSink;
import vo.Alert;
import vo.Transaction;

public class FraudDetectionJob {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.setInteger(RestOptions.PORT, 8082);


        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);
        env.setStateBackend(new FsStateBackend("file:///D:///checkpoints"));
        DataStream<String> text = env.socketTextStream("localhost", 8000, "\n").name("tcpTest");
        DataStream<Transaction> transactions=text.map(new TcpMaperFunction());

        Test01.disposeDataStream(transactions);
        Test02.disposeDataStream(transactions);

/*        DataStream<Alert> alerts = transactions
                .keyBy(Transaction::getUid)
                .process(new FraudDetector())
                .name("fraud-detector");

        AlterSink alterSink=new AlterSink();
        alerts.addSink(alterSink).setParallelism(1);*/
       // alerts.addSink(new AlterSink());
/*        DataStream<Alert> alerts = transactions
                .keyBy(Transaction::getAccountId)
                .process(new FraudDetector())
                .name("fraud-detector");

        alerts
                .addSink(new AlertSink())
                .name("send-alerts");*/

        env.execute("Fraud Detection");
    }
}
