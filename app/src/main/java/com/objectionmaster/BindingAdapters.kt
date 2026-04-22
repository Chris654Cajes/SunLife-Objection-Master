package com.objectionmaster

import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.objectionmaster.model.Objection

/**
 * Data binding adapter to load text from objection
 */
@BindingAdapter("objectionTitle")
fun bindObjectionTitle(textView: TextView, objection: Objection?) {
    objection?.let {
        textView.text = it.title
    }
}

/**
 * Data binding adapter to load response text
 */
@BindingAdapter("objectionResponse")
fun bindObjectionResponse(textView: TextView, objection: Objection?) {
    objection?.let {
        textView.text = it.response
    }
}

/**
 * Data binding adapter for follow-up question
 */
@BindingAdapter("objectionFollowUp")
fun bindObjectionFollowUp(textView: TextView, objection: Objection?) {
    objection?.let {
        textView.text = it.followUp
    }
}

/**
 * Data binding adapter for psychology explanation
 */
@BindingAdapter("objectionPsychology")
fun bindObjectionPsychology(textView: TextView, objection: Objection?) {
    objection?.let {
        textView.text = it.psychology
    }
}

/**
 * Data binding adapter for confidence tip
 */
@BindingAdapter("objectionConfidenceTip")
fun bindObjectionConfidenceTip(textView: TextView, objection: Objection?) {
    objection?.let {
        textView.text = it.confidenceTip
    }
}
