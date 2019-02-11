import axios from 'axios'
import { createMessage, returnErrors } from './messages'
import { tokenConfig } from './auth'

import { GET_INTEGRATIONS, DELETE_INTEGRATION, ADD_INTEGRATION, GET_ERRORS } from './types'

// CRUD?
export const getIntegrations = () => (dispatch, getState) => {
    axios.get('/api/integrations/', tokenConfig(getState))
        .then(response => {
            dispatch({
                type: GET_INTEGRATIONS,
                payload: response.data
            });
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
        })
}

export const deleteIntegration = (id) => dispatch => {
    axios.delete(`/api/integrations/${id}/`)
        .then(response => {
            dispatch(createMessage({ msg: `Integration #${id} was deleted` }))
            dispatch({
                type: DELETE_INTEGRATION,
                payload: id
            });
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
        })
}

export const addIntegration = (integration) => dispatch => {
    axios.post('/api/integrations/', integration)
        .then(response => {
            dispatch(createMessage({ msg: `Integration #${response.data.id} created` }))
            dispatch({
                type: ADD_INTEGRATION,
                payload: response.data
            });
        }).catch(err => {
            dispatch(returnErrors(err.response.data, err.response.status))
        })
}