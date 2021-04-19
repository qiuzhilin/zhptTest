package map;

import org.apache.flink.api.common.functions.MapFunction;
import vo.EventFLowVo;
import vo.Transaction;

import java.util.Date;

public class EventFlowMapFunction implements MapFunction<String, EventFLowVo> {
    @Override
    public EventFLowVo map(String s) throws Exception {
        System.out.println(s);
        String[] str=s.split(",");

        EventFLowVo eventFLowVo=new EventFLowVo(str[0],new Date());
        return eventFLowVo;
    }
}
