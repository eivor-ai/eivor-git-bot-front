import axios from 'axios'
import { returnErrors } from './messages'
import { USER_LOADED, USER_LOADING, AUTH_ERROR, LOGIN_SUCCESS, LOGIN_FAILED, LOGOUT_SUCCESS } from './types'

export const loadUser = () => (dispatch, getState) => {
    axios.get('/api/account/user', tokenConfig(getState))
        .then(res => {
            dispatch({
                type: USER_LOADED,
                payload: res.data
            })
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
            dispatch({
                type: AUTH_ERROR
            })
        })
}

export const login = (username, password) => (dispatch) => {

    const config = {
        headers: {
            "Content-Type": "application/json",
        }
    }

    const body = JSON.stringify({ username, password })

    axios.post('/api/auth/login', body, config)
        .then(res => {
            dispatch({
                type: LOGIN_SUCCESS,
                payload: res.data
            })
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
            dispatch({
                type: LOGIN_FAILED
            })
        })
}

export const logout = () => (dispatch, getState) => {
    axios.post('/api/auth/logout/', null, tokenConfig(getState))
        .then(res => {
            dispatch({
                type: LOGOUT_SUCCESS
            })
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
            dispatch({
                type: AUTH_ERROR
            })
        })
}

export const tokenConfig = getState => {
    // Get token
    const token = getState().auth.token
    const config = {
        headers: {
            "Content-Type": "application/json",
        }
    }

    if (token) {
        config.headers["Authorization"] = `Token ${token}`
    }

    return config
}