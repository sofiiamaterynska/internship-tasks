import java.io.{BufferedWriter, File, FileWriter}

import com.google.api.services.youtube.{YouTube, YouTubeRequestInitializer}
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestInitializer



 object VideoData {

   val fileNameJSON = "youtube.json"

    // init google api access
    val transport = new NetHttpTransport()
    val factory = new JacksonFactory()
    val httpRequestInit = new HttpRequestInitializer {
       override def initialize(re: HttpRequest): Unit = {}
    }

    def main(args: Array[String]) {
       val service = new YouTube.Builder(transport, factory, httpRequestInit).setApplicationName("test")
         .setYouTubeRequestInitializer(new YouTubeRequestInitializer("AIzaSyC8ntHInRTmNaECJs9o3ySZMo58AVcugsg"))
         .build() //connection()
       val request = service.videos.list("snippet,contentDetails,statistics")
       val response = request.setChart("mostPopular").setRegionCode("US").execute
       writeToFile(fileNameJSON, response.toPrettyString)
       println(response.toPrettyString)
    }

    def writeToFile(fileName: String, textToWrite: String): Unit ={
       val file = new File(fileName)
       val bw = new BufferedWriter(new FileWriter(file))
       bw.write(textToWrite)
       bw.close()
    }
 }

