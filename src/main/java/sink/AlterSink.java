package sink;

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import vo.Alert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlterSink extends RichSinkFunction<Alert> {
    private List<String> alist=new ArrayList<>();
    private static AlterSink alterSink;
    public static AlterSink getInstance(){
            if (alterSink==null){
                alterSink=new AlterSink();
            }
            return alterSink;
    }

    @Override
    public void invoke(Alert value, Context context) throws Exception {
        System.out.println("sink invoke~~~~~~~~~ +"+new Date());
        System.out.println(value.toString());
        System.out.println(this);
        //Thread.sleep(1000);
        alist.add(value.toString());
        System.out.println(alist.size());

    }
}
