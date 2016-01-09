package com.bf;

import com.bf.lang.BFContext;
import com.bf.parser.BFParser;
import com.bf.truffle.root.BFRootNode;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.source.Source;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by moritz on 08.01.2016.
 */
public class HelloWorld {


    private final static String HELLO_WORLD = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";

    private final BFContext context = new BFContext(new int[32_000]);
    private final BFRootNode rootNode = new BFParser().parse(context, Source.fromText(HELLO_WORLD, "hello world"));
    private final RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);

    //redirected
    private final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @Before
    public void before() {
        System.setOut(new PrintStream(myOut));
    }

    @After
    public void after() {
        System.setOut(null);
    }


    @Test
    public void mandelbrot() {
        Object call = callTarget.call();

        Assert.assertEquals("Hello World!", myOut.toString().replace("\n", ""));

    }

}
