package com.sofiia.production.functions

import com.sofiia.production.DOM.YTVideo
import org.scalatest.FunSuite

class YTVideoListParserTest extends FunSuite{

  test("YTVideoListParser.fromJson"){

    val title = "Eminem - Lose Yourself [HD]"
    val likes = 4586209
    val dislikes = 148105
    val views = 807968505
    val channelTitle = "msvogue23"
    val expectedResult = List(YTVideo(title, likes, dislikes, views, channelTitle))

    val JSONVideoString = "{ \"items\": [{\n  \"contentDetails\" : {\n    \"caption\" : \"false\",\n    \"definition\" : \"hd\",\n    \"dimension\" : \"2d\",\n    \"duration\" : \"PT5M24S\",\n    \"licensedContent\" : false,\n    \"projection\" : \"rectangular\"\n  },\n  \"etag\" : \"\\\"SJZWTG6xR0eGuCOh2bX6w3s4F94/umKZKhzLS89k4rCxmL6nyMW5RYI\\\"\",\n  \"id\" : \"_Yhyp-_hX2s\",\n  \"kind\" : \"youtube#video\",\n  \"snippet\" : {\n    \"categoryId\" : \"24\",\n    \"channelId\" : \"UCstaTFTqZAC_OqfAq_JF6vA\",\n    \"channelTitle\" : \"msvogue23\",\n    \"description\" : \"feat. Eminem from the movie 8 MILE\\n\\nNo copyright infringement intended. All contents belong to its rightful owners. This is for entertainment purposes only.\",\n    \"liveBroadcastContent\" : \"none\",\n    \"localized\" : {\n      \"description\" : \"feat. Eminem from the movie 8 MILE\\n\\nNo copyright infringement intended. All contents belong to its rightful owners. This is for entertainment purposes only.\",\n      \"title\" : \"Eminem - Lose Yourself [HD]\"\n    },\n    \"publishedAt\" : \"2015-08-07T01:12:12.000Z\",\n    \"tags\" : [ \"Lose Yourself (Musical Recording)\", \"Hip Hop Music (Musical Genre)\", \"Eminem (Music Video Performer)\", \"Rap Rock (Musical Genre)\", \"Music (TV Genre)\", \"8 Mile (Film)\", \"8 Mile (Soundtrack)\", \"The Slim Shady LP (Musical Album)\", \"official\", \"Trailer (Website Category)\", \"MTV (TV Network)\", \"Music Video (TV Genre)\", \"libra\", \"The Marshall Mathers LP (Musical Album)\" ],\n    \"thumbnails\" : {\n      \"default\" : {\n        \"height\" : 90,\n        \"url\" : \"https://i.ytimg.com/vi/_Yhyp-_hX2s/default.jpg\",\n        \"width\" : 120\n      },\n      \"high\" : {\n        \"height\" : 360,\n        \"url\" : \"https://i.ytimg.com/vi/_Yhyp-_hX2s/hqdefault.jpg\",\n        \"width\" : 480\n      },\n      \"maxres\" : {\n        \"height\" : 720,\n        \"url\" : \"https://i.ytimg.com/vi/_Yhyp-_hX2s/maxresdefault.jpg\",\n        \"width\" : 1280\n      },\n      \"medium\" : {\n        \"height\" : 180,\n        \"url\" : \"https://i.ytimg.com/vi/_Yhyp-_hX2s/mqdefault.jpg\",\n        \"width\" : 320\n      },\n      \"standard\" : {\n        \"height\" : 480,\n        \"url\" : \"https://i.ytimg.com/vi/_Yhyp-_hX2s/sddefault.jpg\",\n        \"width\" : 640\n      }\n    },\n    \"title\" : \"Eminem - Lose Yourself [HD]\"\n  },\n  \"statistics\" : {\n    \"commentCount\" : \"171597\",\n    \"dislikeCount\" : \"148105\",\n    \"favoriteCount\" : \"0\",\n    \"likeCount\" : \"4586209\",\n    \"viewCount\" : \"807968505\"\n  }\n}]}"
    val actualResult = new YTVideoListParser().fromJson(JSONVideoString)

    assert( actualResult == expectedResult)
  }

  test("YTVideoListParser.throwsException"){
    assertThrows[NullPointerException](new YTVideoListParser().fromJson(""))
  }

}
