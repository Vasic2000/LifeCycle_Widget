package cz.vasic2000.lifecycle_widget

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class EmptyFragment : Fragment() {
    private val TAG = "[CycleActivity]"
    lateinit var firstRunTextView: TextView

    companion object {
        fun newInstance() = EmptyFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        makeMessage("onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeMessage("onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val emptyFragmentView: View = inflater.inflate(R.layout.fragment_empty, container, false)
        firstRunTextView = emptyFragmentView.findViewById(R.id.textViewInfo) as TextView
        if (savedInstanceState == null) {
            firstRunTextView.text = "Первый запуск!"
        } else {
            firstRunTextView.text = "Повторный запуск!"
        }
        makeMessage("onCreateView()")
        emptyFragmentView.findViewById<Button>(R.id.buttonExit).setOnClickListener {
            activity?.finish()
        }
        return emptyFragmentView
    }

    override fun onStart() {
        super.onStart()
        makeMessage("onStart()");
    }

    override fun onResume() {
        super.onResume()
        makeMessage("onResume()");
    }

    override fun onPause() {
        super.onPause()
        makeMessage("onPause()");
    }

    override fun onStop() {
        super.onStop()
        makeMessage("onStop()");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        makeMessage("onDestroyView()");
    }

    override fun onDestroy() {
        super.onDestroy()
        makeMessage("onDestroy()");
    }

    override fun onDetach() {
        super.onDetach()
        makeMessage("onDetach()");
    }

    private fun makeMessage(message: String) {
        Toast.makeText(
            getContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
        Log.i(TAG, message)
    }
}
