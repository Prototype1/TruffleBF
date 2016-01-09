# TruffleBF
A simple Brain Fuck AST Interpreter using the Truffle Framework. 

To reach optimal performance execute on graal vm but it is also possible to execute it on any other JVM. 

To get started take a look at the test folder, it includes a benchmark and many tests. 

The language interop is not yet implemented neither the debugger. 

An idea would be to allow a language to switch the backed array (int array per default) per context so it can manipulate 
its own primivite arrays via BrainFuck. 

License is MIT 

The used Truffle Version was build by me will asap change it using maven when Truffle 0.9 is available in maven repo.