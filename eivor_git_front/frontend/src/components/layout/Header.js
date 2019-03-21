import React, { Component } from 'react'
import { connect } from 'react-redux'
import PropTypes from 'prop-types'
import { logout } from '../../actions/auth'

export class Header extends Component {
  static propTypes = {
    auth: PropTypes.object.isRequired,
    logout: PropTypes.func.isRequired
  }

  render() {
    const { isAuthenticated, user } = this.props.auth

    const authLinks = (
      <ul className="navbar-nav ml-autho mt-2 mt-lg-0">
        <span className="navbar-text mr-3">
          Howdy, <strong>{user ? `${user.username}` : ''}</strong>
        </span>
        <li className="nav-item">
          <a onClick={this.props.logout} href="javascript:;" className="nav-link text-light">Logout</a>
        </li>
      </ul>
    )
    const guestLinks = (
      // No Op for now
      ""
    )

    return (
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <a className="navbar-brand" href="/">Eivor Git Bot</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarColor02">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item active">
              <a className="nav-link" href="/">Dashboard</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="https://jgodara.github.io" target="_blank">Developer Website</a>
            </li>
          </ul>
        </div>

        {isAuthenticated ? authLinks : guestLinks}
      </nav>
    )
  }
}

const mapStateToProps = state => ({
  auth: state.auth
})

export default connect(mapStateToProps, { logout })(Header)
