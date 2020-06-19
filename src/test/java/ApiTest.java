import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import response.Loan;
import response.User;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest extends BaseTest{

    @Test
    public void test_loginApi_with_good_credentials_returns200()  {
        req.setUsername("coding.challenge.login@upgrade.com");
        req.setPassword("On$3XcgsW#9q");
        given().log().all()
                .body(req)
                .contentType(ContentType.JSON)
                .header("x-cf-source-id", Constants.SOURCE_ID)
                .header("x-cf-corr-id", Constants.UUID).
                when()
                .post(Constants.BASE_URL+"/login").
                then()
                .statusCode(200);
    }

    @Test
    public void test_validate_response()  {
        req.setUsername("coding.challenge.login@upgrade.com");
        req.setPassword("On$3XcgsW#9q");
        Response response =given().log().all()
                .spec(reqSpec)
                .body(req)
                .when()
                .post(Constants.BASE_URL+"/login").
                then()
                .spec(resSpec)
                .extract()
                .response();

        User user=response.as(User.class, ObjectMapperType.GSON);
        List<Loan> loans=user.getLoansInReview();

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(user.getFirstName(), "Ian");
        softAssert.assertEquals(user.getUserId().toString(),"9114917");
        softAssert.assertEquals(user.getUserUuid(),"34c16f53-38c4-461a-bd14-11fa748d2663");
        softAssert.assertTrue(user.getLoanApplications().isEmpty(),"loan application not empty");

        for(Loan loan:loans){
            softAssert.assertEquals(loan.getId().toString(),"9545966");
            softAssert.assertTrue(loan.getProductType().equals("PERSONAL_LOAN"),"Product type mismatch");
            softAssert.assertEquals(loan.getCreateDate(),"2019-08-21T18:18:59.959Z");
            softAssert.assertFalse(loan.getHasOpenBackendCounter());
            softAssert.assertEquals(loan.getSourceSystem(),"BORROWER_FUNNEL_V2");
            softAssert.assertEquals(loan.getStatus(),"PENDING");
        }
        softAssert.assertAll();





    }

    @Test
    public void test_wrong_credentials_returns_401()  {
        req.setUsername("seti");
        req.setPassword("Password");
        given().log().all()
                .body(req)
                .contentType(ContentType.JSON)
                .header("x-cf-source-id", Constants.SOURCE_ID)
                .header("x-cf-corr-id", Constants.UUID).
                when()
                .post(Constants.BASE_URL+"/login").
                then()
                .statusCode(401);
    }

}
