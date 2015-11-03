.PS
copy "sequence.pic";

# Object definition
object(S,"s:Switch");
object(P,"p:Pump");

# Message exchange
message(S,P,"run()");
message(S,P,"stop()");

# Object lifeline completion
complete(S);
complete(P);

.PE
T
