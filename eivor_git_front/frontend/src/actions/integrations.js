import axios from 'axios'
import { createMessage } from './messages'

import { GET_INTEGRATIONS, DELETE_INTEGRATION, ADD_INTEGRATION, GET_ERRORS } from './types'

// CRUD?
export const getIntegrations = () => dispatch => {
    axios.get('/api/integrations/')
        .then(response => {
            dispatch({
                type: GET_INTEGRATIONS,
                payload: response.data
            });
        }).catch(err => console.log(err))
}

export const deleteIntegration = (id) => dispatch => {
    axios.delete(`/api/integrations/${id}/`)
        .then(response => {
            dispatch(createMessage({ msg: `Integration #${id} was deleted` }))
            dispatch({
                type: DELETE_INTEGRATION,
                payload: id
            });
        }).catch(err => console.log(err))
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
            const error = {
                msg: err.response.data,
                status: err.response.status
            }

            dispatch({
                type: GET_ERRORS,
                payload: error
            })
        })
}