package mx.com.coppel.coppel_test.ui.character_screen.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.coppel.coppel_test.data.model.CharactersResponse
import mx.com.coppel.coppel_test.data.repository.MarvelRepo
import mx.com.coppel.coppel_test.util.ResourceState
import mx.com.coppel.coppel_test.util.StringUtil
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

/* 
 * @author azapata 
 * Feb 2022
*/

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val marvelRepo: MarvelRepo,
    @Named("auth_public_token") private val authPublicToken: String,
    @Named("auth_private_token") private val authPrivateToken: String
) : ViewModel() {

    private val _verifyScreenState = MutableLiveData<ResourceState<CharactersResponse>>(ResourceState.Default)
    private val _offset = MutableLiveData(0)
    private val _limit = MutableLiveData(StringUtil.RESPONSE_TOTAL_ITEMS)
    private val _reachTheLimit = MutableLiveData(false)
    private val _showFirstAlert = MutableLiveData(true)

    var offset: MutableLiveData<Int> = _offset
    var limit: MutableLiveData<Int> = _limit
    var reachTheLimit: MutableLiveData<Boolean> = _reachTheLimit
    var showFirstAlert: MutableLiveData<Boolean> = _showFirstAlert
    val verifyScreenState: MutableLiveData<ResourceState<CharactersResponse>> = _verifyScreenState

    fun getCharacters() {
        _verifyScreenState.value = ResourceState.Loading
        try {
            viewModelScope.launch {
                _verifyScreenState.value = ResourceState.Success(
                    marvelRepo.getCharacters(
                        offset = offset.value!!,
                        limit = limit.value!!,
                        authPublicToken = authPublicToken,
                        authPrivateToken = authPrivateToken
                    )
                )
            }
        } catch (ex: Exception) {
            _verifyScreenState.value = ResourceState.Failure(ex)
        }
    }

    fun updateReachTheLimit(){
        _reachTheLimit.value = true
    }
    fun updateShowFirstAlert(){
        _showFirstAlert.value = false
    }

    fun addMoreItems() {
        _offset.value = _offset.value?.plus(_limit.value!!)
        getCharacters()
    }
}