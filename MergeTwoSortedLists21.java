/**
 * @author liji
 * @date 2021/11/19
 */
public class MergeTwoSortedLists21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //保护节点
        ListNode sentry = new ListNode(-200);
        ListNode head = sentry;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                head.next = l1;
                head = head.next;
                l1 = l1.next;
            }
            if (l2 != null) {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }
        return sentry.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
