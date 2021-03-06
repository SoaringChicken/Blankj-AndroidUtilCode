package com.blankj.utilcode.pkg.feature.span

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import android.text.style.UpdateAppearance
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorInt
import com.blankj.common.activity.CommonActivity
import com.blankj.utilcode.pkg.R
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.span_activity.*

/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/27
 * desc  : demo about SpanUtils
 * ```
 */
class SpanActivity : CommonActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, SpanActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var mSpanUtils: SpanUtils
    private lateinit var animSsb: SpannableStringBuilder

    private var lineHeight: Int = 0
    private var textSize: Float = 0f
    private lateinit var valueAnimator: ValueAnimator
    private lateinit var mShader: Shader
    private var mShaderWidth: Float = 0f
    private lateinit var matrix: Matrix
    private lateinit var mBlurMaskFilterSpan: BlurMaskFilterSpan
    private lateinit var mShadowSpan: ShadowSpan
    private lateinit var mForegroundAlphaColorSpan: ForegroundAlphaColorSpan
    private lateinit var mForegroundAlphaColorSpanGroup: ForegroundAlphaColorSpanGroup
    private lateinit var mPrinterString: String
    private var density: Float = 0f

    override fun bindTitleRes(): Int {
        return R.string.demo_span
    }

    override fun bindLayout(): Int {
        return R.layout.span_activity
    }

    override fun initView(savedInstanceState: Bundle?, contentView: View?) {
        super.initView(savedInstanceState, contentView)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                ToastUtils.showShort("???????????????")
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        lineHeight = spanAboutTv.lineHeight
        textSize = spanAboutTv.textSize
        density = resources.displayMetrics.density

        SpanUtils.with(spanAboutTv)
                .appendLine("SpanUtils").setBackgroundColor(Color.LTGRAY).setBold().setForegroundColor(Color.YELLOW).setHorizontalAlign(Layout.Alignment.ALIGN_CENTER)
                .appendLine("?????????").setForegroundColor(Color.GREEN)
//                .appendLine("????????????").setForegroundColor(Color.RED).setBackgroundColor(Color.LTGRAY).setFontSize(10).setLineHeight(280, SpanUtils.ALIGN_BOTTOM)
                .appendLine("?????????").setBackgroundColor(Color.LTGRAY)
                .appendLine("??????????????????").setLineHeight(2 * lineHeight, SpanUtils.ALIGN_CENTER).setBackgroundColor(Color.LTGRAY)
                .appendLine("??????????????????").setLineHeight(2 * lineHeight, SpanUtils.ALIGN_BOTTOM).setBackgroundColor(Color.GREEN)
                .appendLine("?????????????????????????????????????????????????????????").setLeadingMargin(textSize.toInt() * 2, 10).setBackgroundColor(Color.GREEN)
                .appendLine("?????????????????????????????????????????????????????????").setQuoteColor(Color.GREEN, 10, 10).setBackgroundColor(Color.LTGRAY)
                .appendLine("????????????????????????????????????????????????????????????").setBullet(Color.GREEN, 20, 10).setBackgroundColor(Color.LTGRAY).setBackgroundColor(Color.GREEN)
                .appendLine("32dp ??????").setFontSize(32, true)
                .appendLine("2 ?????????").setFontProportion(2f)
                .appendLine("?????? 2 ?????????").setFontXProportion(1.5f)
                .appendLine("?????????").setStrikethrough()
                .appendLine("?????????").setUnderline()
                .append("??????").appendLine("??????").setSuperscript()
                .append("??????").appendLine("??????").setSubscript()
                .appendLine("??????").setBold()
                .appendLine("??????").setItalic()
                .appendLine("?????????").setBoldItalic()
                .appendLine("monospace ??????").setFontFamily("monospace")
                .appendLine("???????????????").setTypeface(Typeface.createFromAsset(assets, "fonts/dnmbhs.ttf"))
                .appendLine("????????????").setHorizontalAlign(Layout.Alignment.ALIGN_OPPOSITE)
                .appendLine("????????????").setHorizontalAlign(Layout.Alignment.ALIGN_CENTER)
                .appendLine("????????????").setHorizontalAlign(Layout.Alignment.ALIGN_NORMAL)
                .append("??????").appendLine("????????????").setClickSpan(clickableSpan)
                .append("??????").appendLine("Url").setUrl("https://github.com/Blankj/AndroidUtilCode")
                .append("??????").appendLine("??????").setBlur(3f, BlurMaskFilter.Blur.NORMAL)
                .appendLine("????????????").setShader(LinearGradient(0f, 0f, 64f * density * 4f, 0f, resources.getIntArray(R.array.rainbow), null, Shader.TileMode.REPEAT)).setFontSize(64, true)
                .appendLine("????????????").setFontSize(64, true).setShader(BitmapShader(BitmapFactory.decodeResource(resources, R.drawable.span_cheetah), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT))
                .appendLine("????????????").setFontSize(64, true).setBackgroundColor(Color.BLACK).setShadow(24f, 8f, 8f, Color.WHITE)

                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_TOP)
                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_CENTER)
                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_BASELINE)
                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_low, SpanUtils.ALIGN_BOTTOM)
                .appendLine("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_TOP)
                .append("??????").setBackgroundColor(Color.LTGRAY)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_TOP)
                .append("??????").setBackgroundColor(Color.LTGRAY)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_TOP)
                .appendLine("??????").setBackgroundColor(Color.LTGRAY)

                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
                .append("??????").setBackgroundColor(Color.GREEN)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_CENTER)
                .appendLine("??????").setBackgroundColor(Color.GREEN)

                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
                .append("??????").setBackgroundColor(Color.LTGRAY)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
                .append("??????").setBackgroundColor(Color.LTGRAY)
                .appendImage(R.drawable.span_block_high, SpanUtils.ALIGN_BOTTOM)
                .appendLine("??????").setBackgroundColor(Color.LTGRAY)

                .append("????????????").appendSpace(30, Color.LTGRAY).appendSpace(50, Color.GREEN).appendSpace(100).appendSpace(30, Color.LTGRAY).appendSpace(50, Color.GREEN)
                .create()

        //        initAnimSpan();
        //        startAnim();
    }

    private fun initAnimSpan() {
        mShaderWidth = 64f * density * 4f
        mShader = LinearGradient(0f, 0f,
                mShaderWidth, 0f,
                resources.getIntArray(R.array.rainbow), null,
                Shader.TileMode.REPEAT)
        matrix = Matrix()

        mBlurMaskFilterSpan = BlurMaskFilterSpan(25f)

        mShadowSpan = ShadowSpan(8f, 8f, 8f, Color.WHITE)

        mForegroundAlphaColorSpan = ForegroundAlphaColorSpan(Color.TRANSPARENT)

        mForegroundAlphaColorSpanGroup = ForegroundAlphaColorSpanGroup(0f)

        mPrinterString = "?????????????????????????????????????????????????????????..."

        mSpanUtils = SpanUtils()
                .appendLine("????????????").setFontSize(64, true).setShader(mShader)
                .appendLine("????????????").setFontSize(64, true).setSpans(mBlurMaskFilterSpan)
                .appendLine("????????????").setFontSize(64, true).setBackgroundColor(Color.BLACK).setSpans(mShadowSpan)
                .appendLine("????????????").setFontSize(64, true).setSpans(mForegroundAlphaColorSpan)
        var i = 0
        val len = mPrinterString.length
        while (i < len) {
            val span = ForegroundAlphaColorSpan(Color.TRANSPARENT)
            mSpanUtils.append(mPrinterString.substring(i, i + 1)).setSpans(span)
            mForegroundAlphaColorSpanGroup.addSpan(span)
            ++i
        }
        animSsb = mSpanUtils.create()
    }

    private fun startAnim() {
        valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener { animation ->
            // shader
            matrix.reset()
            matrix.setTranslate(animation.animatedValue as Float * mShaderWidth, 0f)
            mShader.setLocalMatrix(matrix)

            // blur
            mBlurMaskFilterSpan.radius = 25 * (1.00001f - animation.animatedValue as Float)

            // shadow
            mShadowSpan.dx = 16 * (0.5f - animation.animatedValue as Float)
            mShadowSpan.dy = 16 * (0.5f - animation.animatedValue as Float)

            // alpha
            mForegroundAlphaColorSpan.setAlpha((255 * animation.animatedValue as Float).toInt())

            // printer
            mForegroundAlphaColorSpanGroup.alpha = animation.animatedValue as Float

            // showMsg
            spanAboutAnimTv.text = animSsb
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = (600 * 3).toLong()
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.start()
    }

    override fun doBusiness() {}

    override fun onDebouncingClick(view: View) {}

//    override fun onDestroy() {
//        if (valueAnimator.isRunning) {
//            valueAnimator.cancel()
//        }
//        super.onDestroy()
//    }
}

class BlurMaskFilterSpan(private var mRadius: Float) : CharacterStyle(), UpdateAppearance {
    private var mFilter: MaskFilter? = null

    var radius: Float
        get() = mRadius
        set(radius) {
            mRadius = radius
            mFilter = BlurMaskFilter(mRadius, BlurMaskFilter.Blur.NORMAL)
        }

    override fun updateDrawState(ds: TextPaint) {
        ds.maskFilter = mFilter
    }
}

class ForegroundAlphaColorSpan(@param:ColorInt private var mColor: Int) : CharacterStyle(), UpdateAppearance {

    fun setAlpha(alpha: Int) {
        mColor = Color.argb(alpha, Color.red(mColor), Color.green(mColor), Color.blue(mColor))
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = mColor
    }
}

class ForegroundAlphaColorSpanGroup(private val mAlpha: Float) {

    private val mSpans: ArrayList<ForegroundAlphaColorSpan> = ArrayList()

    var alpha: Float
        get() = mAlpha
        set(alpha) {
            val size = mSpans.size
            var total = 1.0f * size.toFloat() * alpha
            for (index in 0 until size) {
                val span = mSpans[index]
                if (total >= 1.0f) {
                    span.setAlpha(255)
                    total -= 1.0f
                } else {
                    span.setAlpha((total * 255).toInt())
                    total = 0.0f
                }
            }
        }

    fun addSpan(span: ForegroundAlphaColorSpan) {
        span.setAlpha((mAlpha * 255).toInt())
        mSpans.add(span)
    }
}

class ShadowSpan(private val radius: Float, var dx: Float, var dy: Float, private val shadowColor: Int) : CharacterStyle(), UpdateAppearance {

    override fun updateDrawState(tp: TextPaint) {
        tp.setShadowLayer(radius, dx, dy, shadowColor)
    }
}