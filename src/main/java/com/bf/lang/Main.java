package com.bf.lang;

import com.bf.parser.BFParser;
import com.bf.truffle.root.BFRootNode;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.source.Source;

/**
 * Created by moritz on 06.01.2016.
 */
public class Main {

    public static void main(String... args) {


        if (args.length == 0) {
            throw new IllegalArgumentException("Please provide a parsable Brainfuck String");
        }

        BFContext context = new BFContext(new int[32_000]);

        BFRootNode rootNode = new BFParser().parse(context, Source.fromText(args[0], "userstring"));


        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);


        callTarget.call();


    }
}
