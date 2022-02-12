import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;

public class LogListener implements ITestListener {
    public static final ByteArrayOutputStream request = new ByteArrayOutputStream();
    public static final ByteArrayOutputStream response = new ByteArrayOutputStream();

    @Override
    public void onTestSuccess(ITestResult result) {
        logRequest(request);
        logResponse(response);
    }

    public void onTestFailure(ITestResult iTestResult) {
        logRequest(request);
        logResponse(response);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("FINISH");
    }

    @Attachment(value = "request")
    public static byte[] logRequest(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    @Attachment(value = "response")
    public static byte[] logResponse(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    public static byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
}