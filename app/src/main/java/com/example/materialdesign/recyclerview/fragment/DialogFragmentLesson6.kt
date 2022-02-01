
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

const val AGREEMENT_KEY = "ОК"

class DialogFragmentLesson6 : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        return builder.setTitle(
           "Удалить?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage("Вы уверены?")
            .setPositiveButton("Да",
                DialogInterface.OnClickListener { dialog, which ->
                    parentFragmentManager.setFragmentResult(DialogFragmentLesson6::class.simpleName!!,
                        Bundle().also { it.putBoolean(AGREEMENT_KEY, true) })
                })
            .setNegativeButton("Нет", null)
            .create()
    }
}