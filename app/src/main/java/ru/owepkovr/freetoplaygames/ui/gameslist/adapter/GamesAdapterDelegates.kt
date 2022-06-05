package ru.owepkovr.freetoplaygames.ui.gameslist.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import freetoplaygames.databinding.GamesItemBinding
import ru.owepkovr.freetoplaygames.arch.recycler.RecyclerViewItem
import ru.owepkovr.freetoplaygames.data.model.ui.GameUIModel

typealias OnItemClick = (GameUIModel) -> Unit

object GamesAdapterDelegates {
    fun gamesDelegate(clickCallback: OnItemClick) =
        adapterDelegateViewBinding<GameUIModel, RecyclerViewItem, GamesItemBinding>(
            { layoutInflater, root -> GamesItemBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                binding.apply {
                    root.setOnClickListener {
                        clickCallback(item)
                    }
                    titleTv.text = item.title
                    shortDescTv.text = item.shortDescription

                    Picasso
                        .get()
                        .load(item.thumbnail)
                        .into(gamePic)
                }
            }
        }
}