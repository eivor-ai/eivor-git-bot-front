import React, { Component } from 'react'
import { connect } from 'react-redux'
import PropTypes from 'prop-types'
import { addIntegration } from '../../actions/integrations'

export class Form extends Component {
  state = {
    app_name: '',
    secret: '',
    oauth_token: '',
    bot_username: '',
    server_url: ''
  }

  static propTypes = {
    addIntegration: PropTypes.func.isRequired
  }

  onChange = e => this.setState({
    [e.target.name]: e.target.value
  })

  onSubmit = e => {
    e.preventDefault()
    // console.log('submit')
    const { app_name, secret, oauth_token, bot_username, server_url, user } = this.state;
    const integration = { app_name, secret, oauth_token, bot_username, server_url, user }
    this.props.addIntegration(integration)
    this.setState({
      app_name: '',
      secret: '',
      oauth_token: '',
      bot_username: '',
      server_url: ''
    })
  }

  render() {
    const { app_name, secret, oauth_token, bot_username, server_url } = this.state;
    return (
      <div className="card border-secondry mt-4 mb-4">
        <div className="card-header">Add integration</div>
        <div className="card-body">
          <form onSubmit={this.onSubmit}>
            <div className="form-group">
              <label htmlFor="app_name">Friendly Name</label>
              <input type="text"
                className="form-control"
                name="app_name"
                placeholder="Enter a name"
                onChange={this.onChange}
                value={app_name} />
            </div>
            <div className="form-group">
              <label htmlFor="secret">Secret Token</label>
              <input type="text"
                className="form-control"
                name="secret"
                placeholder="Psst, your token please"
                onChange={this.onChange}
                value={secret} />
            </div>
            <div className="form-group">
              <label htmlFor="oauth_token">Access/OAuth Token</label>
              <input type="text"
                className="form-control"
                name="oauth_token"
                aria-describedby="tokenhelp"
                placeholder="And your Access/OAuth Token"
                onChange={this.onChange}
                value={oauth_token} />
              <small id="tokenhelp" className="form-text text-muted">We always encrypt your tokens and never share them with anyone else.</small>
            </div>
            <div className="form-group">
              <label htmlFor="bot_username">Username of the bot</label>
              <input type="text"
                className="form-control"
                name="bot_username"
                placeholder="Beep boop, beep beep?"
                onChange={this.onChange}
                value={bot_username} />
            </div>
            <div className="form-group">
              <label htmlFor="server_url">URL of your GitLab server</label>
              <input type="text"
                className="form-control"
                name="server_url"
                aria-describedby="serverHelp"
                placeholder="Tell us where you are"
                onChange={this.onChange}
                value={server_url} />
              <small id="serverHelp" className="form-text text-muted">This defaults to <b>https://gitlab.com</b>.</small>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
          </form>
        </div>
      </div>
    )
  }
}


export default connect(null, { addIntegration })(Form)
