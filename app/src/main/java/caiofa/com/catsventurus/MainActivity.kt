package caiofa.com.catsventurus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import caiofa.com.catsventurus.Adapter.CatListAdapter
import caiofa.com.catsventurus.Async.AsyncGetCats
import caiofa.com.catsventurus.Entities.Cat
import caiofa.com.catsventurus.Intefaces.CatListInterface
import caiofa.com.catsventurus.Util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.JustifyContent


class MainActivity : AppCompatActivity(), CatListInterface  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        loadDataFromAsync()

    }


    private fun loadDataFromAsync(){
        AsyncGetCats(this).execute( Constants.WS.URL, Constants.WS.CLIENT_ID )
    }

    override fun onCatList(catList: List<Cat>) {
        loadScreen(catList)
    }

    private fun loadScreen(catList: List<Cat>){


        val layoutManager = FlexboxLayoutManager()
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.setAlignItems(AlignItems.BASELINE);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        rvCats.setLayoutManager(layoutManager)

        rvCats.adapter = CatListAdapter( catList)
    }

}
