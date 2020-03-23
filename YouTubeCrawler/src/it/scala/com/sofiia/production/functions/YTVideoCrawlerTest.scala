package com.sofiia.production.functions

import java.io.FileInputStream
import java.util.Properties

import com.sofiia.production.functions.io.FileReader

class YTVideoCrawlerTest extends YTTest {
  test("YTVideoCrawler.allVideoItemsAreCrawled"){
    val properties: Properties = new Properties()
    properties.load(new FileInputStream(fileName))
    val mostPopularVideosFileName = properties.getProperty("mostPopularVideoFile")
    val file = FileReader.read(mostPopularVideosFileName)
    val videoList = new YTVideoListParser().fromJson(file)
    assert(videoList.size.equals(properties.getProperty("numberOfVideos").toInt))
  }
}
