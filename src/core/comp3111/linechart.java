package core.comp3111;


/**
 *  The implementation of the LineChart class. 
 *  It store the line chart in javafx.scene.chart.LineChart.
 * 	B
 * 
 * @author YuenTing
 *
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class linechart extends xychart{
	/**
	 * Constructor of the LineChart
	 * Can extend it to more than one series
	 * 
	 * @param DataTable
	 * 			- the DataTable object. It should contains at least 2 Number type DataColumns.
	 * @param AxisLabels
	 * 			- The DataColumn names. Must pass 2 number type.
	 * 			- The 1st label is the x-axis, and the 2nd label is the y-axis.
	 * @param ChartName
	 * 			- Name of the LineChart
	 * @throws ChartException
	 */
	
	
	public linechart(DataTable DataTable, String[] AxisLabels, String ChartName) throws ChartException{		
		//Constructor of the parent class

		super(DataTable, AxisLabels, ChartName, "LineChart");
		
		/** Check: Must passed 2 DataColumn with Number Type */

		//Check: Must passed 2 DataColumn
		if (AxisLabels.length != 2) {
			throw new ChartException(this.ChartType, String.format("Inconsistent number of DataColumn: %d", AxisLabels.length));
		}
		//Initialize the attributes
		this.xlabel = AxisLabels[0];
		this.ylabel = AxisLabels[1];
		
		//initialize the DataColumns
		DataColumn[] dcs = {this.xdc, this.ydc};
		
		for (int i = 0; i < 2; i++) {
			DataColumn dc = DataTable.getCol(AxisLabels[i]);
			//Check if the DataColumn exists
			if (dc == null) {
				throw new ChartException(this.ChartType, String.format("Unexisted DataColumn named &s for DataTable %s! Try again!", 
																		AxisLabels[i], this.DataTableName)) ;
			}
			else {
				dcs[i] = DataTable.getCol(AxisLabels[i]);
			}
		}
		//Check if the size for every DataColumns are the same
		if (xdc.getSize() != ydc.getSize()) {
			throw new ChartException(this.ChartType, "DataColumns are of different size!");
		}
		//Initialize: Keep track of the size of DataColumn
		SizeOfdc = xdc.getSize();

		//Check: Both DataColumn must be number type
		if (this.xdc.getTypeName() != DataType.TYPE_NUMBER) {
			throw new ChartException(this.ChartType, String.format("Inconsistent Data Column type: "
								+ "x-axis should be Number Type (Current: '&s' DataColumn with type &s))",
								xlabel, this.xdc.getTypeName()));	
		}
		if (this.ydc.getTypeName() != DataType.TYPE_NUMBER) {
			throw new ChartException(this.ChartType, String.format("Inconsistent Data Column type: "
					+ "y-axis should be Number Type (Current: '&s' DataColumn with type &s))",
					ylabel, this.ydc.getTypeName()));
		}
		
		//Create the line chart from javafx	
		
		//Keep track of the Object[] from DataColumn
		Object[] xarray = xdc.getData();
		Object[] yarray = ydc.getData();
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel(xlabel);
		xAxis.setLabel(ylabel);
		
		this.xychart  = new LineChart<Number, Number> (xAxis, yAxis); 
		this.xychart.setTitle(this.ChartName); //title of the chart is the ChartName
		
		//Define a series 
		 XYChart.Series<Number, Number> series =  new XYChart.Series<Number, Number>();
		 
		 for (int i = 0; i < this.SizeOfdc; i ++) {
			 series.getData().add(new XYChart.Data<Number, Number>((Number)xarray[i], (Number)yarray[i]));
		 }
		//Add the series to the LineChart
		 this.xychart.getData().add(series);
		 
		/*
		//Define a series for each column
		for (String AxisLabel: AxisLabels) {
	        XYChart.Series<Number, Number> series = new XYChart.Series();
	        series.setName(AxisLabel);
	        DataColumn ydc  = DataTable.getCol(AxisLabel);
	        int SizeOfdc = ydc.getSize();
	        for (int i = 0; i < SizeOfdc; i++) {
	        	series.getData().add(new XYChart.Data<Number, Number>(xdc[i], ydc[i]);
	        }
	        xychart.getData().add(series);
			
			
		}*/
			
	}
	/**
	 * Set the ChartName. 
	 * 
	 * @param ChartName
	 * 		- the name of the line chart
	 * @return void
	 */
	public void SetChartName(String ChartName) {
		this.ChartName = ChartName;
		this.xychart.setTitle(ChartName);
		return;
		
	}
		
	//Attributes
	protected String xlabel;
	protected String ylabel;
	protected DataColumn xdc;
	protected DataColumn ydc;
	protected int SizeOfdc;

	
	
	
	
	
}