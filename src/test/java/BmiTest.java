package test;

import nl.hu.iac.soap.impl.BmiServiceImpl;
import nl.hu.iac.soap.wsinterface.BmiRequest;
import nl.hu.iac.soap.wsinterface.Fault;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigInteger;

public class BmiTest {
    private BmiServiceImpl _bmiService;
    private BmiRequest _mockRequest;


    @Before
    public void setup(){
        _mockRequest = new BmiRequest();
        _mockRequest.setHeight(new BigInteger("200"));
        _mockRequest.setWeight(new BigInteger("85"));

        _bmiService = new BmiServiceImpl();
    }

    @Test
    public void testCalculateBmi() throws Fault {
        Assert.assertEquals(21.25, _bmiService.calculateBmi(_mockRequest).getResult(), 0);
    }

    @Test(expected = Fault.class)
    public void testCalculateBmiWithIncorrectHeight() throws Fault {
        _mockRequest.setHeight(null);
        Assert.assertEquals(21.25, _bmiService.calculateBmi(_mockRequest).getResult(), 0);
    }

    @Test(expected = Fault.class)
    public void testCalculateBmiWithIncorrectWidth() throws Fault {
        _mockRequest.setWeight(null);
        Assert.assertEquals(21.25, _bmiService.calculateBmi(_mockRequest).getResult(), 0);
    }
}
