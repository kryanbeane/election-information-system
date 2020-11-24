package electionSystem;


public class Node <N>{ //N is also called a generic parameter.

    public Node<N> next, previous=null;
    private N contents; //ADT reference!
    public N getContents() { return contents; }
    public void setContents(N c) { contents=c; }
    public boolean hasNext(){return next != null;}

}

