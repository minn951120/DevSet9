package set;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IntSetBstTest {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNegativeSize() {
    	IntSetBst bst = new IntSetBst(-1, 0);
    }

    @Test
    public void testSetMaxElement() {
        IntSetBst bst = new IntSetBst(10, 100);
        assertThat(bst.getMaxElement(), is(equalTo(10)));
    }

    @Test
    public void testSetMaxValue() {
        IntSetBst bst = new IntSetBst(10, -1);
        assertThat(bst.getMaxValue(), is(equalTo(-1)));
    }

    @Test
    public void testNewbstSize() {
        IntSetBst bst = new IntSetBst(100, 100);
        assertThat(bst.size(), is(equalTo(0)));
    }

    @Test
    public void testInsert() {
        IntSetBst bst = new IntSetBst(100, 100);
        bst.insert(10);

        assertThat(bst.size(), is(equalTo(1)));
    }

    @Test
    public void testMultipleInsert() {
        IntSetBst bst = new IntSetBst(100, 100);

        for (int i = 0; i < 10; ++i) {
            bst.insert(i);
        }

        assertThat(bst.size(), is(equalTo(10)));
    }

    @Test
    public void testDuplicateInsert() {
        IntSetBst bst = new IntSetBst(100, 100);
        bst.insert(10);
        bst.insert(10);

        assertThat(bst.size(), is(equalTo(1)));
    }

    @Test
    public void testDuplicateMultipleInsert() {
        IntSetBst bst = new IntSetBst(100, 100);

        for (int i = 0; i < 10; ++i) {
            bst.insert(i);
            bst.insert(i);
        }

        assertThat(bst.size(), is(equalTo(10)));
    }

    @Test
    public void testOverMaxElementInsert() {
        IntSetBst bst = new IntSetBst(10, 100);

        for (int i = 0; i < 1000; ++i) {
            bst.insert(i);
        }

        assertThat(bst.size(), is(equalTo(10)));
    }

    @Test
    public void testOverMaxValueInsert() {
        IntSetBst bst = new IntSetBst(10, -1);

        for (int i = 0; i < 1000; ++i) {
            bst.insert(i);
        }

        assertThat(bst.size(), is(equalTo(0)));
    }

    @Test
    public void testNormalElementsReport() {
        IntSetBst bst = new IntSetBst(15, 10);
        int[] elements = { 2, 3, 10, 4, 5, 7, 6, 1, 9, 8 };

        for (int element : elements) {
            bst.insert(element);
        }

        int[] reportedElement = new int[bst.size()];
        bst.report(reportedElement);

        // verify each sorted elements.
        Arrays.sort(elements);
        for (int i = 0; i < bst.size(); ++i) {
            assertThat(reportedElement[i], is(equalTo(elements[i])));
        }
    }

    @Test
    public void testReverseSortedElementsReport() {
        IntSetBst bst = new IntSetBst(15, 10);

        // insert elements from 10 to 0.
        for (int i = 10; i >= 0; --i) {
            bst.insert(i);
        }

        int[] reportedElement = new int[bst.size()];
        bst.report(reportedElement);

        // verify each sorted elements.
        for (int i = 0; i < 10; ++i) {
            assertThat(reportedElement[i], is(equalTo(i)));
        }
    }

    @Test
    public void testAlreadySortedElementsReport() {
        IntSetBst bst = new IntSetBst(100, 100);

        // insert elements from 0 to 100.
        for (int i = 0; i < 100; ++i) {
            bst.insert(i);
        }

        int[] reportedElement = new int[bst.size()];
        bst.report(reportedElement);

        // verify each sorted elements.
        for (int i = 0; i < 100; ++i) {
            assertThat(reportedElement[i], is(equalTo(i)));
        }
    }

    @Test
    public void testDuplicateElementsReport() {
        IntSetBst bst = new IntSetBst(15, 10);
        int[] elements = { 1, 4, 1, 4, 2, 3, 2, 3 };

        for (int element : elements) {
            bst.insert(element);
        }

        int[] reportedElement = new int[bst.size()];
        bst.report(reportedElement);

        assertThat(reportedElement[0], is(equalTo(1)));
        assertThat(reportedElement[1], is(equalTo(2)));
        assertThat(reportedElement[2], is(equalTo(3)));
        assertThat(reportedElement[3], is(equalTo(4)));
    }
}
