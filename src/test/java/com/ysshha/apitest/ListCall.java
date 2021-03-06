package com.ysshha.apitest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.junit.Assert;


public class ListCall extends AbstractCall {

    private static final Logger logger = LogManager.getLogger(ListCall.class);

    public void getList() {
        logger.trace("ListCall.getList()");

        RequestSpecification request = RestAssured.given();

        response = request.get("/order/list");
    }

    public void checkList() {
        logger.trace("ListCall.checkList()");

        Assert.assertTrue(checkResponseContentType("application/json"));
        Assert.assertTrue(checkResponseStatusCode(200));
        Assert.assertTrue(checkResponseStatusLine("HTTP/1.1 200 "));
        Assert.assertTrue(checkResponseHeadersContain("Connection=keep-alive"));
    }

    public void numOrdersInList(int i) {
        logger.trace("ListCall.ordersInList()");

        String str = response.asString();
        JSONArray array = new JSONArray(str);

        Assert.assertTrue(array.length() == i);
    }

    public void orderInList(int ordId, String cardNum, String passNum,  String ordStat, int amount) {
        logger.trace("ListCall.ordersInList()");

        String str = response.asString();
        //JSONArray array = new JSONArray(str);
        String sub = str.substring(1, str.length() - 1);

        Assert.assertTrue(checkResponseKeyValueExists(sub, "orderId", ordId));
        Assert.assertTrue(checkResponseKeyValueExists(sub, "cardNumber", cardNum));
        Assert.assertTrue(checkResponseKeyValueExists(sub, "passNumber", passNum));
        Assert.assertTrue(checkResponseKeyValueExists(sub, "orderStatus", ordStat));
        Assert.assertTrue(checkResponseKeyValueExists(sub, "amount", amount));
    }



}
