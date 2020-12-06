package electionSystem;


import java.util.Iterator;

public class List<F> implements Iterable<F>{

    public Node<F> head, tail=null;
    private int countOfContents = 0;

    // Attempt at insertionSort

    /*public void sort(){
        int inner, outer;

        for(outer=1; outer>countOfContents; outer++){
            Node<F> temp = this.accessAtIndex(outer);       // Selects a node that we will start shifting
            inner=outer;
            System.out.println("Old man");
            while(inner>0 && this.accessAtIndex(inner)==temp) {
                Node<F> Temp2 = this.a
                this.accessAtIndex(inner)=this.accessAtIndex(inner-1);
                --inner;
            }
            this.accessAtIndex(inner)=temp;
        }
    }*/
    //first attempt at swapping nodes.
    /*public void swap(int node1Index, int node2Index){
        Node<F>  node1 = head, node2 = head;

        //Checks if list is empty
        if(head == null) {
            return;
        }

        //If n1 and n2 are equal, then list will remain the same
        if(node1Index == node2Index)
            return;

        //Search for node1
        node1 = this.accessAtIndex(node1Index);
        node2 = this.accessAtIndex(node2Index);



        if(node1 != null && node2 != null) {

            //If previous node to node1 is not null then, it will point to node2
            if(node1.previous != null)
                node1.previous.next = node2;
            else
                head  = node2;

            //If previous node to node2 is not null then, it will point to node1
            if(node2.previous != null)
                node2.previous.next = node1;
            else
                head  = node1; //if the previous is null, then it will be at the head anyway.

            //Swaps the next nodes of node1 and node2
            Node<F> temp = node1.next;
            node1.next = node2.next;
            node2.next = temp;
        }
        else {
            System.out.println("It is not possible to swap these nodes: one or more node is null");
        }
    }*/

    //Second attempt at swapping nodes

   /* public void swap(int positionOfNode1, int positionOfNode2){

        Node<F> previousOfNode1 = null;
        Node<F> previousOfNode2 = null;

        Node<F> currentNode = head;
        int position = 1;
        while(currentNode!=null && currentNode.next!=null){
            // -1 used to find the previous node
            if(position == positionOfNode1 - 1){
                previousOfNode1 = currentNode;
            }else if(position == positionOfNode2 - 1){
                previousOfNode2 = currentNode;
            }
            if(null != previousOfNode1 && null != previousOfNode2){
                break;
            }
            currentNode = currentNode.next;
            position++;
        }
        //Swapping
        Node<F> temp1 = previousOfNode1.next;
        Node<F> temp2 = previousOfNode2.next;

        Node<F> node1Next = temp1.next;
        Node<F> node2Next = temp2.next;

        previousOfNode1.next = temp2;
        previousOfNode2.next = temp1;

        temp2.next = node1Next;
        temp1.next = node2Next;
    }*/

    /*public void swap(int i, int j) {
        if (i >= this.length() || j >= this.length()) {
            throw new IndexOutOfBoundsException();
        }
        if (i == j) {
            return;
        }
        if (j < i) {
            swap(j, i);
            return;
        }

        // i < j

        Node<F> ithPredecessor = null;
        Node<F> ithNode = head;
        for (int z = 0; z < i; z++) {
            ithPredecessor = ithNode;
            ithNode = ithNode.next;
        }

        Node<F> jthPredecessor = ithNode;
        Node<F> jthNode = ithNode.next;
        for (int q = i + 1; q < j; q++) {
            jthPredecessor = jthNode;
            jthNode = jthNode.next;
        }

        // Relink both nodes in the list:

        // - The jthNode:
        if (ithPredecessor == null) {
            head = jthNode;
        } else {
            ithPredecessor.next = jthNode;
        }
        Node<F> jNext = jthNode.next;
        //if (ithNode.next == jthNode) {
        if (jthPredecessor == ithNode) {
            jthNode.next = ithNode;
        } else {
            jthNode.next = ithNode.next;
        }

        // - The ithNode:
        if (jthPredecessor == ithNode) {
        } else {
            jthPredecessor.next = ithNode;
        }
        ithNode.next = jNext;
    }*/

    public void swapContents(int index1, int index2){
        Node<F> node1 = this.accessAtIndex(index1);
        Node<F> node2 = this.accessAtIndex(index2);
        Node<F> temp = new Node<F>();
        temp.setContents(node1.getContents());
        node1.setContents(node2.getContents());
        node2.setContents(temp.getContents());

    }

    public void addNode(F e) {
        //We want to add it in at the end, not the start. Not FIFO
        //Create a new node
        Node<F> newNode = new Node<>();
        newNode.setContents(e);

        //If the head equals null then the list is empty
        if(head == null) {
            head = tail = newNode;          //both head and tail are the newNode; The first, last, and only item
            head.previous = null;           //Now head and tail will be linked to null from previous and next.
            tail.next = null;
        }
        else {


            head.previous=newNode;      //newnode is the head's previous
            newNode.next = head;        //the next after newNode is current head node, two are now linked.
            head=newNode;               //we can now move the head to newnode.
            head.previous=null;         //the one before head is null, as it is the head of the list.(Line may not be needed);
        }
        countOfContents++;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public int length(){
        return countOfContents;
    }

    //returns the first node in the list
    public Node<F> accessFirst(){
        return head;
    }

    //adapted from example in notes
    public void removeNode(int index){

        if (head!=null){                                    //check to make sure not empty
            Node<F> temp;                                   //Update, now using two nodes

            if (index == 0){                                //if you want to delete the first item
                if(head.next==null){                        //and if the list contains only one item
                    head=tail=null;                         //-from skydiver example.
                }else{
                    head=head.next;                         //else, set the head equal to the next node(original head no longer using memory).
                }
                countOfContents--;
                System.out.println("Deleted node at position: 0");
            }

            else if (index > 0 && index <countOfContents){
                temp=head;

                for(int i=1;i<index;i++) temp=temp.next;    //makes temp the one BEFORE the item to be deleted.('<index')
                temp.next = temp.next.next;                 //the one after temp is now equal to the one after that.
                countOfContents--;                          //then the last node is equal to the one before the one to be deleted.
                //(thus deleting the desired node)
                System.out.println("Deleted node at position: " + index);
            }
            else System.out.println("Index was beyond range!");
        }
        else System.out.println("The list was empty!");
    }

    //returns the second node in the list
    public Node<F> accessSecond(){
        if(head.next!=null) {
            return head.next;
        }else{
            return null;
        }
    }

    //returns the node at the inputted index in the list
    public Node<F> accessAtIndex(int index){
        if(index<0)return null;

        Node<F> temp = head;
        if(head!=null) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        }
        return temp;
    }

    public void clear() { //Empty list
        head=null;
    }

    public  String printList() {
        Node<F> currNode = head;
        String fullList ="";

        // Traverse through the List
        while (currNode != null) {
            // Print the data at current node
            fullList += currNode.getContents() + "\n";

            // Go to next node
            currNode = currNode.next;
        }
        return fullList;
    }

    public String printList2(){
        String fullList = "";
        for(Node<F> temp = head; temp!=null; temp=temp.next){
            fullList += temp.getContents() + "\n";
        }
        return fullList;
    }

    @Override
    public Iterator<F> iterator() {
        return new myIterator<F>(head);
    }

    public void emptyList(){
        head=null;
    }

}




