import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import request.UpgradeLoginRequestPayload;
import org.testng.annotations.BeforeClass;


import static io.restassured.http.ContentType.JSON;

public class BaseTest {

    UpgradeLoginRequestPayload req= new UpgradeLoginRequestPayload();
    ResponseSpecification resSpec;
    RequestSpecification reqSpec;

    @BeforeClass
    public void setUp() {
        reqSpec = getRequestSpecification();
        reqSpec.contentType(JSON);
        reqSpec.header(Constants.SOURCE_ID, Constants.SOURCE_ID_VALUE);
        reqSpec.header(Constants.CORR_ID,Constants.UUID);
        resSpec = getResponseSpecification();
    }

    public static RequestSpecification getRequestSpecification() {
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri(Constants.BASE_URL);
        RequestSpecification reqSpec = reqBuilder.build();
        return reqSpec;
    }

    public static ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        ResponseSpecification resSpec = resBuilder.build();
        return resSpec;
    }

}
