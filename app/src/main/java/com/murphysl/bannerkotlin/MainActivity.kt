package com.murphysl.bannerkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * MainActivity
 *
 * @author: MurphySL
 * @time: 2017/2/14 15:48
 */

class MainActivity : AppCompatActivity(){

    //private lateinit var banner :Banner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        initView()
    }

    private fun initView() {
        val imgs : List<String> = listOf<String>(
                "http://oh1zr9i3e.bkt.clouddn.com/public/16-11-26/13922823.jpg",
                "http://oh1zr9i3e.bkt.clouddn.com/public/16-11-26/87303803.jpg",
                "http://oh1zr9i3e.bkt.clouddn.com/public/16-11-26/87347346.jpg"
        )
        val titles : List<String> = listOf<String>(
                "1111111111111111111111111111",
                "222222222222222222222222222",
                "333333333333333333333333333"
        )
        banner.setTitles(titles)
        banner.setImgsByUrl(imgs)
        banner.start()
    }

}

