package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventFLowVo {
    private String eventType;
    private Date  startTime;

    public EventFLowVo(String eventType, Date startTime) {
        this.eventType = eventType;
        this.startTime = startTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    public String getKeyStr(){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYmmddHH");
        return sdf.format(this.startTime)+"_"+this.eventType;
    }
}
