package com.example.andriispasswordmanager

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_row.view.*

class Adapter(context: Context) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var dataset = emptyList<AccountRecord>()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val itemRowView: ConstraintLayout) : RecyclerView.ViewHolder(itemRowView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): Adapter.MyViewHolder {
        // create a new view
        val itemRowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false) as ConstraintLayout
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(itemRowView)
    }

    internal fun setDataset(accounts: List<AccountRecord>) {
        this.dataset = accounts
        notifyDataSetChanged()
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemRowView.service_name.text = dataset[position].username
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}