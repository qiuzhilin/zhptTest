package warterMask;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

public class WarterMaskET implements AssignerWithPeriodicWatermarks<String> {
    private Long currentMaxTime=0l;
    private Long currentWaterMark=0l;

    /**
     * 执行完extractTimestamp 会执行此方法
     * @return
     */
    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        //水位时间更新，
        currentWaterMark =currentMaxTime-5000;
        Watermark watermark=new Watermark(currentWaterMark);
        //System.out.println("当前水位线:" + currentWaterMark+"this:"+this);
        return watermark;
    }

    /**
     * 摘取evnetTime 时间返回的是timeStamp类型
     * @param s
     * @param l
     * @return
     */
    @Override
    public long extractTimestamp(String s, long l) {
        //数据首先会进入此方法，表示从源数据中摘取eventTime, 必定是long类型上
        String[] arr=s.split(",");
        Long timeStamp=Long.parseLong(arr[1]);
        currentMaxTime=Math.max(currentMaxTime,timeStamp);
        System.out.println("Key:" + arr[0] + ",EventTime:" + timeStamp + ",水位线:" + currentWaterMark);
        return timeStamp;
    }
}
