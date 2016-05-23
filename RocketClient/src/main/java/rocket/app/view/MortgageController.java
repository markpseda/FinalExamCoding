package rocket.app.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable{

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	public MortgageController(){
	}
	//	Create private instance variables for:
	@FXML
	private Button btnPayment;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox loanTerm;
	
	
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblTerm;
	@FXML
	private Label lblDownPayment;
	
	@FXML
	public Label lblError;
	
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		if(loanTerm.getSelectionModel().getSelectedItem().toString() == "15 Year")
			lq.setiTerm(180);
		else
			lq.setiTerm(360);
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double PaymentPossible = lRequest.getdIncome()*.28;
		double OtherPaymentPossible = (lRequest.getdIncome() - lRequest.getdExpenses())*.36;
		double FinalPaymentPossible;
		if(PaymentPossible < OtherPaymentPossible){
			FinalPaymentPossible = PaymentPossible;
		}
		else {
			FinalPaymentPossible = OtherPaymentPossible;
		}
	

		double payment = lRequest.getdPayment();
		String output;
		if(payment == 0){
			output = "Credit Score too Low";
			lblError.setText(output);
		}
		else if(FinalPaymentPossible > payment){
			output = new DecimalFormat("#.##").format(payment);
			String APR = String.valueOf(lRequest.getdRate());
			lblError.setText("Your mortgage payment will be: $" + output + " and your APR is " + APR + "%");
		}
		else{
			output = "House Cost Too High.";
			lblError.setText(output);
		}

	}

	ObservableList<String> list = FXCollections.observableArrayList("15 Year","30 Year");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loanTerm.setItems(list);	
	}
}
