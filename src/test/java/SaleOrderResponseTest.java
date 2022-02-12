import com.my.soap.ws.client.NumberConversion;
import com.my.soap.ws.client.NumberConversionSoapType;
import jakarta.xml.ws.Binding;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.Handler;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Listeners(LogListener.class)
public class SaleOrderResponseTest {
    @Test
    public void testNumberConversion1() {
        NumberConversion service = new NumberConversion();
        NumberConversionSoapType numberConversionSoap = service.getNumberConversionSoap();

        String result = numberConversionSoap.numberToWords(BigInteger.valueOf(1234));
        System.out.println(result);
    }

    @Test
    public void testNumberConversion2() {
        NumberConversion service = new NumberConversion();
        NumberConversionSoapType numberConversionSoap = service.getNumberConversionSoap();

        Binding binding = ((BindingProvider) numberConversionSoap).getBinding();
        List<Handler> handlerChain = binding.getHandlerChain();
        handlerChain.add(new SOAPLoggingHandler());
        binding.setHandlerChain(handlerChain);

        numberConversionSoap.numberToDollars(BigDecimal.valueOf(123.45));
    }
}
