package alex.datastructures.list;

import java.util.Stack;

public class LinkedListUtils {

	public static void printIter(ListNode l) {
		Stack<Integer> stack = new Stack<Integer>();
		ListNode list = l;
		while (list != null) {
			stack.push(list.data);
			list = list.next;
		}
		while (!stack.isEmpty()) {
			System.out.printf("%d\t", stack.pop());
		}
		System.out.println();
	}

	public static ListNode sort(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode lastSorted = head;
		ListNode toBeSorted = lastSorted.next;
		while (toBeSorted != null) {
			if (toBeSorted.data < head.data) {
				lastSorted.next = toBeSorted.next;
				toBeSorted.next = head;
				head = toBeSorted;
			} else {
				ListNode node = head;
				while (node != lastSorted && node.next.data < toBeSorted.data) {
					node = node.next;
				}
				if (node != lastSorted) {
					lastSorted.next = toBeSorted.next;
					toBeSorted.next = node.next;
					node.next = toBeSorted;
				} else {
					lastSorted = lastSorted.next;
				}
			}
			toBeSorted = lastSorted.next;
		}
		return head;
	}

	public static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode merged;
		if (l1.data < l2.data) {
			merged = l1;
			merged.next = mergeSortedLists(l1.next, l2);
		} else {
			merged = l2;
			merged.next = mergeSortedLists(l1, l2.next);
		}
		return merged;
	}

	public static ListNode meetingNode(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head.next;
		if (slow == null)
			return null;
		ListNode fast = slow.next;
		while (fast != null && slow != null) {
			if (fast == slow)
				return fast;
			slow = slow.next;
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}
		}
		return null;
	}

	public static ListNode entryOfLoop(ListNode head) {
		ListNode meetingNode = meetingNode(head);
		if (meetingNode == null)
			return null;
		int nodesInloop = 1;
		ListNode node = meetingNode;
		while (node.next != meetingNode) {
			node = node.next;
			nodesInloop++;
		}
		node = head;
		for (int i = 0; i < nodesInloop; i++) {
			node = node.next;
		}
		ListNode node2 = head;
		while (node2 != node) {
			node2 = node2.next;
			node = node.next;
		}
		return node;
	}

	public static ListNode removeDuplicates(ListNode head) {
		if (head == null)
			return null;
		ListNode preNode = null;
		ListNode node = head;
		while (node != null) {
			ListNode next = node.next;
			boolean delete = false;
			if (next != null && next.data == node.data) {
				delete = true;
			}
			if (!delete) {
				preNode = node;
				node = next;
			} else {
				int value = node.data;
				ListNode toBeDeleted = node;
				while (toBeDeleted != null && toBeDeleted.data == value) {
					next = toBeDeleted.next;
					toBeDeleted = next;
				}
			}
			if (preNode == null) {
				head = next;
			} else {
				preNode.next = next;
			}
			node = next;
		}
		return head;
	}

	public static ListNode findKthToTail(ListNode head, int k) {
		if (head == null || k == 0)
			return null;
		ListNode ahead = head;
		for (int i = 0; i < k - 1; i++) {
			if (ahead.next != null) {
				ahead = ahead.next;
			} else {
				return null;
			}
		}
		ListNode kth = head;
		while (ahead.next != null) {
			kth = kth.next;
			ahead = ahead.next;
		}
		return kth;
	}

	public static ListNode swapFirstAndLastKthNodes(ListNode head, int k) {
		if (head == null || k == 0)
			return head;
		ListNode ahead = head;
		ListNode prevAhead = null;
		for (int i = 0; i < k - 1; i++) {
			if (ahead.next != null) {
				prevAhead = ahead;
				ahead = ahead.next;
			} else {
				return head;
			}
		}
		ListNode aheadCopy = ahead;
		ListNode tailKth = head;
		ListNode prevTailKth = null;
		while (aheadCopy.next != null) {
			prevTailKth = tailKth;
			tailKth = tailKth.next;
		}
		if (prevAhead != null && prevTailKth != null) {
			// swap
			prevAhead.next = tailKth;
			prevTailKth.next = ahead;
			ListNode temp = tailKth.next;
			tailKth.next = ahead.next;
			ahead.next = temp;

		} else {
			// swap first and last change the head
			ListNode tmp = ahead.next;
			ahead.next = null;
			prevTailKth.next = ahead;
			tailKth.next = tmp;
			head = tailKth;
		}
		return head;
	}

	public static ListNode reverse(ListNode head) {
		ListNode reversedHead = null;
		ListNode node = head;
		ListNode prev = null;
		while (node != null) {
			ListNode next = node.next;
			if (next == null) {
				reversedHead = node;
			}
			node.next = prev;
			prev = node;
			node = next;
		}
		return reversedHead;
	}
	
	public static ListNode reverseGroups(ListNode head, int k) {
		if (head == null || k <= 1)
			return head;

		ListNode reversedHead = null;
		ListNode n1 = head;
		ListNode prev = null;
		while (n1 != null) {
			ListNode n2 = n1;
			for (int i = 1; i < k && n2.next != null; i++) {
				n2 = n2.next;
			}
			ListNode next = n2.next;
			reverseGroup(n1, n2);
			if (reversedHead == null) {
				reversedHead = n2;
			}
			if (prev != null) {
				prev.next = n2;
			}
			prev = n1;
			n1 = next;
		}

		return reversedHead;
	}

	private static void reverseGroup(ListNode n1, ListNode n2) {
		ListNode n = n1;
		ListNode prev = null;
		while (n != n2) {
			ListNode next = n.next;
			n.next = prev;
			prev = n;
			n = next;
		}
		n.next = prev;
	}
	public static ListNode reverseGroups2(ListNode head, int k){
		if(head == null || k<=1) return head;
		ListNode rHead = null;
		ListNode node = head;
		ListNode prev=null;
		while(node!= null){
			ListNode node2 = node;
			for(int i=1; i<k && node2.next!= null;i++){
				node2 = node2.next;
			}
			ListNode next = node2.next;
			rGroup(node, node2);
			if(rHead==null){
				rHead = node2;
			}
			if(prev!= null){
				prev.next = node2;
			}
			prev = node;
			node = next;
		}
		return rHead;
	}
	public static void rGroup(ListNode n1, ListNode n2){
		ListNode node = n1;
		ListNode prev = null;
		while(n1!= n2){
			ListNode next = n1.next;
			n1.next = prev;
			prev = node;
			node = next;
		}
		n1.next = prev;
	}

	public static void main(String[] args) {

	}

}
