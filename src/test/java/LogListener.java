import io.qameta.allure.Attachment;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;

public class LogListener implements IInvokedMethodListener {
    public static final ByteArrayOutputStream request = new ByteArrayOutputStream();
    public static final ByteArrayOutputStream response = new ByteArrayOutputStream();

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            logRequest(request);
            logResponse(response);
        }
    }

    @Attachment(value = "request")
    public static byte[] logRequest(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    @Attachment(value = "response")
    public static byte[] logResponse(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    private static byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
}