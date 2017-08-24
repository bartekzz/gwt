package com.exempel.martin.client;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */


public class ExempelProjekt implements EntryPoint {
  private VerticalPanel mainPanel = new VerticalPanel();
  private Grid myGrid = new Grid(5, 4);
  /*
  private HorizontalPanel buttonsPanel0 = new HorizontalPanel();
  private HorizontalPanel buttonsPanel1 = new HorizontalPanel();
  private HorizontalPanel buttonsPanel2 = new HorizontalPanel();
  private HorizontalPanel buttonsPanel3 = new HorizontalPanel();
  private HorizontalPanel buttonsPanel4 = new HorizontalPanel();
  */
  
  private FlexTable flexTable = new FlexTable();
  
  private double answer;
  private double operand1;
  private double operand2;
  
  private ArrayList<Button> buttonDigitsList;
  private ArrayList<Button> buttonOperatorsList;
  private ArrayList<Button> buttonOthersList;
  private Label valueLabel;
  private String value = "";
  private String operandValue1 = "";
  private String operandValue2 = "";
  private String operator = "";
  private int counter = 0;
  

	
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
	  
  	// Create Value Label
	  valueLabel = new Label("");
	  
	// Create table for stock data.
    flexTable.setText(0, 0, "Question");
    flexTable.setText(0, 1, "Answer");
	
	
	// Create array list for digits buttons
	buttonDigitsList = new ArrayList<>();
	// Create array list for digits buttons
	buttonOperatorsList = new ArrayList<>();
	// Add buttons
	for(int i = 9; i > 0; i--) {
	Button button = new Button(Integer.toString(i));
		buttonDigitsList.add(button);
		button.addStyleName("newWidth");
		if(i < 4) {
			//buttonsPanel1.add(button);
			myGrid.setWidget(3, i-1, button);
			}
		else if(i > 3 && i < 7) {
			//buttonsPanel2.add(button);
			myGrid.setWidget(2, i-4, button);
			}
		else if(i >= 7) {
			//buttonsPanel3.add(button);
			myGrid.setWidget(1, i-7, button);
		}
	}
 
	Button buttonAC = new Button("AC");
	Button buttonZero = new Button("0");
	Button buttonDecimal = new Button(",");
	Button buttonModulo = new Button("%");
	Button buttonDivision = new Button("/");
	Button buttonMultiplication = new Button("*");
	Button buttonAddition = new Button("+");
	Button buttonSubtraction = new Button("-");
	Button buttonAnswer = new Button("=");
	
	buttonAC.addStyleName("newWidth");
	buttonModulo.addStyleName("newWidth");
	buttonDivision.addStyleName("newWidth");
	buttonMultiplication.addStyleName("newWidth");
	buttonAddition.addStyleName("newWidth");
	buttonSubtraction.addStyleName("newWidth");
	buttonAnswer.addStyleName("newWidth");
	buttonDecimal.addStyleName("newWidth");
	buttonZero.addStyleName("newWidth");
	
	myGrid.getCellFormatter().setWidth(1, 1, "10px");
	
	
	//buttonsPanel4.add(buttonAC);
	myGrid.setWidget(0, 0, buttonAC);
	//buttonsPanel4.add(buttonModulo);
	myGrid.setWidget(0, 1, buttonModulo);
	//buttonsPanel4.add(buttonDivision);
	myGrid.setWidget(0, 2, buttonDivision);
	//buttonsPanel3.add(buttonMultiplication);
	myGrid.setWidget(1, 3, buttonMultiplication);
	//buttonsPanel2.add(buttonSubtraction);
	myGrid.setWidget(2, 3, buttonSubtraction);
	//buttonsPanel1.add(buttonAddition);
	myGrid.setWidget(3, 3, buttonAddition);
	//buttonsPanel0.add(buttonZero);
	myGrid.setWidget(4, 0, buttonZero);
	//buttonsPanel0.add(buttonDecimal);
	myGrid.setWidget(4, 1, buttonDecimal);
	//buttonsPanel0.add(buttonAnswer);
	myGrid.setWidget(4, 2, buttonAnswer);


	
	buttonOperatorsList.add(buttonModulo);
	buttonOperatorsList.add(buttonDivision);
	buttonOperatorsList.add(buttonMultiplication);
	buttonOperatorsList.add(buttonSubtraction);
	buttonOperatorsList.add(buttonAddition);
	
	buttonDigitsList.add(buttonZero);
	
	//buttonList.add(buttonAnswer);
	
	// Click handler for all buttons
	ClickHandler buttonDisplayHandler = new ClickHandler() {
		  public void onClick(ClickEvent event) {
			  Button button = (Button) event.getSource();
			  String buttonValue = button.getText();
			  displayValue(buttonValue);
			  // Set counter which controls operator and operands
			  String[] matches = new String[] {"/", "*", "+", "-", "%"};
			  for (String s : matches) {
			    if (buttonValue.contains(s)) {
			      setOperator(buttonValue);
			      counter = 2;
			      toggleOperators(false);
			      return;
			    }
			  }
    			  if(counter == 2) {
	    			setOperandValue2(buttonValue);
	    		  } else {
	    			setOperandValue1(buttonValue);
	    			counter = 1;
	    		  }  
		  }
		};
	
	// Add click handler to all buttons
	for(Button button : buttonDigitsList) {
		button.addClickHandler(buttonDisplayHandler);
	}
	
	// Add click handler to all buttons
	for(Button button : buttonOperatorsList) {
		button.addClickHandler(buttonDisplayHandler);
	}
	
	// Add click handler to decimal button
		
	buttonDecimal.addClickHandler(buttonDisplayHandler);
		
	
	// TODO Assemble Main panel.
	
	
	mainPanel.add(flexTable);
	mainPanel.add(valueLabel);
	mainPanel.add(myGrid);
	/*
	mainPanel.add(buttonsPanel4);
	mainPanel.add(buttonsPanel3);
	mainPanel.add(buttonsPanel2);
	mainPanel.add(buttonsPanel1);
	mainPanel.add(buttonsPanel0);
	*/
	// TODO Associate the Main panel with the HTML host page.
	RootPanel.get("calc").add(mainPanel);
	// TODO Move cursor focus to the input box.
	    
	    
	buttonAC.addClickHandler(new ClickHandler() {
	    public void onClick(ClickEvent event) {
    		  toggleOperators(true);
	      clearAllValues();
	    }});
	
	buttonAnswer.addClickHandler(new ClickHandler() {
	    public void onClick(ClickEvent event) {
	      calculate();
	      addCalculation();
	      toggleOperators(true);
	      clearAllValues();
	    }});
	    
	    // Listen for keyboard events on calculate button.
	    buttonAnswer.addKeyDownHandler(new KeyDownHandler() {
	    public void onKeyDown(KeyDownEvent event) {
	      if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    	  calculate();
	    	  addCalculation();
	    	  toggleOperators(true);
	    	  clearAllValues();
	      }
	    }
	  });
	       
  }
  
  public void clearAllValues() {
	  valueLabel.setText("");
	  value = "";
	  answer = 0;
	  operator = "";
	  operandValue1 = "";
	  operandValue2 = "";
	  counter = 0;
  }
  
  public void toggleOperators(boolean toggle) {
	  for(Button button : buttonOperatorsList) {
		  button.setEnabled(toggle);
	  }
  }
  
  // Set operator
  public void setOperator(String operatorValue) {
	  operator = operatorValue;
	  //Window.alert("Operator: "+ operator);
  }
  
  public String commaToDot(String input) {
	  String output = input.replace(",", ".");
	  return output;
  }
  
  public void setOperandValue1(String operand1) {
	  String operand = commaToDot(operand1);
	  operandValue1 += operand;
	  //Window.alert("Operand1: "+ operandValue1);
  }
  
  public void setOperandValue2(String operand2) {
	  String operand = commaToDot(operand2);
	  operandValue2 += operand;
	  //Window.alert("Operand2: "+ operandValue2);
  }
  
//Display value in valueLabel
	public void displayValue(String buttonValue) {
		value = value + buttonValue;
		valueLabel.setText(value);
	}
  

		private void calculate() {
			
			if(counter == 2) {
			
				 operand1=Double.parseDouble(operandValue1);
				 //Window.alert("Operand 1 (float): " + operand1);
				 operand2=Double.parseDouble(operandValue2);
				 //Window.alert("Operand 2 (float): " + operand2);
				 
				 //Multiplication
				 if(operator.equals("*"))
				 {
					 multiply(); 
				 }
				 //Modulo
				 else if (operator.equals("%"))
				 {
					 modulo();
				 }
				//Division
				 else if (operator.equals("/"))
				 {
					 divide();
				 }
				//Subtraction
				 else if (operator.equals("-"))
				 {
					 subtract(); 
				 }
				 //addition
				 else {
					 add();
				 }
			}
			 
		}
		
		private void add() {
			// TODO Auto-generated method stub
			answer=operand1+operand2;
			//Window.alert("The answer is: " + answer);
		}


		private void subtract() {
			// TODO Auto-generated method stub
			answer=operand1-operand2;
			//Window.alert("The answer is: " + answer);
		}


		private void divide() {
			// TODO Auto-generated method stub
			if(operand2 != 0) {
				 answer=operand1/operand2;
				 //Window.alert("The answer is: " + answer);
				 } else {
					 Window.alert("Cannot divide by 0. Undefined!");
				 }
		}


		private void modulo() {
			// TODO Auto-generated method stub
			answer=operand1%operand2;
			 //Window.alert("The answer is: " + answer);
		}


		private void multiply() {
			// TODO Auto-generated method stub
			answer=operand1*operand2;
			//Window.alert("The answer is: " + answer);
		}


		//Checks if a String could be seen as an integer
		public boolean isInteger( String input )
		{
		   try
		   {
		      Double.parseDouble( input );
		      return true;
		   }
		   catch(NumberFormatException e)
		   {
		      return false;
		   }
		}
		
		/**
		   * Add stock to FlexTable. Executed when the user clicks the addStockButton or
		   * presses enter in the newSymbolTextBox.
		   */
		private void addCalculation() {
			
			NumberFormat fmt = NumberFormat.getDecimalFormat();
			String formatted = fmt.format(answer);
			
		     
		      // Add the calculation answer to the table.
		      int row = flexTable.getRowCount();
		      //flexTable.setText(row, 0, Double.toString(answer));
		      flexTable.setText(row, 0, formatted);
	    }
			
		
}