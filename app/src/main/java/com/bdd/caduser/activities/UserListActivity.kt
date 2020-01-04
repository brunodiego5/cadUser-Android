package com.bdd.caduser.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bdd.caduser.R
import com.bdd.caduser.helpers.UserAdapter
import com.bdd.caduser.models.User
import com.bdd.caduser.services.ServiceBuilder
import com.bdd.caduser.services.UserService
import kotlinx.android.synthetic.main.activity_user_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_user_list)
		setSupportActionBar(toolbar)
		toolbar.title = title

		fab.setOnClickListener {
			val intent = Intent(this@UserListActivity, UserCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()

     	 loadUsers()
	}

	private fun loadUsers() {
		val userService: UserService = ServiceBuilder.buildService(UserService::class.java)

		val requestCall = userService.getAll()

		requestCall.enqueue(object: Callback<List<User>> {

			override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
				if (response.isSuccessful) {
					//200 para 299
					val userList = response.body()!!
					user_recycler_view.adapter = UserAdapter(userList)
				} else if (response.code() == 401) {
					Toast.makeText(this@UserListActivity, "Your session has expired. Please Login again.", Toast.LENGTH_LONG).show()

				} else {
					//300, 400, 500
					Toast.makeText(this@UserListActivity, "Failed to retrive items", Toast.LENGTH_LONG).show()
				}
			}

			//Network error, Establishing connection with server
			override fun onFailure(call: Call<List<User>>, t: Throwable) {
				Toast.makeText(this@UserListActivity, "Error Occurred" + t.toString(), Toast.LENGTH_LONG).show()

			}
		})
	}
}
