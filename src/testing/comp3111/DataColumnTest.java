package testing.comp3111;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import core.comp3111.DataColumn;
import core.comp3111.DataType;

/**
 * A sample DataColumn test case written using JUnit. It achieves 100% test
 * coverage on the DataColumn class
 * 
 * @author cspeter
 *
 */
class DataColumnTest {

	@Test
	void testCoverageEmptyDataColumnConstructor() {

		DataColumn dc = new DataColumn();
		assert (dc.getSize() == 0);

	}

	@Test
	void testCoverageNonEmptyDataColumnConstructor() {

		Number[] arr = new Integer[] { 1, 2, 3, 4, 5 };
		DataColumn dc = new DataColumn(DataType.TYPE_NUMBER, arr);
		assert (dc.getSize() == 5);

	}

	@Test
	void testCoverageGetDataAndType() {

		DataColumn dc = new DataColumn();
		assert (dc.getTypeName().equals(""));
		assert (dc.getData() == null);

	}
	
	@Test
	void testEquals_Not() {
		Number[] arr = new Integer[] { 1, 2, 3, 4, 5 };
		String[] arr2 = new String[] {"Heya","Heya","Heya","Heya","Heya"};
		DataColumn dc = new DataColumn(DataType.TYPE_NUMBER, arr);
		DataColumn dc2 = new DataColumn(DataType.TYPE_STRING,arr2);
		assertEquals(false,dc.equals(dc2));
	}
	
	@Test
	void testEquals_SameDT() {
		Number[] arr = new Integer[] { 1, 2, 3, 4, 5 };
		Number[] arr2 = new Integer[] { 6, 7, 8, 9, 10 };
		DataColumn dc = new DataColumn(DataType.TYPE_NUMBER, arr);
		DataColumn dc2 = new DataColumn(DataType.TYPE_NUMBER,arr2);
		assertEquals(false,dc.equals(dc2));
	}
	
	@Test
	void testEquals_DiffDT() {
		Number[] arr = new Integer[] { 1, 2, 3, 4, 5 };
		DataColumn dc = new DataColumn(DataType.TYPE_NUMBER, arr);
		DataColumn dc2 = new DataColumn(DataType.TYPE_STRING,arr);
		assertEquals(false,dc.equals(dc2));
	}
	
	@Test
	void testEquals_Same() {
		String[] arr = new String[] { "Hey", "There", "Whats", "Up"};
		String[] arr2 = new String[] { "Hey", "There", "Whats", "Up"};
		DataColumn dc = new DataColumn(DataType.TYPE_STRING, arr);
		DataColumn dc2 = new DataColumn(DataType.TYPE_STRING,arr2);
		assertEquals(true,dc.equals(dc2));
	}
}
