package co.com.store.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import co.com.store.R
import co.com.store.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginActivityView {

    private val mPresenter:ILoginActivityPresenter = LoginActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter.bind(this)
        mPresenter.onCreate(null)
    }

    override fun initComponents() {
        supportActionBar?.hide()
        mSRLLogin?.isEnabled = false
        mBTNSignIn?.setOnClickListener {
            mETPassword?.let {
                mEtEmail?.let {
                    mPresenter.login(mEtEmail.text.toString(), mETPassword.text.toString())
                }
            }
        }
    }

    override fun loginSuccess() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }

    override fun showError(message: String) {
        mBTNSignIn?.let {
            Snackbar.make(mBTNSignIn,message, Snackbar.LENGTH_LONG).show()

        }
    }

    override fun showProgressBar() {
        mSRLLogin?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLLogin?.isRefreshing = false
    }
}
