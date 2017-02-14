package com.murphysl.bannerkotlin

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Banner
 *
 * @author: MurphySL
 * @time: 2017/2/14 15:51
 */

class Banner : RelativeLayout {

    private var textSize : Float? = null
    private var textColor : Int? = null
    private var indicator : Int? = null
    private var scrollFlag : Boolean? = null

    private var viewPager : ViewPager? = null
    private var textView : TextView? = null

    private var titles : MutableList<String> = ArrayList()
    private var imgs : MutableList<String> = ArrayList()
    private var pics : MutableList<ImageView> = ArrayList()

    private var currentPage : Int = 1

    constructor(context : Context) : this(context , null){}

    constructor(context : Context , attributeSet: AttributeSet?) : this(context , attributeSet , 0){}

    constructor(context: Context , attributeSet: AttributeSet? , defStyleAttr: Int)
            : this(context , attributeSet , defStyleAttr , 0){


    }

    constructor(context: Context , attributeSet: AttributeSet? , defStyleAttr: Int , defStyleRes : Int)
            :super(context , attributeSet , defStyleAttr , defStyleRes){
        initAttrs(attributeSet)

        viewPager = ViewPager(context)
        viewPager?.setBackgroundColor(Color.RED)
        val lvp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        lvp.addRule(CENTER_IN_PARENT)
        addView(viewPager , lvp)

        textView = TextView(context)
        textView?.setText("text")
        textView?.setTextSize(24.0F)
        val ltv = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 40)
        ltv.addRule(ALIGN_PARENT_BOTTOM)
        ltv.addRule(CENTER_HORIZONTAL)
        addView(textView , ltv)

    }

    private fun initImageViews() {
        for(i:Int in 0..imgs.size-1){
            Log.i("123" , "1")
            var imageView : ImageView = ImageView(context)
            var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            params.addRule(CENTER_IN_PARENT)
            Picasso.with(context)
                    .load(imgs.get(i))
                    .into(imageView)
            pics.add(imageView)
        }
    }

    fun start(){
        Log.i("123" , "$imgs.size")
        initImageViews()
        initViewPager()
    }


    private fun initAttrs(attributeSet: AttributeSet?){
        val ta : TypedArray = context.obtainStyledAttributes(attributeSet , R.styleable.Banner)
        textSize = ta.getFloat(R.styleable.Banner_titleSize , 0F)
        textColor = ta.getColor(R.styleable.Banner_titleColor , R.color.colorWhite)
        indicator = ta.getResourceId(R.styleable.Banner_indicatorFormat , 0)
        scrollFlag = ta.getBoolean(R.styleable.Banner_scroll , true)

        ta.recycle()
    }

    fun setTitles(titles : List<String>){
        this.titles.add(titles.get(titles.size - 1))
        this.titles.addAll(titles)
        this.titles.add(titles.get(0))
        Log.i("123" , "setTitles")
    }

    fun setImgsByUrl(imgs : List<String>) {
        this.imgs.add(imgs.get(imgs.size - 1))
        this.imgs.addAll(imgs)
        this.imgs.add(imgs.get(0))
        Log.i("123" , "setImgsByUrl")
    }

    fun getImgsNum() : Int?{
        return imgs.size
    }


    private fun initViewPager() {

        viewPager?.adapter = object : PagerAdapter(){
            override fun getCount(): Int {
                return pics.size
            }

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return view == `object`
            }

            override fun instantiateItem(container: ViewGroup?, position: Int): Any {
                container?.addView(pics.get(position))
                val view = pics.get(position)

                return view
            }

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
                container?.removeView(`object` as View)
            }

        }
        viewPager?.currentItem = currentPage
        viewPager?.addOnPageChangeListener(  object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                viewPager!!.currentItem = currentPage
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if(position == 0){
                    currentPage = getImgsNum()!! - 2
                }else if(position == imgs.size -1){
                    currentPage = 1
                }else{
                    currentPage = position
                }
            }
        })

    }


}