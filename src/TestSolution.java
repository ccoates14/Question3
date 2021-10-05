import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestSolution {

    @ParameterizedTest
    @MethodSource("testScenarioProvider")
    public void testAddNumbersAsLinkedList(ListNode l1, ListNode l2, ListNode expectedAnswer) {
        compareResults(expectedAnswer, l1, l2);
    }

    @ParameterizedTest
    @MethodSource("testRandomScenarioProvider")
    public void testRandomNumbers(ListNode l1, ListNode l2, ListNode expectedAnswer) {
        compareResults(expectedAnswer, l1, l2);
    }

    private static void compareResults(ListNode expectedResult, ListNode l1, ListNode l2) {
        assertLinkedListsEquals(expectedResult, new Solution().addTwoNumbers(
                l1,
                l2
        ));
    }

    private static Stream<Arguments> testRandomScenarioProvider() {
        List<Arguments> arguments = new ArrayList<>();
        final int RANGE = 1000;

        for (int i = 0; i < 100; i++) {
            final int n1 = generateRandomNumberWithinRange(RANGE);
            final int n2 = generateRandomNumberWithinRange(RANGE);
            final ListNode expectedAnswer = generateListFromNumber(n1 + n2);

            ListNode l1 = generateListFromNumber(n1);
            ListNode l2 = generateListFromNumber(n2);

            arguments.add(Arguments.arguments(
                    l1,
                    l2,
                    expectedAnswer
            ));
        }

        return arguments.stream();

    }

    private static int generateRandomNumberWithinRange(int max) {
        return (int)(Math.random() * max);
    }

    private static ListNode generateListFromNumber(int num) {
        ListNode node = new ListNode(0);
        ListNode head = node;

        do {
            node.val = num % 10;
            num = num / 10;

            node.next = new ListNode(num);
            node = node.next;
        } while (num >= 10);

        return head;
    }

    private static Stream<Arguments> testScenarioProvider() {
        return Stream.of(
                Arguments.arguments(
                        arrayToLinkedList(new int[]{2,4,3}),
                        arrayToLinkedList(new int[]{5,6,4}),
                        arrayToLinkedList(new int[]{7,0,8})
                ),

                Arguments.arguments(
                        arrayToLinkedList(new int[]{1}),
                        arrayToLinkedList(new int[]{9, 9}),
                        arrayToLinkedList(new int[]{0,0,1})
                ),

                Arguments.arguments(
                        arrayToLinkedList(new int[]{1}),
                        arrayToLinkedList(new int[]{9,9,9, 9,9,9, 9,9,9, 9,9,9}),
                                arrayToLinkedList(new int[]{0,0,0, 0,0,0, 0,0,0, 0,0,0, 1})
                ),

                Arguments.arguments(
                        arrayToLinkedList(new int[]{4}),
                        arrayToLinkedList(new int[]{3, 5, 9}),
                        arrayToLinkedList(new int[]{7, 5, 9})
                ),

                Arguments.arguments(
                        arrayToLinkedList(new int[]{8, 0, 4}),
                        arrayToLinkedList(new int[]{3}),
                        arrayToLinkedList(new int[]{1, 1, 4})
                ),

                Arguments.arguments(
                        arrayToLinkedList(new int[]{7, 0, 1}),
                        arrayToLinkedList(new int[]{2}),
                        arrayToLinkedList(new int[]{9, 0, 1})
                )
        );
    }

    private static void assertLinkedListsEquals(ListNode l1, ListNode l2) {
        do {
            Assertions.assertNotNull(l1);
            Assertions.assertNotNull(l2);
            Assertions.assertEquals(l1.val, l2.val);

            l1 = l1.next;
            l2 = l2.next;
        } while (l1 != null || l2 != null);
    }

    private static ListNode arrayToLinkedList(int[] nums) {
        ListNode head = null;
        ListNode currentNode = null;

        for (int i : nums) {
            if (head == null) {
                head = currentNode = new ListNode(i);
            } else {
                currentNode.next = new ListNode(i);
                currentNode = currentNode.next;
            }
        }

        return head;
    }
}
