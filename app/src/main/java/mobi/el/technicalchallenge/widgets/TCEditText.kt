package mobi.el.technicalchallenge.widgets

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import mobi.el.technicalchallenge.R
import mobi.el.technicalchallenge.widgets.TCEditText.EditTextType.*

import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import mobi.el.technicalchallenge.databinding.LayoutTcEdittextBinding


class TCEditText : LinearLayout {

    enum class EditTextType {
        PASSWORD,
        EMAIL,
        AGE
    }

    lateinit var inputEditText: EditText
    lateinit var labelTextView: TextView
    lateinit var errorTextView: TextView
    var editTextType: EditTextType = EMAIL
    var binding : LayoutTcEdittextBinding? = null


    constructor(context: Context)
            : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_tc_edittext, this, true);

        this.inputEditText = binding?.edittextInput!!
        this.labelTextView = binding?.textviewLabel!!
        this.errorTextView = binding?.textviewError!!
    }

    fun setInputEditText(editTextType: EditTextType) {
        setType(editTextType)
        when (editTextType) {
            PASSWORD -> {
                setLabel(this.resources.getString(R.string.password))
                this.inputEditText.setHint(R.string.password)
                this.inputEditText.setFilters(arrayOf<InputFilter>(LengthFilter(12)))
                this.inputEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            EMAIL -> {
                setLabel(this.resources.getString(R.string.email))
                this.inputEditText.setHint(R.string.email)
                this.inputEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            AGE -> {
                setLabel(this.resources.getString(R.string.age))
                this.inputEditText.setFilters(arrayOf<InputFilter>(LengthFilter(2)))
                this.inputEditText.setHint(R.string.age)
                this.inputEditText.inputType = InputType.TYPE_CLASS_NUMBER
            }
        }
    }

    fun setType(editTextType: EditTextType) {
        this.editTextType = editTextType
    }

    fun setLabel(label: String) {
        this.labelTextView.setText(label)
    }

    fun getInput(): String {
        return this.inputEditText.text.toString()
    }

    fun setHint(hint : String){
        this.inputEditText.hint = hint
    }

    fun setWrongInputDrawable(){
        inputEditText
            .setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                R.drawable.wrong_entry, 0)
    }

    fun removeWrongInputDrawable(){
        inputEditText
            .setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                0, 0)
    }

    fun setErrorText(error : String){
        errorTextView.visibility = VISIBLE
        errorTextView.text = error
    }

    fun removeErrorText(){
        errorTextView.visibility = INVISIBLE
    }
}