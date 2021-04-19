package jobMain;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.functions.sink.TwoPhaseCommitSinkFunction;

public class Test05 extends TwoPhaseCommitSinkFunction {
    /**
     * Use default {@link ListStateDescriptor} for internal state serialization. Helpful utilities for using this
     * constructor are {@link TypeInformation#of(Class)}, {@link TypeHint} and
     * {@link TypeInformation#of(TypeHint)}. Example:
     * <pre>
     * {@code
     * TwoPhaseCommitSinkFunction(TypeInformation.of(new TypeHint<State<TXN, CONTEXT>>() {}));
     * }
     * </pre>
     *
     * @param transactionSerializer {@link TypeSerializer} for the transaction type of this sink
     * @param contextSerializer     {@link TypeSerializer} for the context type of this sink
     */
    public Test05(TypeSerializer transactionSerializer, TypeSerializer contextSerializer) {
        super(transactionSerializer, contextSerializer);
    }

    @Override
    protected void invoke(Object transaction, Object value, Context context) throws Exception {

    }

    @Override
    protected Object beginTransaction() throws Exception {
        return null;
    }

    @Override
    protected void preCommit(Object transaction) throws Exception {

    }

    @Override
    protected void commit(Object transaction) {

    }

    @Override
    protected void abort(Object transaction) {

    }
}
