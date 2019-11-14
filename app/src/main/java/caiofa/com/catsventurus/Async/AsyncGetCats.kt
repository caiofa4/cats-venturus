package caiofa.com.catsventurus.Async

import android.os.AsyncTask
import caiofa.com.catsventurus.Entities.Cat
import caiofa.com.catsventurus.Entities.Image
import caiofa.com.catsventurus.Intefaces.CatListInterface
import com.google.gson.Gson
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class AsyncGetCats constructor(private val catListInterface: CatListInterface) : AsyncTask <String, String, String>(){

    override fun doInBackground(vararg url: String?): String {
        var text = ""
        val connection = URL(url[0]).openConnection() as HttpURLConnection
        connection.setRequestProperty("Authorization", "Client-ID " + url[1])

        try {
            connection.connect()
            text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
        } catch (e: Exception){

        }finally {
            connection.disconnect()
        }
        return text
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        catListInterface.onCatList(handleJson(result))
    }

    private fun handleJson(jsonString: String) : List<Cat> {

        val catList = ArrayList<Cat>()

        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")


        var i = 0
        while ( i < jsonArray.length() ){

            val jsonObjectCat = jsonArray.getJSONObject(i)

            try {
                val jsonArrayCatImages = jsonObjectCat.getJSONArray("images")
                val jsonObjectCatImage = jsonArrayCatImages.getJSONObject(0)

                val gson = Gson()
                val image = gson.fromJson(jsonObjectCatImage.toString(), Image::class.java)

                val cat = gson.fromJson(jsonObjectCat.toString(), Cat::class.java)
                cat.image = image

                catList.add(cat)
            } catch (e: Exception){
                e.printStackTrace()
            }
            i++

        }

        return catList

    }
}