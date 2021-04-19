package processFunction;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import vo.EventFLowVo;

public class MyCountAggregate implements AggregateFunction<EventFLowVo, Integer, Tuple2<String,Integer>> {
    @Override
    public Integer createAccumulator() {
        return 0;
    }

    @Override
    public Integer add(EventFLowVo eventFLowVo, Integer integer) {
        return integer+1;
    }

    @Override
    public Tuple2<String, Integer> getResult(Integer integer) {

        return null;
    }

    @Override
    public Integer merge(Integer integer, Integer acc1) {
        return integer+acc1;
    }
}
