import React, { Component, Fragment } from 'react'
import { withAlert } from 'react-alert'

import { connect } from 'react-redux'
import PropTypes from 'prop-types'

export class Alerts extends Component {

    static propTypes = {
        error: PropTypes.object.isRequired,
        message: PropTypes.object.isRequired
    }

    componentDidUpdate(previousProps) {
        const { error, alert, message } = this.props;
        if (error !== previousProps.error) {
            Object.keys(error.msg).forEach(function (key) {
                var actualError = error.msg[key]
                if (actualError.join !== undefined) {
                    actualError = actualError.join(', ')
                }
                alert.error(`Error in ${key}: ${actualError}`)
            })
        }

        if (message !== previousProps.message) {
            if (message.msg)
                alert.success(message.msg)
        }
    }

    render() {
        return <Fragment />
    }
}

const mapStateToProps = state => ({
    error: state.errors,
    message: state.messages
})

export default connect(mapStateToProps)(withAlert(Alerts))
