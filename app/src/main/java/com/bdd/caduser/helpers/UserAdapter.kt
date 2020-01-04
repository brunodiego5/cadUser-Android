package com.bdd.caduser.helpers

import android.content.Intent
// import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bdd.caduser.activities.UserDetailActivity
import com.bdd.caduser.models.User
import com.bdd.caduser.R

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		holder.user = userList[position]
		holder.txvUser.text = userList[position].name

		holder.itemView.setOnClickListener { v ->
			val context = v.context
			val intent = Intent(context, UserDetailActivity::class.java)
			intent.putExtra(UserDetailActivity.ARG_ITEM_ID, holder.user!!.id)

			context.startActivity(intent)
		}
	}

	override fun getItemCount(): Int {
		return userList.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val txvUser: TextView = itemView.findViewById(R.id.txv_user)
		var user: User? = null

		override fun toString(): String {
			return """${super.toString()} '${txvUser.text}'"""
		}
	}
}
