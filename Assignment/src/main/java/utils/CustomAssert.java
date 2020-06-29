package utils;

import base.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class CustomAssert extends SoftAssert {

    @Override
    public void onAssertSuccess(IAssert<?> iAssert) {
        super.onAssertSuccess(iAssert);
        if (iAssert.getExpected()==null){
            TestBase.getTestlogger().log(Status.PASS,"Assertion passed::"+iAssert.getMessage() + "|| Actual :"+ iAssert.getActual());
        }else{
            TestBase.getTestlogger().log(Status.PASS,"Assertion passed::"+iAssert.getMessage() +"|| Expected :"+iAssert.getExpected()+ "|| Actual :"+ iAssert.getActual());

        }
    }

    @Override
    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        super.onAssertFailure(iAssert,assertionError);
        TestBase.getTestlogger().log(Status.FAIL, "Assertion failed::" + iAssert.getMessage() + "|| Expected :" + iAssert.getExpected() + "|| Actual :" + iAssert.getActual());
    }
}
