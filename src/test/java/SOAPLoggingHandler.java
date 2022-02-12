import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.util.Set;

public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {
    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (isRequest) {
            System.out.println("is Request");
        } else {
            System.out.println("is Response");
        }
        SOAPMessage message = context.getMessage();
        try {
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer();
            // Format it
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            final ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
            final StreamResult result = new StreamResult(streamOut);
            transformer.transform(message.getSOAPPart().getContent(), result);
            String formattedSoap = streamOut.toString();

            System.out.println(formattedSoap);
        } catch (TransformerException | SOAPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }

}
