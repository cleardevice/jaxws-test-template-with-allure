import com.my.soap.ws.client.NumberConversion;
import com.my.soap.ws.client.NumberConversionSoapType;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.Handler;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

//@Listeners(LogListener.class)
public class SaleOrderResponseTest {
    private NumberConversionSoapType getPort() {
        NumberConversion service = new NumberConversion();
        NumberConversionSoapType numberConversionSoap = service.getNumberConversionSoap();

        Binding binding = ((BindingProvider) numberConversionSoap).getBinding();
        List<Handler> handlerChain = binding.getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler(LogListener.request, LogListener.response));
        binding.setHandlerChain(handlerChain);

        return numberConversionSoap;
    }

    @Test
    public void testNumberConversion1() {
        String result = getPort().numberToWords(BigInteger.valueOf(1234));
        Assert.assertEquals(result.trim(), "one thousand two hundred and thirty four");
        LogListener.logRequest(LogListener.request);
        LogListener.logResponse(LogListener.response);
    }

    @Test
    public void testNumberConversion2() {
        String result = getPort().numberToDollars(BigDecimal.valueOf(123.45));
        Assert.assertEquals(result, "one hundred and twenty three dollars and forty five cents");
    }
}
