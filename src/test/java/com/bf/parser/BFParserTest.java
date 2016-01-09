package com.bf.parser;

import com.bf.lang.BFContext;
import com.bf.truffle.root.BFRootNode;
import com.oracle.truffle.api.source.Source;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by moritz on 07.01.2016.
 */
public class BFParserTest {

    private final BFContext context = new BFContext(new int[10]);


    @Test
    public void testParser() {

        BFParser parser = new BFParser();


        BFRootNode rootNode = parser.parse(context, Source.fromText("+++[---[++++++]]", "Just a test"));


        Assert.assertEquals(4, rootNode.nodes.length);


    }


}