package mx.com.coppel.coppel_test.util

import android.app.AlertDialog
import android.content.Context
import mx.com.coppel.coppel_test.R

/* 
 * @author azapata 
 * Feb 2022
*/
class AlertDialogUtil {
    companion object{

        fun loadAlertDialog(context: Context, msg: String) : AlertDialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage(msg)
            builder.setCancelable(true)
            return builder.create()
        }

        fun errorAlertDialog(context: Context, msg: String): AlertDialog{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage(msg)
            builder.setCancelable(true)
            builder.setPositiveButton(
                "Aceptar"
            ) { dialog, id -> dialog.cancel() }
            return builder.create()
        }

        fun infoAlertDialog(context: Context, msg: String): AlertDialog{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Información")
            builder.setMessage(msg)
            builder.setCancelable(true)
            builder.setPositiveButton(
                "Aceptar"
            ) { dialog, id -> dialog.cancel() }
            return builder.create()
        }
    }
}