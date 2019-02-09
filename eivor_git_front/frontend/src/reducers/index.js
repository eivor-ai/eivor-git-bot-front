import { combineReducers } from 'redux'
import integrations from './integrations'
import errors from './errors'
import messages from './messages'

export default combineReducers({
    integrations,
    errors,
    messages
});