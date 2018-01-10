package nl.hu.iac.soap.impl;

import nl.hu.iac.soap.wsinterface.*;

import javax.jws.WebService;

@WebService(endpointInterface = "nl.hu.iac.soap.wsinterface.BmiServiceInterface")
public class BmiServiceImpl implements BmiServiceInterface{
    /** Calculates the BMI for the SOAP service based on the height and length of the user.
     * The BMI calculation is done via this formula:
     * BMI = weight / (length * length)
     *
     *
     * @param req incoming SOAP request
     * @return the calculated BMI in a double format
     * @throws Fault any error that can occur during the calculation is thrown
     */
    @Override
    public BmiResponse calculateBmi(BmiRequest req) throws Fault {
        try{
            BmiResponse resp = new BmiResponse();
            double height = req.getHeight().doubleValue() / 100; //Divide the given height by 100 to convert to meters instead of centimeters.

            resp.setResult(req.getWeight().doubleValue() / (height * height)); //calculate the BMI
            return resp;
        }
        catch(Exception e){
            BmiFault bmiFault = new BmiFault();
            bmiFault.setErrorCode((short) 1);
            throw new Fault("Something went horribly wrong while calculating your BMI! No offence..." + "\n Error: " + e.getMessage(), bmiFault);
        }
    }
}
