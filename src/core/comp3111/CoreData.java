package core.comp3111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import core.comp3111.DataTable;

/**
 * Data storage class for the application.  Main property is a 2D matrix of DataTable objects.
 * 
 * @author michaelfrost
 *
 */
public class CoreData implements Serializable {
	
	// Defines
	public static final long serialVersionUID = 1;
	
	// Class variables
	private ArrayList<ArrayList<DataTable>> masterTableList; //👺
	//TO-DO:After merge, change the Object type to xychart which is defined by Cherry
	private HashMap<String, ArrayList<Object>> blah;

	// Initializer
	public CoreData() {
		masterTableList = new ArrayList<ArrayList<DataTable>>();
	}
	
	/**
	 * Add a new inner list of data tables and a parent data table
	 * 
	 * @param table
	 * 			The DataTable
	 * @return the index in which the table was added
	 */
	public int[] addParentTable(DataTable table) {
		int[] newParentIndex = {Constants.EMPTY, Constants.EMPTY};
		
		if (table != null)
		{
			ArrayList<DataTable> inner = new ArrayList<DataTable>();
			inner.add(table);
			masterTableList.add(inner);
			newParentIndex[Constants.OUTER] = masterTableList.size() - 1;
			newParentIndex[Constants.INNER] = 0;
		}
		
		return newParentIndex;
	}
	
	/**
	 * Add a new child table to the specified outer list
	 * 
	 * @param table
	 *            The DataTable
	 * @return child table index
	 */
	public int[] addChildTable(DataTable table, int parentIndex) {
		int[] newChildIndex = {Constants.EMPTY,Constants.EMPTY};
		
		if (masterTableList.size() > parentIndex && table != null)
		{
			masterTableList.get(parentIndex).add(table);
			newChildIndex[Constants.INNER] = masterTableList.get(parentIndex).size() - 1;
			newChildIndex[Constants.OUTER] = parentIndex;
		}
		
		return newChildIndex;
	}
	
	/**
	 * Fetch the ArrayList of DataTables by outer index 
	 * 
	 * @param index
	 * 			The index of the data table ArrayList in outer Arraylist
	 * @return the inner ArrayList
	 */
	public ArrayList<DataTable> getInnerList(int outerIndex) {
		ArrayList<DataTable> tableList = null;
		
		// Return the ArrayList of the index so long as the index can have contents
		if (masterTableList.size() > outerIndex) {
			tableList = masterTableList.get(outerIndex);
		}
		
		return tableList;
	}
	
	/**
	 * Fetch the DataTable by inner and outer index
	 * 
	 * @param outerIndex 
	 * 				int that represents outer ArrayList index to access 
	 * @param innerIndex 
	 * 				int that represents inner ArrayList index to access
	 * @return the DataTable
	 */
	public DataTable getDataTable(int[] index) {
		DataTable table = null;
		
		// Chain of if statements to protect against nulls
		if (masterTableList.size() > index[Constants.OUTER] && 
				masterTableList.get(index[Constants.OUTER]) != null &&
				masterTableList.get(index[Constants.OUTER]).size() > index[Constants.INNER] &&
				masterTableList.get(index[Constants.OUTER]).get(index[Constants.INNER]) != null) {
			
			table = masterTableList.get(index[Constants.OUTER]).get(index[Constants.INNER]);
		}
		
		return table;
	}
	
	/**
	 * Return the size of the inner ArrayList, meaning the number of related parent and children DataTables
	 * 
	 * @param innerIndex
	 * 			The array index on the outer ArrayList
	 * @return
	 */
	public int getInnerSize(int innerIndex) {
		int size = Constants.EMPTY;
		
		// Make sure there is a ArrayList at the desired outer index
		if (innerIndex < masterTableList.size()) {
			size = masterTableList.get(innerIndex).size();
		}
		
		return size;
	}
	
	public int getOuterSize() {
		return masterTableList.size();
	}
	
	/**
	 * Sets the DataTable referenced by index to the passed DataTable
	 * 
	 * @param index
	 * 			Outer and Inner index of the DataTable in question
	 * @param table
	 * 			The DataTable that will be stored
	 */
	public boolean setDataTable(int[] index, DataTable table) {
		boolean success = false;
		if (index[Constants.OUTER] >= 0 && 
				index[Constants.INNER] >= 0 && 
				masterTableList.size() > index[Constants.OUTER] &&
				masterTableList.get(index[Constants.OUTER]).size() > index[Constants.INNER] ) {
			
			masterTableList.get(index[Constants.OUTER]).set(index[Constants.INNER], table);
			success = true;
		}
		return success;
	}
	
	/**
	 * Searches through the contained DataTables for a table of the supplied name. 
	 * 
	 * @param name
	 * 			DataTable name that is being searched for
	 * @return [outer, inner] indices of the desired DataTable, [-1,-1] if not found
	 */
	public int[] searchForDataTable(String name) { 
		int[] indices = {Constants.EMPTY,Constants.EMPTY};
		boolean found = false;
		
		// Iterate through the ArrayList searching for the desired DataTable
		// Set bounds to outer ArrayList
		for (int outerIndex = 0; outerIndex < masterTableList.size() && !found; outerIndex++) {
			// Look deeper if there is content here
			if (masterTableList.get(outerIndex) != null) {
				// We now need to look at parent and children DataTables
				for (int innerIndex = 0; innerIndex < masterTableList.get(outerIndex).size() && !found; innerIndex++) {
					// Look deeper if there is content and finally evaluate comparison
					if (masterTableList.get(outerIndex).get(innerIndex) != null && 
							masterTableList.get(outerIndex).get(innerIndex).getTableName().equalsIgnoreCase(name)) {
						
						indices[Constants.OUTER] = outerIndex;
						indices[Constants.INNER] = innerIndex;
						found = true;
					}
				}
			}
		}
		
		return indices;
	}
}
