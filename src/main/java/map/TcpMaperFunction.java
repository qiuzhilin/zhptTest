package map;

import org.apache.flink.api.common.functions.MapFunction;
import vo.Transaction;

import java.util.Date;

public class TcpMaperFunction implements MapFunction<String, Transaction> {
    @Override
    public Transaction map(String s) throws Exception {
        System.out.println(s);
        String[] str=s.split(",");

        Transaction transaction=new Transaction(Long.parseLong(str[0]),Double.parseDouble(str[1]),new Date());
        return transaction;
    }
}
