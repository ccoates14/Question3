public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode(0);
        ListNode head = sum;

        /*
        for each number in the list
            add them both together
            if the number is >= 10 carry then one and store the remainder in the current node
         repeat till done
         */
        boolean carry;

        while (l1 != null || l2 != null) {
            int columnSum = (l1 != null ? l1.val : 0) +
                    (l2 != null ? l2.val : 0) + sum.val;
            carry = columnSum >= 10;

            if (carry) columnSum = (columnSum - 10);

            sum.val = columnSum;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            if (l1 != null || l2 != null) {
                sum.next = new ListNode(carry ? 1 : 0);
                sum = sum.next;
            } else if (carry) {
                sum.next = new ListNode(1);
            }
        }

        return head;
    }
}
