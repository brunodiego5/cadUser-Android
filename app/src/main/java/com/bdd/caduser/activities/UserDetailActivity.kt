package com.bdd.caduser.activities

import android.content.Intent
import android.os.Bundle
// import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bdd.caduser.R
import com.bdd.caduser.helpers.SampleData
import com.bdd.caduser.models.User
import com.bdd.caduser.services.ServiceBuilder
import com.bdd.caduser.services.UserService
import kotlinx.android.synthetic.main.activity_user_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_user_detail)

		setSupportActionBar(detail_toolbar)
		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val bundle: Bundle? = intent.extras

		if (bundle?.containsKey(ARG_ITEM_ID)!!) {

			val id = intent.getIntExtra(ARG_ITEM_ID, 0)

			loadDetails(id)

			initUpdateButton(id)

			initDeleteButton(id)
		}
	}

	private fun loadDetails(id: Int) {
		val userService = ServiceBuilder.buildService(UserService::class.java)
		val requestCall = userService.getOne(id)

		requestCall.enqueue(object: Callback<User> {

			override fun onResponse(call: Call<User>, response: Response<User>) {
				if (response.isSuccessful) {
					val user = response.body()
					user?.let {
						et_name.setText(user.name)
						et_email.setText(user.email)

						collapsing_toolbar.title = user.name
					}
				} else {
					Toast.makeText(this@UserDetailActivity, "Failed to retrive details", Toast.LENGTH_LONG).show()
				}


			}

			override fun onFailure(call: Call<User>, t: Throwable) {
				Toast.makeText(this@UserDetailActivity, "Failed to retrive details "+t.toString(), Toast.LENGTH_LONG).show()
			}

		})
	}

	private fun initUpdateButton(id: Int) {

		btn_update.setOnClickListener {

			val name = et_name.text.toString()
			val email = et_email.text.toString()

			val user = User()

			user.id = id
			user.name = name
			user.email = email

			val userService = ServiceBuilder.buildService(UserService::class.java)
			val requestCall = userService.update(id, user)

			requestCall.enqueue(object: Callback<User> {

				override fun onResponse(call: Call<User>, response: Response<User>) {
					if (response.isSuccessful) {
						finish()
						Toast.makeText(this@UserDetailActivity, "Item Updated Successfully", Toast.LENGTH_SHORT).show()
						val user = response.body()

					} else {
						Toast.makeText(this@UserDetailActivity, "Failed to update item", Toast.LENGTH_SHORT).show()
					}


				}

				override fun onFailure(call: Call<User>, t: Throwable) {
					Toast.makeText(this@UserDetailActivity, "Failed to update itemm "+t.toString(), Toast.LENGTH_SHORT).show()
				}

			})
		}
	}

	private fun initDeleteButton(id: Int) {

		btn_delete.setOnClickListener {

            val userService = ServiceBuilder.buildService(UserService::class.java)
            val requestCall = userService.delete(id)

            requestCall.enqueue(object: Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        finish()
                        Toast.makeText(this@UserDetailActivity, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@UserDetailActivity, "Failed to delete", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@UserDetailActivity, "Failed to delete "+t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		val id = item.itemId
		if (id == android.R.id.home) {
			navigateUpTo(Intent(this, UserListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {

		const val ARG_ITEM_ID = "item_id"
	}
}
