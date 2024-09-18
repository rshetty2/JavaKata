package leetcode;

/**
 * https://www.youtube.com/watch?v=KMS0WFxrsT8
 */
public class TwoNumbers {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode resultNode = new ListNode(0);
        ListNode ptr = resultNode;
        int carry = 0;

        while(l1 != null || l2 != null) {
            int sum = 0 + carry;

            if(l1 != null) {
                sum+= l1.val;
                l1 = l1.next;
            }

            if (l2 !=null) {
                sum+=l2.val;
                l2= l2.next;
            }

            carry = sum /10;
            sum = sum %10;

            ptr.next = new ListNode(sum);
            ptr = ptr.next;
        }
        if (carry == 1) ptr.next = new ListNode(1);

        return resultNode.next;

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5, node1);
        ListNode node3 = new ListNode(6, node2);

        ListNode node4 = new ListNode(7);
        ListNode node5 = new ListNode(8, node4);
        ListNode node6 = new ListNode(9, node5);

        ListNode result = TwoNumbers.addTwoNumbers(node3,node6);
        System.out.println("Result list length = " );
        //ListNode current = result;
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
