package co.com.store.profile


import android.app.Fragment
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.com.store.R
import co.com.store.login.LoginActivity
import co.com.store.utils.FragmentCallback
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment(), IUserProfileFragmentView {

    private val mPresenter: IUserProfileFragmentPresenter = UserProfileFragmentPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.bind(this)
        mPresenter.bindFragmentCallback(activity as FragmentCallback)
        mPresenter.onCreate(arguments)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onViewCreated()
    }

    override fun initComponents() {

        mSRLProfile?.isEnabled = false

        mBTNSignIn?.setOnClickListener {
            activity?.let {
                activity.startActivity(Intent(activity, LoginActivity::class.java))
            }
        }

        mBTNLogOut?.setOnClickListener {
            mPresenter.logOut()
        }

        mLYAddress?.setOnClickListener {
            mPresenter.openAddressFragment()
        }

    }


    override fun showProgressBar() {
        mSRLProfile?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLProfile?.isRefreshing = false
    }

    override fun setName(name: String) {
        mTVName?.text = name
    }

    override fun setEmail(email: String) {
        mTVEmail?.text = email
    }

    override fun hideUserDetails() {
        mLYAddress?.visibility = View.GONE
        mTVName?.visibility = View.GONE
        mTVEmail?.visibility = View.GONE
        mBTNLogOut?.visibility = View.GONE
        mBTNSignIn?.visibility = View.VISIBLE
    }

    override fun showUserDetails() {
        mLYAddress?.visibility = View.VISIBLE
        mTVName?.visibility = View.VISIBLE
        mTVEmail?.visibility = View.VISIBLE
        mBTNLogOut?.visibility = View.VISIBLE
        mBTNSignIn?.visibility = View.GONE
    }

    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }
}
