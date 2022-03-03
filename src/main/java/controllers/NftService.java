package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class NftService extends Base {

    public static Response createNft(Object nftData, String userToken) {
        return restAssuredHelper
                        .postRequest(nftData,
                                HeaderProvider.bearerToken(userToken),ContentType.JSON, EndPoints.CREATE_NFT);
    }

    public static Response getNft(String nftId, String userToken) {
        return restAssuredHelper
                .getRequestWithPathParam(setPathParams(nftId),
                        HeaderProvider.bearerToken(userToken), EndPoints.GET_SINGLE_NFT);
    }

    public static Response getAllNftsDetailsOfAnUser(String userId, String userToken) {

        Map<String,String> nftParamsMap = new HashMap<>();
        nftParamsMap.put("ownerId",userId );

        return restAssuredHelper
                        .getRequestWithQueryParam(HeaderProvider.bearerToken(userToken),
                                EndPoints.GET_ALL_NFTS_OF_AN_USER, nftParamsMap);
    }

    public static Response updateNft(String nftId, String userToken, Object nftData) {
        return restAssuredHelper
                        .putRequest(setPathParams(nftId),
                                HeaderProvider.bearerToken(userToken),
                                nftData,
                                ContentType.JSON,
                                EndPoints.UPDATE_NFT);
    }

    public static Response deleteNft(String nftId, String userToken) {
        return restAssuredHelper
                        .deleteRequest(setPathParams(nftId),
                                HeaderProvider.bearerToken(userToken), EndPoints.DELETE_NFT);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("nftId", pathParam);

        return userParamsMap;
    }
}