package com.bf.truffle.root;

import com.bf.lang.BFTypeSystem;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;

/**
 * Created by moritz on 06.01.2016.
 */
@NodeInfo(shortName = "root of all normal nodes")
@TypeSystemReference(BFTypeSystem.class)
public abstract class BFNode extends Node {

    public BFNode(SourceSection src) {
        super(src);
    }

    public abstract void executeVoid(VirtualFrame frame);

}
