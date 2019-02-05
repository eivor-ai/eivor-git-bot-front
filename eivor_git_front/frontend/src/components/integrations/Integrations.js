import React, { Component, Fragment } from 'react'
import { connect } from 'react-redux'
import PropTypes from 'prop-types'
import { getIntegrations, deleteIntegration } from '../../actions/integrations'

export class Integrations extends Component {
  static propTypes = {
    integrations: PropTypes.array.isRequired,
    getIntegrations: PropTypes.func.isRequired,
    deleteIntegration: PropTypes.func.isRequired
  }

  componentDidMount() {
    this.props.getIntegrations()
  }

  render() {
    return (
      <Fragment>
        <h2>Your integrations</h2>
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Server URL</th>
              <th>Bot Username</th>
              <th />
            </tr>
          </thead>
          <tbody>
            {this.props.integrations.map(integration => (
              <tr key={integration.id}>
                <td>{integration.id}</td>
                <td>{integration.server_url}</td>
                <td>{integration.bot_username}</td>
                <td><button className="btn btn-sm btn-danger" onClick={this.props.deleteIntegration.bind(this, integration.id)}>Delete</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </Fragment>
    )
  }
}

const mapStateToProps = state => ({
  integrations: state.integrations.integrations
})

export default connect(mapStateToProps, { getIntegrations, deleteIntegration })(Integrations)
