package set;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IntSetListTest {
    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNegativeSize() {
        IntSetList list = new IntSetList(-1, 0);
    }

    @Test
    public void testSetMaxElement() {
        IntSetList list = new IntSetList(10, 100);
        assertThat(list.getMaxElement(), is(equalTo(10)));
    }

    @Test
    public void testSetMaxValue() {
        IntSetList list = new IntSetList(10, -1);
        assertThat(list.getMaxValue(), is(equalTo(-1)));
    }

    @Test
    public void testNewListSize() {
        IntSetList list = new IntSetList(100, 100);
        assertThat(list.size(), is(equalTo(0)));
    }

    @Test
    public void testInsert() {
        IntSetList list = new IntSetList(100, 100);
        list.insert(10);

        assertThat(list.size(), is(equalTo(1)));
    }

    @Test
    public void testMultipleInsert() {
        IntSetList list = new IntSetList(100, 100);

        for (int i = 0; i < 10; ++i) {
            list.insert(i);
        }

        assertThat(list.size(), is(equalTo(10)));
    }

    @Test
    public void testDuplicateInsert() {
        IntSetList list = new IntSetList(100, 100);
        list.insert(10);
        list.insert(10);

        assertThat(list.size(), is(equalTo(1)));
    }

    @Test
    public void testDuplicateMultipleInsert() {
        IntSetList list = new IntSetList(100, 100);

        for (int i = 0; i < 10; ++i) {
            list.insert(i);
            list.insert(i);
        }

        assertThat(list.size(), is(equalTo(10)));
    }

    @Test
    public void testOverMaxElementInsert() {
        IntSetList list = new IntSetList(10, 100);

        for (int i = 0; i < 1000; ++i) {
            list.insert(i);
        }

        assertThat(list.size(), is(equalTo(10)));
    }

    @Test
    public void testOverMaxValueInsert() {
        IntSetList list = new IntSetList(10, -1);

        for (int i = 0; i < 1000; ++i) {
            list.insert(i);
        }

        assertThat(list.size(), is(equalTo(0)));
    }

    @Test
    public void testNormalElementsReport() {
        IntSetList list = new IntSetList(15, 10);
        int[] elements = { 2, 3, 10, 4, 5, 7, 6, 1, 9, 8 };

        for (int element : elements) {
            list.insert(element);
        }

        int[] reportedElement = new int[list.size()];
        list.report(reportedElement);

        // verify each sorted elements.
        Arrays.sort(elements);
        for (int i = 0; i < list.size(); ++i) {
            assertThat(reportedElement[i], is(equalTo(elements[i])));
        }
    }

    @Test
    public void testReverseSortedElementsReport() {
        IntSetList list = new IntSetList(15, 10);

        // insert elements from 10 to 0.
        for (int i = 10; i >= 0; --i) {
            list.insert(i);
        }

        int[] reportedElement = new int[list.size()];
        list.report(reportedElement);

        // verify each sorted elements.
        for (int i = 0; i < 10; ++i) {
            assertThat(reportedElement[i], is(equalTo(i)));
        }
    }

    @Test
    public void testAlreadySortedElementsReport() {
        IntSetList list = new IntSetList(100, 100);

        // insert elements from 0 to 100.
        for (int i = 0; i < 100; ++i) {
            list.insert(i);
        }

        int[] reportedElement = new int[list.size()];
        list.report(reportedElement);

        // verify each sorted elements.
        for (int i = 0; i < 100; ++i) {
            assertThat(reportedElement[i], is(equalTo(i)));
        }
    }

    @Test
    public void testDuplicateElementsReport() {
        IntSetList list = new IntSetList(15, 10);
        int[] elements = { 1, 4, 1, 4, 2, 3, 2, 3 };

        for (int element : elements) {
            list.insert(element);
        }

        int[] reportedElement = new int[list.size()];
        list.report(reportedElement);

        assertThat(reportedElement[0], is(equalTo(1)));
        assertThat(reportedElement[1], is(equalTo(2)));
        assertThat(reportedElement[2], is(equalTo(3)));
        assertThat(reportedElement[3], is(equalTo(4)));
    }
}
