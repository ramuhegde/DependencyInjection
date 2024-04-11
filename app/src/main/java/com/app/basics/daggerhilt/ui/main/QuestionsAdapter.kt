package com.app.basics.daggerhilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.basics.daggerhilt.databinding.QuestionItemBinding
import com.app.basics.daggerhilt.storage.db.Question
import com.app.basics.daggerhilt.util.toDate

class QuestionsAdapter() : RecyclerView.Adapter<QuestionsAdapter.QuestionsHolder>() {

    private val questionsList = ArrayList<Question>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsHolder {
        return QuestionsHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = questionsList.size

    override fun onBindViewHolder(holder: QuestionsHolder, position: Int) {
        holder.bindData(questionsList[position])
    }

    fun bindQuestions(questions: List<Question>) {
        questionsList.clear()
        questionsList.addAll(questions)
        notifyDataSetChanged()
    }

    class QuestionsHolder(private val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(question: Question) {
            binding.apply {
                questionTitle.text = question.title
                ownerName.text = question.owner.displayName
                dateTime.text = question.creationDate.toDate(binding.root.context)
            }
        }

    }
}