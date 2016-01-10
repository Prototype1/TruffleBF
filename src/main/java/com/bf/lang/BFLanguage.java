package com.bf.lang;

import com.bf.parser.BFParser;
import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.instrument.Visualizer;
import com.oracle.truffle.api.instrument.WrapperNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.source.Source;

import java.io.IOException;

/**
 * Created by moritz on 06.01.2016.
 */
@TruffleLanguage.Registration(name = "BF", version = "0.1", mimeType = "application/x-bf")
public class BFLanguage extends TruffleLanguage<BFContext> {
    /**
     * Constants which are Context independent
     * <p>
     * TODO verify that DESCRIPTOR can be Context independent
     */

    public final static String HEAP_SLOT_NAME = "heap";
    public final static String COUNTER_SLOT_NAME = "counter";

    public final static FrameDescriptor DESCRIPTOR = new FrameDescriptor();
    public static final FrameSlot HEAP_SLOT = DESCRIPTOR.addFrameSlot(HEAP_SLOT_NAME, FrameSlotKind.Object);
    public static final FrameSlot COUNTER_SLOT = DESCRIPTOR.addFrameSlot(COUNTER_SLOT_NAME, FrameSlotKind.Int);


    private final static BFParser PARSER = new BFParser();


    @Override
    protected BFContext createContext(Env env) {

        return new BFContext(new int[32_000]);
    }

    @Override
    protected CallTarget parse(Source source, Node node, String... strings) throws IOException {

        return Truffle.getRuntime().createCallTarget(PARSER.parse(null, source));
    }

    @Override
    protected Object findExportedSymbol(BFContext context, String s, boolean b) {
        return null;
    }

    @Override
    protected Object getLanguageGlobal(BFContext context) {
        return context;
    }

    @Override
    protected boolean isObjectOfLanguage(Object o) {

        return o instanceof int[];
    }

    @Override
    protected Visualizer getVisualizer() {
        return null;
    }

    @Override
    protected boolean isInstrumentable(Node node) {
        return false;
    }

    @Override
    protected WrapperNode createWrapperNode(Node node) {
        return null;
    }

    @Override
    protected Object evalInContext(Source source, Node node, MaterializedFrame materializedFrame) throws IOException {
        throw new IllegalStateException("evalInContext not supported in BF");
    }
}
