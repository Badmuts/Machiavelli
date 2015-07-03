package Machiavelli.Controllers;

import java.rmi.RemoteException;

import Machiavelli.Models.PuntenModel;
import Machiavelli.Views.PuntenView;

public class PuntenController {
    private PuntenModel puntenModel;

    public PuntenController(PuntenModel puntenModel) throws RemoteException {
        PuntenView puntenView = new PuntenView(this);
        this.puntenModel = puntenModel;
        
    }
}
