package electionSystem;


import java.util.Iterator;

public class List<F> implements Iterable<F>{

    public Node<F> head, tail=null;
    private int countOfContents = 0;

    //adds an element to the list.
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
        if(head!=null){

        }
        for(int i = 0; i<index;i++){
            temp = temp.next;
        }
        return temp;
    }

    public void clear() { //Empty list
        head=null;
    }

    public  String printList()
    {
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
        for(Node temp = head; temp!=null; temp=temp.next){
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




