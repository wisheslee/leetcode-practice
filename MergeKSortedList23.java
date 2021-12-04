import java.util.Arrays;

/**
 * @author liji
 * @date 2021/12/4
 */
public class MergeKSortedList23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        int size = lists.length;
        ListNode node1 = mergeKLists(Arrays.copyOfRange(lists, 0, size / 2));
        ListNode node2 = mergeKLists(Arrays.copyOfRange(lists, size / 2, size));
        //合并
        return merge(node1, node2);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode protect = new ListNode(0);
        ListNode head = protect;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                head.next = node1;
                head = head.next;
                node1 = node1.next;
            } else {
                head.next = node2;
                head = head.next;
                node2 = node2.next;
            }
        }
        while (node1 != null) {
            head.next = node1;
            head = head.next;
            node1 = node1.next;
        }

        while (node2 != null) {
            head.next = node2;
            head = head.next;
            node2 = node2.next;
        }
        return protect.next;
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
