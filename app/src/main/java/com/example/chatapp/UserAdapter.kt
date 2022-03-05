package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class UserAdapter(val context:Context, val userList:ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserviewHolder>() {

    class UserviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtname = itemView.findViewById<TextView>(R.id.txtname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserviewHolder {
       val view:View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserviewHolder(view)
    }

    override fun onBindViewHolder(holder: UserviewHolder, position: Int) {
       val currentUser = userList[position]

        holder.txtname.text = currentUser.name

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}