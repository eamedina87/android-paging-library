package ec.erickmedina.paging_test.search

import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import ec.erickmedina.paging_test.R
import ec.erickmedina.paging_test.common.base.BaseFragment
import ec.erickmedina.paging_test.entity.LastFmResponses
import ec.erickmedina.paging_test.search.adapter.SearchAdapter
import ec.erickmedina.paging_test.search.adapter.SearchPageAdapter
import ec.erickmedina.paging_test.states.NetworkState
import ec.erickmedina.paging_test.util.invisible
import ec.erickmedina.paging_test.util.visible
import kotlinx.android.synthetic.main.fragment_search.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(), SearchContract.View {

    override fun getLayoutId(): Int = R.layout.fragment_search

    override val mViewModel by viewModel<SearchViewModel>()

    private var mPagedAdapter : SearchPageAdapter = SearchPageAdapter()

    override fun initView() {
        setActivityTitle("Search")
        setActivityButtonUp(false)
        search_img.setOnClickListener { searchForArtist() }
        search_list_container.adapter = mPagedAdapter
    }

    private fun searchForArtist() {
        when {
            search_text.text.toString().isNotEmpty() -> {
                val listing = mViewModel.getArtistForInput(search_text.text.toString())
                listing.pagedList.observe(this, Observer {
                    when (it.size) {
                        0 -> onArtistSearchEmpty()
                        else -> onArtistSearchSuccess(it)
                    }
                })
                listing.dataState.observe(this, Observer {
                    when (it) {
                        is NetworkState.Loading -> showProgress()
                        is NetworkState.Success -> hideProgress()
                        is NetworkState.Error -> {
                            hideProgress()
                            onArtistSearchError(it.error.toString())
                        }
                    }
                })
            }
            else -> search_text.requestFocus()
        }
    }

    override fun showProgress() {
        progressbar.visible()
    }

    override fun hideProgress() {
        progressbar.invisible()
    }

    override fun onArtistSearchSuccess(list: PagedList<LastFmResponses.Artist>) {
        empty_message.invisible()
        mPagedAdapter.submitList(list)
    }

    override fun onArtistSearchEmpty() {
        empty_message.visible()
    }

    override fun onArtistSearchError(error: String?) {
        showMessage(error ?: "error")
    }

}