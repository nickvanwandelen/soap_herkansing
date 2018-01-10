package nl.hu.iac.soap.impl;

import nl.hu.iac.soap.wsinterface.*;

import javax.jws.WebService;

@WebService(endpointInterface = "nl.hu.iac.soap.wsinterface.BmiServiceInterface")
public class BmiServiceImpl implements BmiServiceInterface{
    @Override
    public BmiResponse calculateBmi(BmiRequest req) throws Fault {
        try{
            BmiResponse resp = new BmiResponse();
            resp.setResult(req.getWeight().doubleValue() / (req.getHeight().intValue() * (req.getHeight().intValue() * req.getHeight().intValue())));
            return resp;
        }
        catch(Exception e){
            BmiFault bmiFault = new BmiFault();
            bmiFault.setErrorCode((short) 1);
            throw new Fault("Something went horribly wrong while calculating your BMI! No offence...", bmiFault);
        }
    }
}
