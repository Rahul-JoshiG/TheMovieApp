package com.example.themovieapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.themovieapp.R
import com.example.themovieapp.databinding.ActivityMovieFragmentBinding
import com.example.themovieapp.fragments.MovieFragment
import com.example.themovieapp.util.Constant
import kotlin.properties.Delegates

class MovieFragmentActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMovieFragmentBinding
    private lateinit var mCurrentVisibleFragment : Fragment
    private val mMovieFragment by lazy{ MovieFragment() }
    private val mFragmentManager = supportFragmentManager
    private lateinit var mMovieType : String
    private var mPosition by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        extractDataFromMainActivity()
        openFragment(mMovieFragment)

    }

    private fun openFragment(fragment: Fragment) {
        Log.d(TAG, "openFragment: opening fragment $fragment")
        if(::mCurrentVisibleFragment.isInitialized){
            hideFragment(mCurrentVisibleFragment)
        }
        addAndShowFragment(fragment)
    }

    private fun addAndShowFragment(fragment: Fragment) {
        Log.d(TAG, "addAndShowFragment: add and show fragment $fragment")
        if(!fragment.isAdded){
            mCurrentVisibleFragment = fragment
            val ft = mFragmentManager.beginTransaction()
            ft.add(R.id.fragment_container_view, fragment)
            ft.commitAllowingStateLoss()
            return
        }
        showFragment(fragment)
    }

    private fun showFragment(fragment: Fragment) {
        Log.d(TAG, "showFragment: show fragment = $fragment")
        val ft = mFragmentManager.beginTransaction()
        ft.show(fragment)
        ft.commit()
    }

    private fun hideFragment(fragment: Fragment) {
        Log.d(TAG, "hideFragment: hiding the fragment $fragment")
        val ft = mFragmentManager.beginTransaction()
        ft.hide(fragment)
        ft.commit()
    }

    private fun extractDataFromMainActivity() {
        Log.d(TAG, "extractDataFromMainActivity: extracting data which came from Main activity")
        val data = intent.extras
        mMovieType = data?.getString(Constant.MOVIE_TYPE).toString()
        mPosition = data?.getInt(Constant.MOVIE_POSITION).toString().toInt()
        Log.d(TAG, "extractDataFromMainActivity: data = $data movie type = $mMovieType position = $mPosition")
        mMovieFragment.arguments = data
    }

    companion object{
        private const val TAG = "MovieFragmentActivity"
    }
}
