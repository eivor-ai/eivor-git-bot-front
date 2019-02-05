import axios from 'axios'

import { GET_INTEGRATIONS, DELETE_INTEGRATION, ADD_INTEGRATION } from './types'

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
            dispatch({
                type: DELETE_INTEGRATION,
                payload: id
            });
        }).catch(err => console.log(err))
}

export const addIntegration = (integration) => dispatch => {
    axios.post('/api/integrations/', integration)
        .then(response => {
            dispatch({
                type: ADD_INTEGRATION,
                payload: response.data
            });
        }).catch(err => console.log(err))
}