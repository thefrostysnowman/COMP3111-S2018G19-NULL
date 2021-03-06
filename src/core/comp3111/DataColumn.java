package core.comp3111;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Serializable;
import java.util.Arrays;

/**
 * DataColumn - A column of data. This class will be used by DataTable. It
 * stores the data values (data) and the its type (typeName). String constants
 * of type name are defined in DataType.
 * 
 * @author cspeter
 *
 */
public class DataColumn implements Serializable {

	
	private static final long serialVersionUID = Constants.SERIALIZABLE_VER;
	
	/**
	 * Constructor. Create an empty data column
	 */
	public DataColumn() {
		data = null;
		typeName = "";
	}

	/**
	 * Constructor. Create a data column by giving the typename and array of Object
	 * 
	 * @param typeName
	 *            - defined in DataType. Should be matched with the type of the
	 *            array element
	 * @param values
	 *            - any Java Object array
	 */
	public DataColumn(String typeName, Object[] values) {
		if (!DataType.CheckInDataType(typeName)) {
			System.err.println("Provided typeName is not included in the default DataType...");
			System.err.println("The typeName is set to Object...");
			set(DataType.TYPE_OBJECT, values);
		};
		set(typeName, values);
	}

	/**
	 * Associate a Java Object array (with the correct typeName) to DataColumn
	 * 
	 * @param typeName
	 *            - defined in DataType. Should be matched with the type of the
	 *            array element
	 * @param values
	 *            - any Java Object array
	 */
	public void set(String typeName, Object[] values) {
		if(DataType.CheckInDataType(typeName)) 	this.typeName = typeName;
		else System.err.println("typeName not in DataType. typeName setting denied...");
		data = values;
	}

	/**
	 * Get the data array
	 * 
	 * @return The Object[]. Developers need to downcast it based on the type name
	 */
	public Object[] getData() {
		return data;
	}

	/**
	 * Get the type name
	 * 
	 * @return the type name, defined in DataType
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * Get the number of elements in the data array
	 * 
	 * @return 0 if data is null. Otherwise, length of the data array
	 */
	public int getSize() {
		if (data == null)
			return 0;
		return data.length;
	}
	
	
	/**
	 * Check if the data is integer[] type
	 * @return true if yes, false otherwise
	 */
	public boolean isInteger() {

		if (!typeName.equals(DataType.TYPE_NUMBER)) {

			return false;
		}
		
		
		double[] stringArrayForStoreDouble = new double[data.length];
		for(int index=0;index<data.length;index++) {
			if(data[index] instanceof Integer) {
				stringArrayForStoreDouble[index]=(double)((Integer)data[index]).intValue();
			} else if(data[index] instanceof Double) {
				stringArrayForStoreDouble[index]=((Double)data[index]).doubleValue();
			} else if(data[index] instanceof Byte){
				System.err.println("The casting type is not support - only Integer/Double are supported - " + data[index].getClass().toString());
				return false;
				//stringArrayForStoreDouble[index]=((Byte)data[index]).doubleValue();
			} else if(data[index] instanceof Short){
				System.err.println("The casting type is not support - only Integer/Double are supported - " + data[index].getClass().toString());
				return false;
				//stringArrayForStoreDouble[index]=((Short)data[index]).doubleValue();
			} else {
				System.err.println("The casting type is not support - only Integer/Double are supported - " + data[index].getClass().toString());
				return false;
				}
		}
		
		for (double d : stringArrayForStoreDouble) {
			if(Math.ceil(d) != Math.floor(d)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Compare two data columns by value
	 * 
	 * @return true if both column data and type is equal
	 */
	@Override
	public boolean equals(Object obj) {
		DataColumn otherDataColumn = (DataColumn) obj;
		return Arrays.equals(data,otherDataColumn.data) && typeName.equals(otherDataColumn.typeName);
	}
	
	
	// attributes
	private Object[] data;
	private String typeName;

}
