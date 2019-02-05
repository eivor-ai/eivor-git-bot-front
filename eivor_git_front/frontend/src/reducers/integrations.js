import { GET_INTEGRATIONS, DELETE_INTEGRATION, ADD_INTEGRATION } from '../actions/types.js'
import { loadavg } from 'os';

const initialState = {
    integrations: []
}

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_INTEGRATIONS:
            return {
                ...state,
                integrations: action.payload
            }
        case DELETE_INTEGRATION:
            return {
                ...state,
                integrations: state.integrations.filter(integration => integration.id !== action.payload)
            }
        case ADD_INTEGRATION:
            return {
                ...state,
                integrations: [...state.integrations, action.payload]
            }
        default:
            return state;
    }
}