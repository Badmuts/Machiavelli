package Models;

import Factories.GebouwFactory;
import javafx.stage.Stage;

public class Spel {
	private Stage primaryStage;
	private Bank bank;
	private GebouwFactory gebouwFactory;
	
	public Spel(Stage primaryStage){
		bank = new Bank();
		gebouwFactory = new GebouwFactory();
		
		this.primaryStage = primaryStage;
	}
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	public void NieuwSpel(){
	}
	public void EindeBeurt(){
		
	}
	
	public Bank getBank()
	{
		return this.bank;
	}
	
	public GebouwFactory getGebouwFactory()
	{
		return this.gebouwFactory;
	}
}
